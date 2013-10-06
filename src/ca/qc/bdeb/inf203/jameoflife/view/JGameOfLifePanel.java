package ca.qc.bdeb.inf203.jameoflife.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas Hurtubise
 */
public class JGameOfLifePanel extends JPanel {

    JButton[][] cellules;

    public JGameOfLifePanel(int x, int y) {
        cellules = new JButton[x][y];
        
        this.setLayout(new GridLayout(x, y));
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cellules[i][j] = new JButton("i" + i);
                cellules[i][j].setVisible(true);
                this.add(cellules[i][j]);
            }
        }
        
        this.setVisible(true);
    }
}
