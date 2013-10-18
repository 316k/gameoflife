package ca.qc.bdeb.inf203.jameoflife.view;

import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JEditorPane;
import javax.swing.JFrame;

/**
 * Fenêtre "À propos"
 * @author Nicolas Hurtubise
 */
public class WindowApropos extends JFrame {

    /**
     * Constructeur
     */
    public WindowApropos() {
        this.setTitle("À propos - Jame of life");
        JEditorPane txtHelp = new JEditorPane("text/html", "");
        txtHelp.setText("<h1>Jame of Life</h1>"
                + "<h2>À propos</h2>"
                + "<p>Jame of Life est une implémentation en Java de <i>Conway's Game of Life</i>.</p>"
                + "<h2>Fonctionnalités</h2>"
                + "<ul>"
                + "<li>Possibilité de choisir parmi les algorithmes suivants :"
                + "<ul>"
                + "<li>Conway</li>"
                + "<li>High Life</li>"
                + "<li>Règle aléatoire</li>"
                + "<li>Règle personnalisée</li>"
                + "</ul>"
                + "<p>Notez que les algorithmes personnalisés doivent être donnés sous la forme <b>S</b><i>urvive</i>/<b>B</b><i>orn</i> (e.g. 23/3 pour le <i>Conway's Game of Life</i>)</p>"
                + "</li>"
                + "<li>Possibilité d'activer/désactiver le mode Pac-Man (wrap sur les côtés);</li>"
                + "<li>Possibilité de remplir la grille aléatoirement;</li>"
                + "<li>Merveilleuse fenêtre d'aide en Heurteumeuleu.</li>"
                + "</ul>"
                + "<h2>Licence</h2>"
                + "<p>Ce programme est disponible sous les termes de la licence GNU GPL v3. Lisez le fichier LICENSE pour plus de détails.</p>");
        txtHelp.setEditable(false);
        txtHelp.setMargin(new Insets(10, 10, 10, 10));
        txtHelp.setPreferredSize(new Dimension(450, 550));
        this.add(txtHelp);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
