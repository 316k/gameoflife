package ca.qc.bdeb.inf203.jameoflife;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerWindows;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Nicolas Hurtubise
 */
public class JameOfLife {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ControllerWindows.nouvellePartie();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JameOfLife.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
