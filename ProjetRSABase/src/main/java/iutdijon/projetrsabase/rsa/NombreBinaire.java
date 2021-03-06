package iutdijon.projetrsabase.rsa;

import iutdijon.projetrsabase.network.Network;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Description de la classe
 *
 * @author Matthieu
 */
public class NombreBinaire {

    /**
     * Liste des bits du nombre binaire
     */
    private BitSet listeBits;

    /**
     * Taille forcée
     */
    private int tailleForcee = -1;

    // ------- METHODES DEJA IMPLEMENTEES --------------------------------------
    //Set un bit
    public void set(int i, boolean valeur) {
        this.listeBits.set(i, valeur);
    }

    //Get un bit
    public boolean get(int i) {
        return this.listeBits.get(i);
    }

    //Constructeurs standard
    public NombreBinaire() {
        this.listeBits = new BitSet();
    }

    //Constructeur clone
    public NombreBinaire(NombreBinaire nombre) {
        this.listeBits = new BitSet();
        for (int i = 0; i < nombre.listeBits.length(); i++) {
            this.listeBits.set(i, nombre.listeBits.get(i));
        }
    }

    //Constructeur à partir d'un int
    public NombreBinaire(int valeur) {
        this.listeBits = new BitSet();
        int i = 0;
        while (valeur != 0) {
            this.listeBits.set(i, valeur % 2 == 1);
            valeur /= 2;
            i++;
        }
    }

