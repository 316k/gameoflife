package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerGrille;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas Hurtubise
 */
public class PanelGrille extends JPanel {

    private JCellule[][] cellules;

    public PanelGrille(int width, int height) {
        this.setBackground(Color.black);

        cellules = new JCellule[width][height];

        this.setLayout(new GridLayout(width, height));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cellules[i][j] = new JCellule(i, j);
                cellules[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JCellule cellule = (JCellule) e.getSource();
                        ControllerGrille.flipCell(((PanelGrille) cellule.getParent()).getGrid(), cellule.getCoordonneeX(), cellule.getCoordonneeY(), getPanelSettings().getLblGeneration(), getPanelSettings().getBtnProchaineGeneration());
                    }
                });

                cellules[i][j].setVisible(true);

                this.add(cellules[i][j]);
            }
        }

        this.setVisible(true);
    }

    public JCellule[][] getGrid() {
        return this.cellules;
    }

    public PanelOptions getPanelSettings() {
        WindowPrincipale window = (WindowPrincipale) this.getParent().getParent().getParent().getParent().getParent();
        return window.getPanelSettings();
    }
}
