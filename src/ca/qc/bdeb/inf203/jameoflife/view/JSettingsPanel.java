/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.inf203.jameoflife.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas Hurtubise
 */
public class JSettingsPanel extends JPanel {
    
    JButton[][] cellules;
    
    public JSettingsPanel() {
        int x=2, y=2;
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
