package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerGrid;
import ca.qc.bdeb.inf203.jameoflife.controller.ControllerWindows;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private MenuBar menu;
    private Menu menuFichier, menuApropos;
    private MenuItem miNouvellePartie, miSauvegarder, miQuitter, miApropos;
    
    public WindowMain(int lignes, int colonnes) {
        this.setTitle("Jame of Life");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setName("mainWindow");
        
        menu = new MenuBar();
        menuFichier = new Menu("Fichier");
        miNouvellePartie = new MenuItem("Nouvelle partie");
        miNouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getThis().dispose();
                ControllerWindows.nouvellePartie();
            }
        });
        menuFichier.add(miNouvellePartie);
        menuApropos = new Menu("À propos");
        
        menu.add(menuFichier);
        menu.add(menuApropos);
        
        this.setMenuBar(menu);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Popup : Voulez-vous sauvegarder ?
                if (JOptionPane.showConfirmDialog(null, (Object) "Voulez-vous sauvegarder ?", "Voulez-vous sauvegarder ?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    ControllerGrid.save();
                }
            }
            
            @Override
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
    
    public WindowMain getThis() {
        return this;
    }
}
