/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client de connexion TCP
 * @author Matthieu
 */
public class Network {
    private Socket socket; 
    private PrintWriter pw;
    private BufferedReader bufr;
    
    /**
     * Constructeur
     * @throws IOException 
     */
    public Network() throws IOException {
         socket = new Socket("127.0.0.1",1234);
         InputStreamReader input = new InputStreamReader(socket.getInputStream());
         bufr = new BufferedReader(input);
         pw = new PrintWriter(this.socket.getOutputStream(),true);
    }
    
    /**
     * Envoi d'un message
     */
    public void sendMessage(String message) throws IOException {
        System.out.println(">" + message);
        pw.println(message);
    }
        
    /**
     * Réception d'un message
     */
    public String receiveMessage() throws IOException {
        String messret = bufr.readLine();
        System.out.println("<" + messret);
        return messret;
    }
    
    /**
     * Fin de la connexion
     */
    public void end() throws IOException {
        System.out.println(">" + "FIN");
        pw.println("FIN");
    }
}
