package ca.qc.bdeb.inf203.jameoflife.view;

import java.awt.GridLayout;
import javax.swing.*;

/**
 * 
 * @author Nicolas Hurtubise
 */
public class MainWindow extends JFrame {

    public MainWindow() {
        this.setTitle("Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 2));
        this.add(new JGameOfLifePanel(20,10));
        this.add(new JSettingsPanel());
        this.pack();
        this.setVisible(true);
    }

}
