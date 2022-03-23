/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;
import java.io.IOException;

/**
 *
 * @author Hugo
 */
public class Defi6EstEgal extends Defi{

    @Override
    public void executer() throws IOException {
          Network net = new Network();
        boolean bool;
        net.receiveMessage();
       String s =  "";
       String s2 = "";
        NombreBinaire nb = new NombreBinaire(net.receiveMessage());
        NombreBinaire nb2 = new NombreBinaire(net.receiveMessage());
        while(!s.equals("Defi valide")){
            
           
             bool = nb.estEgal(nb2);
        net.sendMessage(Boolean.toString(bool));
        s = net.receiveMessage();
        s = net.receiveMessage();
        s2 = net.receiveMessage();
        nb= new NombreBinaire(s);
        nb2 = new NombreBinaire(s2);
        
        }
    }
    }
    

