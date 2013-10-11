/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerGrille;
import ca.qc.bdeb.inf203.jameoflife.controller.ControllerFenetres;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Nicolas Hurtubise
 */
public class PanelOptions extends JPanel {

    private JButton btnRandomize, btnProchaineGeneration, btnAide;
    private JValidTextField vTxtRule, vTxtGeneration;
    private JLabel lblGeneration;
    private JComboBox cmbAlgorithme;
    private JCheckBox chkPacManMode, chkPartyMode;

    public PanelOptions() {

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        lblGeneration = new JLabel("Génération #0,00");
        content.add(lblGeneration);
        content.add(new JLabel());
        content.add(new JLabel("Algorithme utilisé"));
        cmbAlgorithme = new JComboBox();
        cmbAlgorithme.addItem("Conway");
        cmbAlgorithme.addItem("High Life");
        cmbAlgorithme.addItem("Random");
        cmbAlgorithme.addItem("Personnalisé");
        cmbAlgorithme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerGrille.setRules(getRule(), getGrid(), lblGeneration, btnProchaineGeneration);
                vTxtRule.setEnabled(cmbAlgorithme.getSelectedIndex() == 3);
            }
        });
        content.add(cmbAlgorithme);

        vTxtRule = new JValidTextField(Color.white, Color.pink);
        vTxtRule.setText("23/3");
        vTxtRule.setRegex("[0-8]{0,9}/[0-8]{0,9}");
        vTxtRule.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnProchaineGeneration.setEnabled(isInputValid());
                if (vTxtRule.isInputValid()) {
                    ControllerGrille.setRules(getRule(), getGrid(), lblGeneration, btnProchaineGeneration);
                }
            }
        });
        vTxtRule.setEnabled(false);
        content.add(vTxtRule);

        content.add(new JLabel());

        chkPacManMode = new JCheckBox("Pac-man mode");
        chkPacManMode.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ControllerGrille.setWrap(chkPacManMode.isSelected(), getGrid(), lblGeneration, btnProchaineGeneration);
            }
        });
        content.add(chkPacManMode);

        chkPartyMode = new JCheckBox("Party mode");
        chkPartyMode.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JCellule[][] grid = getGrid();
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        grid[i][j].setParty(((JCheckBox) e.getSource()).isSelected());
                    }
                }
                ControllerGrille.synchroniser(getGrid(), lblGeneration, btnProchaineGeneration);
            }
        });
        content.add(chkPartyMode);

        content.add(new JLabel());

        content.add(new JLabel("Bonds de :"));
        vTxtGeneration = new JValidTextField(Color.white, Color.pink);
        vTxtGeneration.setMaxValue(2000.0);
        vTxtGeneration.setMinValue(0.0);
        vTxtGeneration.setNumericOnly(true);
        vTxtGeneration.setText("0.5");
        vTxtGeneration.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                btnProchaineGeneration.setEnabled(isInputValid());
            }
        });
        content.add(vTxtGeneration);
        content.add(new JLabel("générations"));

        content.add(new JLabel(" "));
        content.setName("content");

        btnRandomize = new JButton("Randomizer");
        btnRandomize.setName("rnd");
        btnRandomize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerGrille.randomize(getGrid(), lblGeneration, btnProchaineGeneration);
            }
        });

        content.add(btnRandomize);

        content.add(new JLabel(" "));

        btnProchaineGeneration = new JButton("Prochaine Génération");
        btnProchaineGeneration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerGrille.prochaineGeneration(getGrid(), getSautDeGeneration(), lblGeneration, btnProchaineGeneration);
            }
        });

        btnProchaineGeneration.setBackground(Color.pink);
        content.add(btnProchaineGeneration);

        content.add(new JLabel(" "));

        btnAide = new JButton("Aide");
        btnAide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerFenetres.apropos();
            }
        });
        content.add(btnAide);

        this.add(content);

        this.setVisible(true);
    }

    public JButton getBtnProchaineGeneration() {
        return btnProchaineGeneration;
    }

    public JLabel getLblGeneration() {
        return lblGeneration;
    }

    private double getSautDeGeneration() {
        return Double.parseDouble(vTxtGeneration.getText());
    }

    private String getRule() {
        String rule = "";
        switch (cmbAlgorithme.getSelectedIndex()) {
            case 0:
                rule = "23/3";
                break;

            case 1:
                rule = "23/36";
                break;

            case 2: // Random
                Random rnd = new Random();
                String survive = "";
                // Survive
                for (int i = 0; i < rnd.nextInt(9); i++) {
                    survive += rnd.nextInt(9);
                }

                String born = "";

                for (int i = 0; i < rnd.nextInt(9); i++) {
                    born += rnd.nextInt(9);
                }

                rule = survive + "/" + born;
                System.out.println(rule);
                break;

            case 3:
                rule = vTxtRule.getText();
        }

        return rule;
    }

    public boolean isInputValid() {
        boolean valid = vTxtGeneration.isInputValid();
        if (vTxtRule.isEnabled() && !vTxtRule.isInputValid()) {
            valid = false;
        }
        return valid;
    }

    public JCellule[][] getGrid() {
        WindowPrincipale window = (WindowPrincipale) this.getParent().getParent().getParent().getParent().getParent();

        return window.getPanelGrid().getGrid();
    }
}
