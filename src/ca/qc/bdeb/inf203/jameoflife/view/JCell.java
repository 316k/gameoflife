package ca.qc.bdeb.inf203.jameoflife.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author Nicolas Hurtubise
 */
public class JCell extends JButton {

    private int x = 0, y = 0;
    private double opacite = 0.0;

    public double getOpacite() {
        return opacite;
    }

    public void setOpacite(double opacite) {
        this.opacite = opacite;
    }

    public JCell(int x, int y) {
        this.x = x;
        this.y = y;
        this.setBorder(null);
    }

    public int getCoordonneeX() {
        return x;
    }

    public int getCoordonneeY() {
        return y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int r = (System.currentTimeMillis() % 3000) < 1000 ? (int) (opacite*255) : 0;
        int v = (System.currentTimeMillis() % 3000) >= 1000 && (System.currentTimeMillis() % 3000) < 2000 ? (int) (opacite*255) : 0;
        int b = (System.currentTimeMillis() % 3000) >= 2000 && (System.currentTimeMillis() % 3000) < 3000 ? (int) (opacite*255) : 0;
        
        Color bg = new Color(r, v, b);
        g.setColor(bg);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
    }
}
