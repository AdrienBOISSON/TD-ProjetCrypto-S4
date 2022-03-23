/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author ab526488
 */
public class DefiAddition extends Defi{
    private Socket socket;
    private BufferedReader fluxentrant;
    private PrintWriter fluxsortant;

    @Override
    public void executer() throws IOException {
        /*//connexion au serveur
        socket = new Socket("127.0.0.1",1234);
        String mess;
        String mess2;
        InputStreamReader input = new InputStreamReader(socket.getInputStream());
        fluxentrant = new BufferedReader(input);
        fluxsortant = new PrintWriter(this.socket.getOutputStream(),true);
        //affiche le défi actuelle
        System.out.println(fluxentrant.readLine());
        String messret = "";
        //debut du defi
        int i =0;
        while(i ==0){
            //lis les messages du serveur
            mess = fluxentrant.readLine();
            System.out.println(mess);
            mess2 = fluxentrant.readLine();
            System.out.println(mess2);
            //fait le calcul
            NombreBinaire nbB = new NombreBinaire(mess); 
            messret =nbB.decalage(Integer.parseInt(mess2)).toString();
            
            //envoi la réponse 
            System.out.println(messret);
            fluxsortant.println(messret);
            //recoie la validation du serveur ou non
            mess = fluxentrant.readLine();
            System.out.println(mess);
           
            if("Defi valide".equals(mess) || "NOK".equals(mess)){
                i =3;
            } 
        } */
        
        Network net = new Network();
        String messageServeur = net.receiveMessage();
        NombreBinaire nb1;
        NombreBinaire nb2;
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué!"))
        {
            nb1 = new NombreBinaire(net.receiveMessage());
            nb2 = new NombreBinaire(net.receiveMessage());
            NombreBinaire nbRes = nb1.addition(nb2);
            net.sendMessage(nbRes.toString());
            //verif si ok ou non
            messageServeur = net.receiveMessage();
        }
        net.end();
    }
}
