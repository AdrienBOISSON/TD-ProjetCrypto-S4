/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.rsa.NombreBinaire;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author gb363467
 */
public class DefiInferieur extends Defi {

    private Socket socket;
    private BufferedReader fluxentrant;
    private PrintWriter fluxsortant;
    
    
    @Override
    public void executer() throws IOException {
        
            //connexion au serveur
        socket = new Socket("127.0.0.1",1234);
        String mess = "";
        String mess2= "";
        InputStreamReader input = new InputStreamReader(socket.getInputStream());
        fluxentrant = new BufferedReader(input);
        fluxsortant = new PrintWriter(this.socket.getOutputStream(),true);
        //affiche le défi actuel
        System.out.println(fluxentrant.readLine());
        //debut du defi
        String messret;
        int i =0;
        while(i == 0){
            //lis les messages du serveur
            mess = fluxentrant.readLine();
            if("Defi valide".equals(mess) || "NOK".equals(mess)){
                i =3;
                fluxentrant.close();
                fluxsortant.close();
                break;
            }
            
            System.out.println(mess);
            mess2 = fluxentrant.readLine();
            System.out.println(mess2);
            //fait le calcul
            NombreBinaire nbB = new NombreBinaire(mess); 
            NombreBinaire nb2 = new NombreBinaire(mess2);
            Boolean emessret = nbB.estInferieurA(nb2);
            messret = emessret.toString();
            //envoi la réponse 
            System.out.println(messret);
            fluxsortant.println(messret);
            //recoie la validation du serveur ou non
            mess = fluxentrant.readLine();
            System.out.println(mess);
           
             
        }
    }
    
    
}
