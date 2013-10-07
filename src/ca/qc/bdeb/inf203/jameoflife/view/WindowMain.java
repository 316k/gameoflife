package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerGrid;
import ca.qc.bdeb.inf203.jameoflife.controller.ControllerWindows;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
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
                ControllerWindows.nouvellePartie();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        ControllerGrid.setGridDimensions(lignes, colonnes);

        pnlGrid = new PanelGrid(lignes, colonnes);
        pnlGrid.setPreferredSize(new Dimension(400, 200));
        panel.add(pnlGrid);

        pnlSettings = new PanelSettings();
        panel.add(pnlSettings);

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public PanelGrid getPanelGrid() {
        return pnlGrid;
    }

    public PanelSettings getPanelSettings() {
        return pnlSettings;
    }
}
