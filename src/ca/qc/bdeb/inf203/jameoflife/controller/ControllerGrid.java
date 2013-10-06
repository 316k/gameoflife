package ca.qc.bdeb.inf203.jameoflife.controller;

import ca.qc.bdeb.inf203.jameoflife.model.Grid;
import ca.qc.bdeb.inf203.jameoflife.model.RuleSet;
import ca.qc.bdeb.inf203.jameoflife.view.JCell;

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
    public static boolean setRules(String ruleString) {
        try {
            grid.setRuleSet(ruleString);
        }
        catch (IllegalArgumentException e) {
            return false;
        }
        
        return true;
    }
    
    public static void flipCell(JCell cellule) {
        int x = cellule.getCoordonneeX();
        int y = cellule.getCoordonneeY();
        cellule.setOpacite(grid.flipCell(x, y).getOpacites()[x][y]);
    }
    
    public static void randomize(JCell[][] grid) {
        ControllerGrid.grid.randomize();
        synchroniser(grid);
    }
    
    public static void prochaineGeneration(JCell[][] grid, double nbrGenerations) {
        ControllerGrid.grid.incrementGeneration(nbrGenerations);
        synchroniser(grid);
    }
    
    public static void synchroniser(JCell[][] grid) {
        double[][] opacites = ControllerGrid.grid.getOpacites();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setOpacite(opacites[i][j]);
                grid[i][j].repaint();
            }
        }
    }
}
