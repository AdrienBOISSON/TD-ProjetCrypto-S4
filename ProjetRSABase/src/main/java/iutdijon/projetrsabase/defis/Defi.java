package iutdijon.projetrsabase.defis;

import java.io.IOException;

/**
 * Classe abstraite pour un défi
 * @author Matthieu
 */
public abstract class Defi {
    /**
     * Lancer le défi
     * @throws java.io.IOException Exception
     */
    public abstract void executer() throws IOException;
}
