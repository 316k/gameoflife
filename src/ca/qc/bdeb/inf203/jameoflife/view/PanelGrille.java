package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerGrille;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * Panel contenant la grille de game of life
 * @author Nicolas Hurtubise
 */
public class PanelGrille extends JPanel {

    private JCellule[][] cellules;

    /**
     * Crée la grille du game of life
     * @param largeur de la grille
     * @param hauteur de la grillle
     */
    public PanelGrille(int largeur, int hauteur) {
        this.setBackground(Color.black);

        cellules = new JCellule[largeur][hauteur];

        this.setLayout(new GridLayout(largeur, hauteur));

        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                cellules[i][j] = new JCellule(i, j);
                cellules[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JCellule cellule = (JCellule) e.getSource();
                        ControllerGrille.flipCell(((PanelGrille) cellule.getParent()).getGrid(), cellule.getCoordonneeX(), cellule.getCoordonneeY(), getPanelOptions().getLblGeneration(), getPanelOptions().getBtnProchaineGeneration());
                    }
                });

                cellules[i][j].setVisible(true);

                this.add(cellules[i][j]);
            }
        }

        this.setVisible(true);
    }

    /**
     * @return la grille de JCellules
     */
    public JCellule[][] getGrid() {
        return cellules;
    }

    /**
     * @return un accès au PanelOptions
     */
    public PanelOptions getPanelOptions() {
        WindowPrincipale window = (WindowPrincipale) this.getParent().getParent().getParent().getParent().getParent();
        return window.getPanelSettings();
    }
}
