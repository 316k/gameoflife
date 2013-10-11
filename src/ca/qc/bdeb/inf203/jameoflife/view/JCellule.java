package ca.qc.bdeb.inf203.jameoflife.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author Nicolas Hurtubise
 */
public class JCellule extends JButton {

    private int x = 0, y = 0;
    private double opacite = 0.0;
    private int r, v, b;
    private boolean partyMode = false;

    public JCellule(int x, int y) {
        this.x = x;
        this.y = y;
        r = 255;
        v = 255;
        b = 255;
        this.setBorder(null);
    }

    public void setParty(boolean partyMode) {
        this.partyMode = partyMode;
    }

    public double getOpacite() {
        return opacite;
    }

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

    public int getCoordonneeX() {
        return x;
    }

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
