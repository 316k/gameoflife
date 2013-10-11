/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerFenetres;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Nicolas Hurtubise
 */
public class WindowNouvellePartie extends JFrame {

    private JValidTextField vTxtLignes = new JValidTextField(Color.white, Color.pink);
    private JValidTextField vTxtColonnes = new JValidTextField(Color.white, Color.pink);
    private JButton btnConfirm = new JButton();

    public WindowNouvellePartie() {
        this.setTitle("Nouvelle Partie - Jame of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));


        vTxtLignes.setText("20");
        vTxtLignes.setHorizontalAlignment(JTextField.CENTER);
        vTxtLignes.setToolTipText("Nombre de lignes");
        vTxtLignes.setMaxValue(100.0);
        vTxtLignes.setMinValue(1.0);
        vTxtLignes.setRegex("[0-9]+");
        vTxtLignes.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                check();
            }
        });


        vTxtColonnes.setText("20");
        vTxtColonnes.setHorizontalAlignment(JTextField.CENTER);
        vTxtColonnes.setToolTipText("Nombre de colonnes");
        vTxtColonnes.setMaxValue(100.0);
        vTxtColonnes.setMinValue(1.0);
        vTxtColonnes.setRegex("[0-9]+");
        vTxtColonnes.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                check();
            }
        });
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(300, 100));
        content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));

        content.add(new JLabel("Taille de la grille : "));

        content.add(vTxtLignes);
        content.add(new JLabel(" x "));
        content.add(vTxtColonnes);

        JPanel buttons = new JPanel(new GridLayout(1, 3));
        buttons.add(new JLabel());
        buttons.add(new JLabel());

        btnConfirm.setText("Débuter");
        btnConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControllerFenetres.fenetreNouvellePartieOk(getLignes(), getColonnes());
            }
        });
        buttons.add(btnConfirm);

        contentPane.add(content);
        contentPane.add(buttons);

        this.getContentPane().add(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void check() {
        btnConfirm.setEnabled(vTxtColonnes.isInputValid() && vTxtLignes.isInputValid());
    }

    public int getLignes() {
        return Integer.parseInt(vTxtLignes.getText());
    }

    public int getColonnes() {
        return Integer.parseInt(vTxtColonnes.getText());
    }
}
