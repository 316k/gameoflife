package ca.qc.bdeb.inf203.jameoflife.controller;

import ca.qc.bdeb.inf203.jameoflife.view.WindowApropos;
import ca.qc.bdeb.inf203.jameoflife.view.WindowPrincipale;
import ca.qc.bdeb.inf203.jameoflife.view.WindowNouvellePartie;

/**
 * Controller s'occupant de l'interaction entre les fenêtres
 *
 * @author Nicolas Hurtubise
 */
public class ControllerFenetres {

    private static WindowPrincipale fenetrePrincipale = null;
    private static WindowNouvellePartie fenetreNouvellePartie = null;
    private static WindowApropos fenetreApropos = null;

    /**
     * Affiche la fenêtre de paramétrage de nouvelle partie
     */
    public static void nouvellePartie() {
        if (fenetreNouvellePartie != null) {
            fenetreNouvellePartie.dispose();
        }
        fenetreNouvellePartie = new WindowNouvellePartie();
        if (fenetrePrincipale != null) {
            fenetrePrincipale.dispose();
        }
    }

    /**
     * Quitte le logiciel
     */
    public static void quitter() {
        System.exit(0);
    }

    /**
     * Crée une nouvelle partie
     *
     * @param lignes Nombre de lignes de la gille de game of life à créer
     * @param colonnes Nombre de colonnes de la gille de game of life à créer
     */
    public static void confirmationNouvellePartie(int lignes, int colonnes) {
        fenetrePrincipale = new WindowPrincipale(lignes, colonnes);
        fenetreNouvellePartie.dispose();
    }

    /**
     * Ouvre la fenêtre "à propos"
     */
    public static void apropos() {
        if (fenetreApropos != null) {
            fenetreApropos.dispose();
        }

        fenetreApropos = new WindowApropos();
    }

    /**
     * Détermine si le logiciel doit être quitté
     */
    public static void fermeture() {
        if ((fenetrePrincipale != null && !fenetrePrincipale.isDisplayable())
                && (fenetreNouvellePartie != null && !fenetreNouvellePartie.isDisplayable())) {
            quitter();
        }
    }
}
