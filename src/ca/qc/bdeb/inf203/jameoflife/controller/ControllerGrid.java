package ca.qc.bdeb.inf203.jameoflife.controller;

import ca.qc.bdeb.inf203.jameoflife.model.Grid;
import ca.qc.bdeb.inf203.jameoflife.model.RuleSet;
import ca.qc.bdeb.inf203.jameoflife.view.JCell;
import java.awt.Color;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Nicolas Hurtubise
 */
public class ControllerGrid {

    private static Grid grid;

    public static void setGridDimensions(int width, int height) {
        grid = new Grid(width, height, new RuleSet("23/3")); // Conway's game of life by default
    }

    /**
     * Défini la règle à suivre pour la grille
     *
     * @param ruleString Règle de Game of life au format "[Survit]/[Naît]"
     * @return si le changement de règle a fonctionné (si la règle spécifiée est
     * valide)
     */
    public static void setRules(String ruleString, JCell[][] grid, JLabel lblGeneration, JButton btnProchaineGeneration) {
        ControllerGrid.grid.setRuleSet(ruleString);
        synchroniser(grid, lblGeneration, btnProchaineGeneration);
    }
    
    public static void flipCell(JCell[][] grid, int x, int y, JLabel lblGeneration, JButton btnProchaineGeneration) {
        ControllerGrid.grid.flipCell(x, y);
        synchroniser(grid, lblGeneration, btnProchaineGeneration);
    }

    public static void randomize(JCell[][] grid, JLabel lblGeneration, JButton btnProchaineGeneration) {
        ControllerGrid.grid.randomize();
        synchroniser(grid, lblGeneration, btnProchaineGeneration);
    }

    public static void prochaineGeneration(JCell[][] grid, double nbrGenerations, JLabel lblGeneration, JButton btnProchaineGeneration) {
        ControllerGrid.grid.incrementGeneration(nbrGenerations);
        synchroniser(grid, lblGeneration, btnProchaineGeneration);
    }
    
    public static void setWrap(boolean isSelected, JCell[][] grid, JLabel lblGeneration, JButton btnProchaineGeneration) {
        ControllerGrid.grid.setWrap(isSelected);
        synchroniser(grid, lblGeneration, btnProchaineGeneration);
    }

    public static void synchroniser(JCell[][] grid, JLabel lblGeneration, JButton btnProchaineGeneration) {
        double[][] opacites = ControllerGrid.grid.getOpacites();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setOpacite(opacites[i][j]);
                grid[i][j].repaint();
            }
        }

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        lblGeneration.setText("Génération #" + nf.format(ControllerGrid.grid.getGeneration()));
        btnProchaineGeneration.setText("Prochaine Génération");
        btnProchaineGeneration.setToolTipText(null);
        btnProchaineGeneration.setBackground(Color.green);
        if (ControllerGrid.grid.isStable()) {
            btnProchaineGeneration.setText(btnProchaineGeneration.getText());
            btnProchaineGeneration.setToolTipText("La grille est déjà dans un état stable. La prochaine génération sera identique à celle-ci.");
            btnProchaineGeneration.setBackground(Color.pink);
        }
    }
}
