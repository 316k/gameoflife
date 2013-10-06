/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerWindows;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sun.awt.X11.AwtGraphicsConfigData;
import sun.swing.SwingAccessor;

/**
 *
 * @author Nicolas Hurtubise
 */
public class WindowNewGame extends JFrame {
    private JTextField txtLignes = new JTextField();
    private JTextField txtColonnes = new JTextField();
    private JButton confirm = new JButton();
    
    public WindowNewGame() {
        this.setTitle("New Game - Jame of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        
        
        txtLignes.setText("90");
        txtLignes.setHorizontalAlignment(JTextField.CENTER);
        txtLignes.setToolTipText("Nombre de lignes");
        
        txtColonnes.setText("90");
        txtColonnes.setHorizontalAlignment(JTextField.CENTER);
        txtColonnes.setToolTipText("Nombre de colonnes");
        
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(300, 100));
        content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));

        content.add(new JLabel("Taille de la grille : "));
        
        content.add(txtLignes);
        content.add(new JLabel(" x "));
        content.add(txtColonnes);
        
        JPanel buttons = new JPanel(new GridLayout(1, 3));
        buttons.add(new JLabel());
        buttons.add(new JLabel());
        
        confirm.setText("Débuter");
        confirm.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ControllerWindows.windowNewGameOk(10,10);
            }
        });
        buttons.add(confirm);
        
        contentPane.add(content);
        contentPane.add(buttons);
        
        this.setLocation(400, 200);
        
        this.getContentPane().add(contentPane);
        this.pack();
        this.setVisible(true);
    }
    
}
