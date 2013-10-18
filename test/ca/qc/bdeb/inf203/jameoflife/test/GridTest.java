/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.jameoflife.test;

import ca.qc.bdeb.inf203.jameoflife.model.Grille;
import ca.qc.bdeb.inf203.jameoflife.model.RuleSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de unit tests pour la grille de Game of Life
 * @author k
 */
public class GridTest {

    private int x, y;
    private Grille grid;
    private RuleSet rule;

    /**
     * Premier test avec les règles de Conway
     */
    @Test
    public void conwaySimple1() {
        x = 3;
        y = 3;
        rule = new RuleSet("23/3");
        grid = new Grille(x, y, rule);

        boolean[][] originalValue = {{true, false, true}, {false, false, false}, {true, false, true}};
        grid.setBooleans(originalValue);

        Grille expectedGrid = new Grille(x, y, rule);
        boolean[][] exprectedValue = {{false, false, false}, {false, false, false}, {false, false, false}};
        expectedGrid.setBooleans(exprectedValue);

        grid.incrementGeneration(1);
        assertArrayEquals(expectedGrid.getBooleans(), grid.getBooleans());
    }

    /**
     * Second test avec les règles de Conway
     */
    @Test
    public void conwaySimple2() {
        x = 3;
        y = 3;
        rule = new RuleSet("23/3");
        grid = new Grille(x, y, rule);

        boolean[][] originalValue = {{true, false, false}, {true, true, false}, {true, false, false}};
        grid.setBooleans(originalValue);

        Grille expectedGrid1 = new Grille(x, y, rule);
        boolean[][] exprectedValue1 = {{true, true, false}, {true, true, false}, {true, true, false}};
        expectedGrid1.setBooleans(exprectedValue1);

        grid.incrementGeneration(1);
        assertArrayEquals(expectedGrid1.getBooleans(), grid.getBooleans());

        Grille expectedGrid2 = new Grille(x, y, rule);
        boolean[][] exprectedValue2 = {{true, true, false}, {false, false, true}, {true, true, false}};
        expectedGrid2.setBooleans(exprectedValue2);

        grid.incrementGeneration(1);
        assertArrayEquals(expectedGrid2.getBooleans(), grid.getBooleans());

    }

    /**
     * Test les fractions de générations
     */
    @Test
    public void generationFractionnelle() {
        x = 3;
        y = 3;
        rule = new RuleSet("23/3");
        grid = new Grille(x, y, rule);

        boolean[][] originalValue = {{true, false, false}, {true, true, false}, {true, false, false}};
        grid.setBooleans(originalValue);

        Grille originalGrid = new Grille(x, y, rule);
        originalGrid.setBooleans(originalValue);
        
        Grille expectedGrid = new Grille(x, y, rule);
        boolean[][] exprectedValue = {{true, true, false}, {true, true, false}, {true, true, false}};
        expectedGrid.setBooleans(exprectedValue);

        grid.incrementGeneration(0.5);
        assertArrayEquals(grid.getBooleans(), originalGrid.getBooleans());
        
        grid.incrementGeneration(0.5);

        assertArrayEquals(expectedGrid.getBooleans(), grid.getBooleans());
    }
    
    /**
     * Test le mode Pac-man (wrapping)
     */
    @Test
    public void pacmanMode() {
        x = 4;
        y = 4;
        rule = new RuleSet("23/3");
        grid = new Grille(x, y, rule);

        grid.setWrap(true);
        
        boolean[][] originalValue = {{false,false,false,false}, {true,false,true,false}, {true,false,false,true}, {false,true,false,false}};
        grid.setBooleans(originalValue);

        Grille expectedGrid1 = new Grille(x, y, rule);
        boolean[][] exprectedValue1 = {{false,true,false,false}, {true,true,false,false}, {true,false,true,true}, {true,false,false,false}};
        expectedGrid1.setBooleans(exprectedValue1);

        grid.incrementGeneration(1);
        assertArrayEquals(expectedGrid1.getBooleans(), grid.getBooleans());
        
        Grille expectedGrid2 = new Grille(x, y, rule);
        boolean[][] exprectedValue2 = {{false,true,false,false}, {false,false,false,false}, {false,false,true,false}, {true,false,true,false}};
        expectedGrid2.setBooleans(exprectedValue2);
        
        grid.incrementGeneration(1);
        assertArrayEquals(expectedGrid2.getBooleans(), grid.getBooleans());
    }
}