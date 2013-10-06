package ca.qc.bdeb.inf203.jameoflife.controller;

import ca.qc.bdeb.inf203.jameoflife.view.WindowMain;
import ca.qc.bdeb.inf203.jameoflife.view.WindowNewGame;

/**
 *
 * @author Nicolas Hurtubise
 */
public class ControllerWindows {

    private static WindowMain mainWindow = null;
    private static WindowNewGame newGameWindow = null;

    public static void newGame() {
        newGameWindow = new WindowNewGame();
    }

    public static void windowNewGameOk(int lignes, int colonnes) {
        mainWindow = new WindowMain(lignes, colonnes);
        newGameWindow.dispose();
    }
}
