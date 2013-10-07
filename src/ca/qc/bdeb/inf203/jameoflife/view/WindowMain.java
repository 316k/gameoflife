package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerGrid;
import ca.qc.bdeb.inf203.jameoflife.controller.ControllerWindows;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Nicolas Hurtubise
 */
public class WindowMain extends JFrame {

    private PanelGrid pnlGrid;
    private PanelSettings pnlSettings;
    
    public WindowMain(int lignes, int colonnes) {
        this.setTitle("Jame of Life");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setName("mainWindow");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Popup : Voulez-vous sauvegarder ?
            }

            public void windowClosed(WindowEvent e) {
                ControllerWindows.newGame();
            }
        });

        this.setLayout(new GridLayout(1, 2));
        ControllerGrid.setGridDimensions(lignes, colonnes);

        pnlGrid = new PanelGrid(lignes, colonnes);
        this.add(pnlGrid);
        
        pnlSettings = new PanelSettings();
        this.add(pnlSettings);

        this.setLocation(400, 200);
        this.pack();
        this.setVisible(true);
    }

    public PanelGrid getPanelGrid() {
        return pnlGrid;
    }
    
    public PanelSettings getPanelSettings() {
        return pnlSettings;
    }
}
