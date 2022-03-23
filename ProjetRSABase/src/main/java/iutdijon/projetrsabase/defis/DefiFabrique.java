package iutdijon.projetrsabase.defis;

import iutdijon.projetrsabase.defis.realisations.*;

/**
 * Fabrique des défis
 * @author Matthieu
 */
public class DefiFabrique {
    
    /**
     * Crée le défi
     * @param numeroDuDefis numéro du défi à créer
     * @return le défi
     */
    public static Defi creer(int numeroDuDefis) {
        Defi defi = null;
        
        switch(numeroDuDefis) {
            case 1 : defi = new Defi1ConnexionAuServeur(); break;
            
            case 3 : defi = new DefiDecalage(); break;
            
            case 2 : defi = new Defi2EstPair();break;
            
            case 4 : defi = new DefiAddition();break;
            
            case 6 : defi = new Defi6EstEgal();break;
            
            case 7 : defi = new DefiMultiplication();break;
            
            default : throw new UnsupportedOperationException("Défis non implémenté !");
        }
        
        return defi;
    }
    
}
