package ca.qc.bdeb.inf203.jameoflife.controller;

import ca.qc.bdeb.inf203.jameoflife.view.WindowMain;
import ca.qc.bdeb.inf203.jameoflife.view.WindowNewGame;
import java.awt.Shape;
import javax.security.auth.Destroyable;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.xml.bind.annotation.XmlElementDecl;
import sun.awt.GlobalCursorManager;

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
