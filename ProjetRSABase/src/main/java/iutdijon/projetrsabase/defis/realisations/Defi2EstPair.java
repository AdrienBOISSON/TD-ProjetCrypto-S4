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
 * @author hv860430
 */
public class Defi2EstPair extends Defi{

    @Override
    public void executer() throws IOException {
        Network net = new Network();
        boolean bool;
        net.receiveMessage();
       String s =  "";
        
        while(!s.equals("Defi valide")){
            NombreBinaire nb = new NombreBinaire(net.receiveMessage());
             bool = nb.estPair();
        net.sendMessage(Boolean.toString(bool));
        s = net.receiveMessage();
        }
    }
    
}
