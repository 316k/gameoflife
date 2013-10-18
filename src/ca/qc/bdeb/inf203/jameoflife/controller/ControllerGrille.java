package ca.qc.bdeb.inf203.jameoflife.controller;

import ca.qc.bdeb.inf203.jameoflife.model.Grille;
import ca.qc.bdeb.inf203.jameoflife.model.RuleSet;
import ca.qc.bdeb.inf203.jameoflife.view.JCellule;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolas Hurtubise
 */
public class ControllerGrille {

    private static Grille grid;

    /**
     * Définit les dimentions de la grille à utiliser
     * @param width
     * @param height
     */
    public static void setGridDimensions(int width, int height) {
        grid = new Grille(width, height, new RuleSet("23/3")); // Conway's game of life by default
    }

    /**
     * Défini la règle à suivre pour la grille
     *
     * @param ruleString Règle de Game of life au format "[Survit]/[Naît]"
     * @param grid 
     * @param lblGeneration 
     * @param btnProchaineGeneration 
     */
    public static void setRules(String ruleString, JCellule[][] grid, JLabel lblGeneration, JButton btnProchaineGeneration) {
        ControllerGrille.grid.setRuleSet(ruleString);
        synchroniser(grid, lblGeneration, btnProchaineGeneration);
    }

    /**
     * Inverse la cellule à la position (x,y)
     * @param grid
     * @param x
     * @param y
     * @param lblGeneration
     * @param btnProchaineGeneration
     */
    public static void flipCell(JCellule[][] grid, int x, int y, JLabel lblGeneration, JButton btnProchaineGeneration) {
        ControllerGrille.grid.flipCell(x, y);
        synchroniser(grid, lblGeneration, btnProchaineGeneration);
    }

    /**
     * Randomize la grille de cellules
     * @param grid
     * @param lblGeneration
     * @param btnProchaineGeneration
     */
    public static void randomize(JCellule[][] grid, JLabel lblGeneration, JButton btnProchaineGeneration) {
        ControllerGrille.grid.randomize();
        synchroniser(grid, lblGeneration, btnProchaineGeneration);
    }

    /**
     * Fait augmenter la grille de nbrGenerations générations
     * @param grid
     * @param nbrGenerations
     * @param lblGeneration
     * @param btnProchaineGeneration
     */
    public static void prochaineGeneration(JCellule[][] grid, double nbrGenerations, JLabel lblGeneration, JButton btnProchaineGeneration) {
        ControllerGrille.grid.incrementGeneration(nbrGenerations);
        synchroniser(grid, lblGeneration, btnProchaineGeneration);
    }

    /**
     * Définit si la grille doit être en mode wrap (Pac-man mode)
     * @param isSelected
     * @param grid
     * @param lblGeneration
     * @param btnProchaineGeneration
     */
    public static void setWrap(boolean isSelected, JCellule[][] grid, JLabel lblGeneration, JButton btnProchaineGeneration) {
        ControllerGrille.grid.setWrap(isSelected);
        synchroniser(grid, lblGeneration, btnProchaineGeneration);
    }

    /**
     * Synchronise la grille de JCellules avec le modèle Grille
     * @param grid
     * @param lblGeneration
     * @param btnProchaineGeneration
     */
    public static void synchroniser(JCellule[][] grid, JLabel lblGeneration, JButton btnProchaineGeneration) {
        double[][] opacites = ControllerGrille.grid.getOpacites();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setOpacite(opacites[i][j]);
                grid[i][j].repaint();
            }
        }

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        lblGeneration.setText("Génération #" + nf.format(ControllerGrille.grid.getGeneration()));
        btnProchaineGeneration.setText("Prochaine Génération");
        btnProchaineGeneration.setToolTipText(null);
        btnProchaineGeneration.setBackground(Color.green);
        if (ControllerGrille.grid.isStable()) {
            btnProchaineGeneration.setText(btnProchaineGeneration.getText());
            btnProchaineGeneration.setToolTipText("La grille est déjà dans un état stable. La prochaine génération sera identique à celle-ci.");
            btnProchaineGeneration.setBackground(Color.pink);
        }
    }

    /**
     * Sauvegarde la grille dans un fichier
     */
    public static void save() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);
        try {
            PrintWriter fichierSauvegarde = new PrintWriter(new FileOutputStream(fileChooser.getSelectedFile()));
            fichierSauvegarde.print(grid.dump());
            fichierSauvegarde.close();
        }
        catch (FileNotFoundException|NullPointerException ex) {
            
        }
    }
}
