package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerFenetres;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Fenêtre de création de partie
 * @author Nicolas Hurtubise
 */
public class WindowNouvellePartie extends JFrame {

    private JValidTextField vTxtLignes = new JValidTextField(Color.white, Color.pink);
    private JValidTextField vTxtColonnes = new JValidTextField(Color.white, Color.pink);
    private JButton btnConfirm = new JButton();

    /**
     * Constructeur
     */
    public WindowNouvellePartie() {
        this.setTitle("Nouvelle Partie - Jame of Life");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        content.add(new JLabel(" "));
        content.add(new JLabel("Taille de la grille : "));

        content.add(vTxtLignes);
        content.add(new JLabel(" x "));
        content.add(vTxtColonnes);
        content.add(new JLabel(" "));

        JPanel buttons = new JPanel(new GridLayout(1, 3));
        buttons.add(new JLabel());
        buttons.add(new JLabel());

        btnConfirm.setText("Débuter");
        btnConfirm.setMargin(new Insets(5, 15, 5, 15));
        btnConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ControllerFenetres.confirmationNouvellePartie(getLignes(), getColonnes());
            }
        });
        buttons.add(btnConfirm);

        contentPane.add(content);
        contentPane.add(buttons);

        this.getContentPane().add(contentPane);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ControllerFenetres.fermeture();
            }
        });


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Enable ou disable le bouton de confirmation, selon si les champs d'entrée
     * sont valides
     */
    public void check() {
        btnConfirm.setEnabled(vTxtColonnes.isInputValid() && vTxtLignes.isInputValid());
    }

    /**
     * @return le nombre de lignes dans la grille de Game of Life
     */
    public int getLignes() {
        return Integer.parseInt(vTxtLignes.getText());
    }

    /**
     * @return le nombre de colonnes dans la grille de Game of Life
     */
    public int getColonnes() {
        return Integer.parseInt(vTxtColonnes.getText());
    }
}
