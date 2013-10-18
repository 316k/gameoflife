package ca.qc.bdeb.inf203.jameoflife.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JButton;

/**
 * Cellule de Game of Life (surcharge d'un JButton)
 * @author Nicolas Hurtubise
 */
public class JCellule extends JButton {

    private int x = 0, y = 0;
    private double opacite = 0.0;
    private int r, v, b;
    private boolean partyMode = false;

    /**
     * Crée une cellule
     * @param x la position en x de la grille
     * @param y la position en y de la grille
     */
    public JCellule(int x, int y) {
        this.x = x;
        this.y = y;
        r = 255;
        v = 255;
        b = 255;
        this.setBorder(null);
    }

    /**
     * @param partyMode si le party mode est activé pour la cellule
     */
    public void setParty(boolean partyMode) {
        this.partyMode = partyMode;
    }

    /**
     * @return l'opacité actuelle de la cellule
     */
    public double getOpacite() {
        return opacite;
    }

    /**
     * @param opacite l'opacité de la cellule
     */
    public void setOpacite(double opacite) {
        // Swap les couleurs
        Random rnd = new Random();
        if (partyMode) {
            do {
                r = rnd.nextBoolean() ? 255 : 0;
                v = rnd.nextBoolean() ? 255 : 0;
                b = rnd.nextBoolean() ? 255 : 0;
            } while (r == v && r == b);
        } else {
            r = 255;
            v = 255;
            b = 255;
        }
        this.opacite = opacite;
    }

    /**
     * @return la coordonnée X de la cellule
     */
    public int getCoordonneeX() {
        return x;
    }

    /**
     *
     * @return la coordonnée Y de la cellule
     */
    public int getCoordonneeY() {
        return y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color bg = new Color((int) (opacite * r), (int) (opacite * v), (int) (opacite * b));
        g.setColor(bg);
        g.fillOval(0, 0, this.getSize().width, this.getSize().height);

    }
}
