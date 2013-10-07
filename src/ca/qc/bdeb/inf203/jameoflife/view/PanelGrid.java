package ca.qc.bdeb.inf203.jameoflife.view;

import ca.qc.bdeb.inf203.jameoflife.controller.ControllerGrid;
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
public class PanelGrid extends JPanel {

    private JCell[][] cellules;

    public PanelGrid(int width, int height) {
        this.setBackground(Color.black);

        cellules = new JCell[width][height];

        this.setLayout(new GridLayout(width, height));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cellules[i][j] = new JCell(i, j);
                cellules[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JCell cellule = (JCell) e.getSource();
                        ControllerGrid.flipCell(((PanelGrid) cellule.getParent()).getGrid(), cellule.getCoordonneeX(), cellule.getCoordonneeY(), getPanelSettings().getLblGeneration(), getPanelSettings().getBtnProchaineGeneration());
                    }
                });

                cellules[i][j].setVisible(true);

                this.add(cellules[i][j]);
            }
        }

        this.setVisible(true);
    }

    public JCell[][] getGrid() {
        return this.cellules;
    }

    public PanelSettings getPanelSettings() {
        WindowMain window = (WindowMain) this.getParent().getParent().getParent().getParent().getParent();
        return window.getPanelSettings();
    }
}
