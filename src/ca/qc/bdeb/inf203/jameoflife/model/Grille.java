package ca.qc.bdeb.inf203.jameoflife.model;

import java.util.Random;

/**
 * Une grille de Game of Life
 *
 * @author Nicolas Hurtubise
 */
public class Grille {

    private RuleSet ruleSet;
    private boolean grid[][];
    private double generation = 0;
    private boolean wrap = false;

    /**
     * Constructeur
     *
     * @param longueur
     * @param hauteur
     * @param ruleSet
     */
    public Grille(int longueur, int hauteur, RuleSet ruleSet) {
        grid = new boolean[longueur][hauteur];
        this.ruleSet = ruleSet;
    }

    /**
     * Défini si le wrapping est activé (mode Pac-man)
     *
     * @param wrap
     */
    public void setWrap(boolean wrap) {
        this.wrap = wrap;
    }

    /**
     * Définit arbitrairement la valeur d'une cellule
     *
     * @param x
     * @param y
     * @param actif
     * @return this
     */
    public Grille setCell(int x, int y, boolean actif) {
        grid[x][y] = actif;
        return this;
    }

    /**
     * @param x
     * @param y
     * @return La valeur de la cellule en (x, y)
     */
    public boolean getCell(int x, int y) {
        return grid[x][y];
    }

    /**
     * Inverse la valeur à la position (x, y)
     * @param x
     * @param y
     * @return this
     */
    public Grille flipCell(int x, int y) {
        grid[x][y] = !grid[x][y];
        return this;
    }

    /**
     * Donne une valeur aléatoire à chaque cellule de la grille
     * @return this
     */
    public Grille randomize() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Random().nextBoolean(); // Ça devrait être une méthode statique
            }
        }
        return this;
    }

    /**
     * @return les règles dirigeant les actions de la grille
     */
    public RuleSet getRuleSet() {
        return ruleSet;
    }

    /**
     * Définit les règles dirigeant les actions de la grille
     * @param ruleSet règles
     * @return this
     */
    public Grille setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
        return this;
    }

    /**
     * Définit les règles dirigeant les actions de la grille
     * @param ruleSet règles en String
     * @return this
     * @throws IllegalArgumentException si le format de règles n'est pas standard
     */
    public Grille setRuleSet(String ruleSet) throws IllegalArgumentException {
        this.ruleSet = new RuleSet(ruleSet);
        return this;
    }

    /**
     * Incrémente la génération de la grille
     * @param nbrGenerations nombre de générations duquel augmenter 
     * @return this
     */
    public Grille incrementGeneration(double nbrGenerations) {
        while (nbrGenerations >= 1) {
            generation += 1.0;
            nbrGenerations -= 1.0;
            grid = nextGeneration();
        }

        if (generation + nbrGenerations >= Math.floor(generation) + 1) {
            // Une dernière fois
            grid = nextGeneration();
        }

        generation += nbrGenerations;
        return this;
    }

    /**
     * @return la génération actuelle
     */
    public double getGeneration() {
        return generation;
    }

    private boolean[][] nextGeneration() {
        boolean[][] futureGrid = new boolean[grid.length][grid[0].length];

        boolean[][] context = new boolean[3][3];

        int x, y;
        boolean value;

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

    /**
     * @return un tableau de contenant les opacités des cellules
     */
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
     *
     * @return État actuel de la grille en tableau de booléens
     */
    public boolean[][] getBooleans() {
        return grid;
    }

    /**
     * Méthode utilisée au niveau des tests unitaires
     *
     * @param grid
     * @return this
     */
    public Grille setBooleans(boolean[][] grid) {
        this.grid = grid;
        return this;
    }

    /**
     * @return si la grille est stable (si les cellules resteront à jamais dans
     * le même état).
     */
    public boolean isStable() {
        boolean stable = true;
        boolean[][] nextGrid = this.nextGeneration();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                stable = stable && (grid[i][j] == nextGrid[i][j]);
            }
        }
        return stable;
    }

    /**
     * @return la grille actuelle au format String: O => cellule vivante
     * . => cellule morte
     */
    public String dump() {
        String dump = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                dump += grid[i][j] ? 'O' : '.';
            }
            dump += "\n";
        }
        return dump;
    }
}