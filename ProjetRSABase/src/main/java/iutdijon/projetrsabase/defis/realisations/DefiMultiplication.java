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
 * @author ab526488
 */
public class DefiMultiplication extends Defi {

    @Override
    public void executer() throws IOException {
        Network net = new Network();
        String messageServeur =  net.receiveMessage();
        NombreBinaire nb1;
        NombreBinaire nb2;
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué!"))
        {
            nb1 = new NombreBinaire(net.receiveMessage());
            nb2 = new NombreBinaire(net.receiveMessage());
            NombreBinaire nbRes = nb1.multiplication(nb2);
            net.sendMessage(nbRes.toString());
            //verif si ok ou non
            messageServeur = net.receiveMessage();
        }
        net.end();
    }
    
}
