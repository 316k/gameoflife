package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerGrille;
import ca.qc.bdeb.inf203.jameoflife.controller.ControllerFenetres;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.*;

/**
 *
 * @author Nicolas Hurtubise
 */
public class WindowPrincipale extends JFrame {
    
    private PanelGrille pnlGrid;
    private PanelOptions pnlSettings;
    private MenuBar menu;
    private Menu menuFichier, menuApropos;
    private MenuItem miNouvellePartie, miSauvegarder, miQuitter, miApropos;
    
    public WindowPrincipale(int lignes, int colonnes) {
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
                ControllerFenetres.nouvellePartie();
            }
        });
        
        miSauvegarder = new MenuItem("Sauvegarder");
        miSauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerGrille.save();
            }
        });
        
        miQuitter = new MenuItem("Quitter");
        miQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        menuFichier.add(miNouvellePartie);
        menuFichier.add(miSauvegarder);
        menuFichier.addSeparator();
        menuFichier.add(miQuitter);
        
        menuApropos = new Menu("À propos");
        
        miApropos = new MenuItem("À propos");
        miApropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerFenetres.apropos();
            }
        });
        
        menuApropos.add(miApropos);
        
        menu.add(menuFichier);
        menu.add(menuApropos);
        
        this.setMenuBar(menu);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Popup : Voulez-vous sauvegarder ?
                if (JOptionPane.showConfirmDialog(null, (Object) "Voulez-vous sauvegarder ?", "Voulez-vous sauvegarder ?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    ControllerGrille.save();
                }
            }
            
            @Override
            public void windowClosed(WindowEvent e) {
                ControllerFenetres.nouvellePartie();
            }
        });
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        ControllerGrille.setGridDimensions(lignes, colonnes);
        
        pnlGrid = new PanelGrille(lignes, colonnes);
        pnlGrid.setPreferredSize(new Dimension(400, 200));
        panel.add(pnlGrid);
        
        pnlSettings = new PanelOptions();
        panel.add(pnlSettings);
        
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public PanelGrille getPanelGrid() {
        return pnlGrid;
    }
    
    public PanelOptions getPanelSettings() {
        return pnlSettings;
    }
    
    public WindowPrincipale getThis() {
        return this;
    }
}
