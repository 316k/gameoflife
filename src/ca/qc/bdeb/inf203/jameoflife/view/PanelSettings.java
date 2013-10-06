/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerGrid;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Nicolas Hurtubise
 */
public class PanelSettings extends JPanel {

    private JButton btnRandomize, btnProchaineGeneration;
    private JValidTextField vTxtRule, vTxtGeneration;

    public PanelSettings() {
        
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(new JLabel("Génération #0"));
        content.add(new JLabel());
        content.add(new JLabel("Algorithme"));
        content.add(new JLabel("  * Conway"));
        content.add(new JLabel("  * High Life"));
        content.add(new JLabel("  * Random"));
        content.add(new JLabel("  * Custom"));
        vTxtRule = new JValidTextField(Color.white, Color.pink);
        vTxtRule.setRegex("[0-8]{0,9}/[0-8]{0,9}");
        
        vTxtRule.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                JValidTextField vTxtField = (JValidTextField) e.getSource();
                btnProchaineGeneration.setEnabled(vTxtField.isInputValid());
                
            }
        });
        content.add(vTxtRule);

        content.add(new JLabel());

        content.add(new JCheckBox("Pac-man mode"));
        content.add(new JCheckBox("Cage mode"));

        content.add(new JLabel());

        content.add(new JLabel("Bonds de :"));
        vTxtGeneration = new JValidTextField(Color.white, Color.pink);
        vTxtGeneration.setMaxValue(2000.0);
        vTxtGeneration.setMinValue(0.0);
        vTxtGeneration.setNumericOnly(true);
        content.add(vTxtGeneration);
        content.add(new JLabel("générations"));

        content.add(new JLabel(" "));
        content.setName("content");
        
        btnRandomize = new JButton("Randomizer");
        btnRandomize.setName("rnd");
        btnRandomize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                WindowMain window = (WindowMain) btn.getParent().getParent().getParent().getParent().getParent().getParent();
                
                JCell[][] grid = window.getPanelGrid().getGrid();
                ControllerGrid.randomize(grid);
            }
        });

        content.add(btnRandomize);

        content.add(new JLabel(" "));

        btnProchaineGeneration = new JButton("Prochaine génération");
        btnProchaineGeneration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                
                WindowMain window = (WindowMain) btn.getParent().getParent().getParent().getParent().getParent().getParent();
                
                Container c = btn.getParent();
                for (int i = 1; c != null; i++) {
                    System.out.println(i + "th parent : " + c.getName());
                    c = c.getParent();
                }
                double nbrGeneration = ((PanelSettings) btn.getParent().getParent()).getGeneration();
                JCell[][] grid = window.getPanelGrid().getGrid();
                ControllerGrid.prochaineGeneration(grid, nbrGeneration);
            }
        });

        content.add(btnProchaineGeneration);

        content.add(new JLabel(" "));

        content.add(new JButton("Aide"));

        this.add(content);

        this.setVisible(true);
    }

    private double getGeneration() {
        System.out.println(vTxtGeneration.getText());
        return Double.parseDouble(vTxtGeneration.getText());
    }
}
