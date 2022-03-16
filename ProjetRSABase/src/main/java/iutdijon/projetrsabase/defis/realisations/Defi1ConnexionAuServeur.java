package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 1er défi : Connexion au serveur
 * @author Matthieu
 */
public class Defi1ConnexionAuServeur extends Defi {

    private Socket socket;
    private BufferedReader fluxentrant;
    private PrintWriter fluxsortant;
    
     
    @Override
    public void executer() throws IOException{
      socket = new Socket("127.0.0.1",1234);
      String mess;
        InputStreamReader input = new InputStreamReader(socket.getInputStream());
        fluxentrant = new BufferedReader(input);
        fluxsortant = new PrintWriter(this.socket.getOutputStream(),true);
        System.out.println(fluxentrant.readLine());
        String messret = "";
        int i =0;
        mess = fluxentrant.readLine();
        System.out.println(mess);
        while(i ==0){
            messret = "";
            int j = Integer.parseInt(mess);
            messret += Integer.valueOf(j+1);
            System.out.println(messret);
            fluxsortant.println(messret);
            mess = fluxentrant.readLine();
            System.out.println(mess);
            mess = fluxentrant.readLine();
            System.out.println(mess);
            if("Defi valide".equals(mess) || "NOK".equals(mess)){
                i =3;
            } 
        } 
     
       
    }
    
}
