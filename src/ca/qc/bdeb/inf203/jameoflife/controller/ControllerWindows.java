package ca.qc.bdeb.inf203.jameoflife.controller;

import ca.qc.bdeb.inf203.jameoflife.view.WindowApropos;
import ca.qc.bdeb.inf203.jameoflife.view.WindowMain;
import ca.qc.bdeb.inf203.jameoflife.view.WindowNouvellePartie;

/**
 *
 * @author Nicolas Hurtubise
 */
public class ControllerWindows {

    private static WindowMain mainWindow = null;
    private static WindowNouvellePartie fenetreNouvellePartie = null;
    private static WindowApropos fenetreApropos = null;

    public static void nouvellePartie() {
        if(fenetreNouvellePartie != null) {
            fenetreNouvellePartie.dispose();
        }
        fenetreNouvellePartie = new WindowNouvellePartie();
    }

    public static void fenetreNouvellePartieOk(int lignes, int colonnes) {
        mainWindow = new WindowMain(lignes, colonnes);
        fenetreNouvellePartie.dispose();
    }

    public static void apropos() {
        if (fenetreApropos != null) {
            fenetreApropos.dispose();
        }

        fenetreApropos = new WindowApropos();
    }
}
