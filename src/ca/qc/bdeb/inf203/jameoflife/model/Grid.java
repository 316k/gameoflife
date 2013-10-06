package ca.qc.bdeb.inf203.jameoflife.model;

import java.util.Random;

/**
 *
 * @author Nicolas Hurtubise
 */
public class Grid {

    private RuleSet ruleSet;
    private boolean grid[][];
    private double generation = 0;
    private boolean wrap = false;

    public void setWrap(boolean wrap) {
        this.wrap = wrap;
    }

    public Grid(int x, int y, RuleSet ruleSet) {
        grid = new boolean[x][y];
        this.ruleSet = ruleSet;
    }

    public Grid setCell(int x, int y, boolean active) {
        grid[x][y] = active;
        return this;
    }

    public Grid randomize() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Random().nextBoolean(); // Ça devrait être une méthode statique
            }
        }
        return this;
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public Grid setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
        return this;
    }

    public Grid setRuleSet(String ruleSet) {
        this.ruleSet = new RuleSet(ruleSet);
        return this;
    }

    public Grid incrementGeneration(double nbrGenerations) {
        if (nbrGenerations + generation < Math.floor(generation) + 1) {
            // Génération incomplète: aucune incidence sur l'état des cases
            generation += nbrGenerations;
        } else {
            // Complétion d'une (ou plusieurs) génération(s) :
            // On passe à la génération suivante, puis on rappelle la fonction
            // autant de fois qu'il le faut.
            grid = nextGeneration();
            generation += (Math.floor(generation) + 1 - generation);
            nbrGenerations -= (Math.floor(generation) + 1 - generation);
            incrementGeneration(nbrGenerations);
        }

        return this;
    }

    public double getGeneration() {
        return generation;
    }

    private boolean[][] nextGeneration() {
        boolean[][] futureGrid = new boolean[grid.length][grid[0].length];

        boolean[][] context = new boolean[3][3];

        int x, y = 0;
        boolean value = false;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // Trouve le contexte de la cellule
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        x = i + k;
                        y = j + l;
                        if (x < 0 || y < 0
                                || x >= grid.length || y >= grid[0].length) {
                            // Out of bounds...
                            if (wrap) {
                                // On wrap les côtés (cf. pac-man)
                                if (x < 0) {
                                    x = grid.length - 1;
                                } else if (x >= grid.length) {
                                    x = 0;
                                }

                                if (y < 0) {
                                    y = grid[0].length - 1;
                                } else if (y >= grid[0].length) {
                                    y = 0;
                                }

                                value = grid[x][y];
                            } else {
                                // On considère que les côtés sont des morts
                                value = false;
                            }
                        } else {
                            value = grid[x][y];
                        }

                        context[k + 1][l + 1] = value;
                    }
                }

                futureGrid[i][j] = ruleSet.getNextValue(context);
            }
        }

        return futureGrid;
    }

    public double[][] getOpacites() {
        double[][] opacites = new double[grid.length][grid[0].length];
        boolean[][] nextGrid = this.nextGeneration();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (nextGrid[i][j] != grid[i][j]) {
                    // Proportionnel à l'endroit où on est rendu dans l'entre génération
                    if (grid[i][j]) {
                        // Si on passe de Vivant vers Mort
                        opacites[i][j] = (Math.floor(generation) + 1 - generation);
                    } else {
                        // Si on passe de Mort à Vivant
                        opacites[i][j] = 1 - (Math.floor(generation) + 1 - generation);
                    }
                } else {
                    // Sinon, l'opacité ne change pas entre les générations.
                    // Elle est donc forcément soit 100%, soit 0%
                    opacites[i][j] = grid[i][j] ? 1 : 0;
                }
            }
        }
        return opacites;
    }

    /**
     * Méthode utilisée au niveau des tests unitaires
     * @return État actuel de la grille en tableau de booléens
     */
    public boolean[][] getBooleans() {
        return grid;
    }

    /**
     * Méthode utilisée au niveau des tests unitaires
     * @param grid
     * @return this
     */
    public Grid setBooleans(boolean[][] grid) {
        this.grid = grid;
        return this;
    }

    /**
     * Méthode dumpant la grille actuelle au format texte.
     * O => cellule vivante
     * . => cellule morte
     */
    public void dump() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] ? 'O' : '.');
            }
            System.out.print("\n");
        }
    }
}