    //Constructeur à partir d'une chaine de caractère binaire
    public NombreBinaire(String s) {
        this();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(s.length() - i - 1) == '1') {
                this.listeBits.set(i, true);
            }
        }
    }

    //Renvoie la taille (en nb de bits)
    public int getTaille() {
        int valeur = 0;
        if (this.tailleForcee == -1) {
            valeur = this.listeBits.length();
        } else {
            valeur = this.tailleForcee;
        }
        return valeur;
    }

    //Force la taille (en nb de bits)
    public void forcerTaille(int valeur) {
        this.tailleForcee = valeur;
    }

    //Affichage
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.getTaille(); i++) {
            if (this.listeBits.get(i)) {
                res = "1" + res;
            } else {
                res = "0" + res;
            }
        }
        if ("".equals(res)) {
            res = "0";
        }
        return res;
    }

    //Scinde un nombreBinaire en nombreBinaire de taille donnée
    public ArrayList<NombreBinaire> scinder(int tailleMorceau) {
        ArrayList<NombreBinaire> res = new ArrayList<>();
        for (int i = 0; i < this.getTaille(); i = i + tailleMorceau) {
            NombreBinaire bitset = new NombreBinaire();
            for (int j = 0; j < tailleMorceau; j++) {
                bitset.set(j, this.listeBits.get(i + j));
            }
            NombreBinaire nb = new NombreBinaire(bitset);
            nb.tailleForcee = tailleMorceau;
            res.add(nb);
        }
        return res;
    }

    //Concaténation de deux nombre binaires de taille : tailleMorceau
    public NombreBinaire concatenation(NombreBinaire mot) {
        NombreBinaire sortie = new NombreBinaire(this);
        int taille = this.getTaille() + mot.getTaille();
        for (int i = 0; i < taille; i++) {
            if (i < this.getTaille()) {
                sortie.listeBits.set(i, this.listeBits.get(i));
            } else {
                sortie.listeBits.set(i, mot.listeBits.get(i - this.getTaille()));
            }
        }
        sortie.forcerTaille(taille);
        return sortie;
    }

    //-------------------------------------------------------------------------
    //DEFI 2 - Renvoie le résultat de l'addition de this avec mot2
    public NombreBinaire addition(NombreBinaire mot2) {
        NombreBinaire nb = new NombreBinaire();
        int retenus = 0;
        int bit1 = 0;
        int bit2 = 0;
        int val = 0;
        int i = 0;
        boolean b1 = false;
        boolean b2 = false;

        if (mot2.getTaille() < this.getTaille()) {
            val = this.getTaille() - mot2.getTaille();
            for (int j = 0; j < val; j++) {
                mot2.set(mot2.getTaille() + j, false);
            }
        }

        while (i <= this.getTaille() || i <= mot2.getTaille()) {
            b1 = this.get(i);
            b2 = mot2.get(i);
            if (b1) {
                bit1 = 1;
            } else if (!b1) {
                bit1 = 0;
            }
            if (b2) {
                bit2 = 1;
            } else if (!b2) {
                bit2 = 0;
            }
            switch (bit1 + bit2 + retenus) {
                case 0:
                    nb.set(i, false);
                    retenus = 0;
                    break;
                case 1:
                    nb.set(i, true);
                    retenus = 0;
                    break;
                case 2:
                    nb.set(i, false);
                    retenus = 1;
                    break;
                case 3:
                    nb.set(i, true);
                    retenus = 1;
                    break;
                default:
                    break;
            }
            i++;

        }
        return nb;
    }

    //DEFI 3 - Caclule le décalage de n bits (multiplie par 2^n)
    public NombreBinaire decalage(int n) {
        NombreBinaire nbB = new NombreBinaire();
        nbB.forcerTaille(this.getTaille() + n);
        for (int i = 0; i < nbB.getTaille(); i++) {
            if (i < n) {
                nbB.set(i, false);
            } else {
                nbB.set(i, this.get(i - n));
            }
        }
        return nbB;
    }

    //DEFI 4 - renvoie le resultat de l'addition de this avec mot3
    public NombreBinaire soustraction(NombreBinaire mot2) {
        return null;
    }

    //DEFI 5 - Renvoie si this est plus petit ou égal à mot2
    public boolean estInferieurA(NombreBinaire mot2) {
        return false;
    }

    //DEFI 6 - Renvoie si this est égal à mot2 ou non
    public boolean estEgal(NombreBinaire mot2) {
        boolean bool = true;
        for (int i = 0; i < this.listeBits.size() - 1; i++) {
            boolean m1 = this.get(i);
            boolean m2 = mot2.get(i);
            if (m1 != m2) {
                bool = false;
            }
        }
        return bool;
    }

    //DEFI 7 - Renvoie si un nombre est pair
    public boolean estPair() throws IOException {
        boolean bool = false;

        boolean i = this.listeBits.get(0);
        if (i == false) {
            bool = true;
        }
        return bool;
    }

    //DEFI 8 - Calcul la multiplication de this avec mot2
    public NombreBinaire multiplication(NombreBinaire mot2) {
        NombreBinaire nb1 = new NombreBinaire();
        NombreBinaire nb2 = new NombreBinaire();
        NombreBinaire nb3 = new NombreBinaire();
        for(int i=0;i<this.getTaille();i++){
            if (this.get(i)){
                nb3=new NombreBinaire(mot2);
                nb1 = nb3.decalage(i);
                nb2=nb2.addition(nb1);
            }
        }
        return nb2;
    }

    //DEFI 9 - Calcul le quotient dans la division euclidienne de this par mot2
    public NombreBinaire quotient(NombreBinaire mot2) {
        return null;
    }

    //DEFI 10 - Calcul this modulo mot2 via une division euclidienne
    public NombreBinaire modulo(NombreBinaire mot2) {
        return null;
    }

    //DEFI 11 - Génère un nombre binaire aléatoire de "taille" bits au maximum.
    public static NombreBinaire randomAvecTailleMax(int taille) {
        return null;
    }

    //DEFI 12 - Calcul de this^exposant modulo m par exponentiation modulaire rapide
    public NombreBinaire puissanceModulo(NombreBinaire exposant, NombreBinaire m) {
        return null;
    }

    //DEFI 13 - Calcul le PGCD de this et mot2
    public NombreBinaire PGCD(NombreBinaire mot2) {
        return null;
    }

    //DEFI 14 - renvoie un nombre aléatoire entre min (inclu) et max (non inclu)
    public static NombreBinaire random(NombreBinaire min, NombreBinaire max) {
        return null;
    }

    //DEFI 15 - Calcul de l'inverse modulo nombre
    //Basé sur l'algo d'euclide étendu (adapté).
    public NombreBinaire inverseModulaire(NombreBinaire nombre) {
        NombreBinaire ZERO = new NombreBinaire(0);

        NombreBinaire n0 = new NombreBinaire(nombre);
        NombreBinaire b0 = new NombreBinaire(this);
        NombreBinaire t0 = new NombreBinaire(0);
        NombreBinaire t = new NombreBinaire(1);

        NombreBinaire q = n0.quotient(b0);
        NombreBinaire r = n0.modulo(b0);
        while (!r.estEgal(ZERO)) {
            NombreBinaire produit = q.multiplication(t);
            NombreBinaire memoire;
            //Gére le fait qu'un nombreBinaire ne peut pas être négatif......
            if (t0.estInferieurA(produit)) {
                memoire = nombre.soustraction(produit.soustraction(t0).modulo(nombre));
            } else {
                memoire = t0.soustraction(produit).modulo(nombre);
            }

            t0 = t;
            t = memoire;
            n0 = b0;
            b0 = r;
            q = n0.quotient(b0);
            r = n0.modulo(b0);
        }
        return t;
    }

}
