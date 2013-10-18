package ca.qc.bdeb.inf203.jameoflife.controller;

import ca.qc.bdeb.inf203.jameoflife.view.WindowApropos;
import ca.qc.bdeb.inf203.jameoflife.view.WindowPrincipale;
import ca.qc.bdeb.inf203.jameoflife.view.WindowNouvellePartie;

/**
 *
 * @author Nicolas Hurtubise
 */
public class ControllerFenetres {

    private static WindowPrincipale fenetrePrincipale = null;
    private static WindowNouvellePartie fenetreNouvellePartie = null;
    private static WindowApropos fenetreApropos = null;

    public static void nouvellePartie() {
        if(fenetreNouvellePartie != null) {
            fenetreNouvellePartie.dispose();
        }
        if(fenetrePrincipale != null) {
            fenetrePrincipale.dispose();
        }
        
        fenetreNouvellePartie = new WindowNouvellePartie();
    }
    
    public static void quitter() {
        fenetreNouvellePartie.dispose();
    }

    public static void fenetreNouvellePartieOk(int lignes, int colonnes) {
        fenetrePrincipale = new WindowPrincipale(lignes, colonnes);
        fenetreNouvellePartie.dispose();
    }

    public static void apropos() {
        if (fenetreApropos != null) {
            fenetreApropos.dispose();
        }

        fenetreApropos = new WindowApropos();
    }
}
