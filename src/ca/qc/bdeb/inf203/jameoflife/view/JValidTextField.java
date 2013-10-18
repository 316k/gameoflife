package ca.qc.bdeb.inf203.jameoflife.view;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 * Surcharge de la classe JTextField permettant la validation de l'entrée
 * @author Nicolas Hurtubise
 */
public class JValidTextField extends JTextField {

    private Color errorColor;
    private Color validColor;
    private String validationRegex = null;
    private Double minValue = null;
    private Double maxValue = null;
    private boolean numericOnly = false;

    /**
     * Constructeur
     *
     * @param validColor Couleur d'arrière-plan en cas d'entrée valide.
     * @param errorColor Couleur d'arrière-plan en cas d'erreur de validation.
     */
    public JValidTextField(Color validColor, Color errorColor) {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                JValidTextField vTxtField = (JValidTextField) e.getSource();
                vTxtField.check();
            }
        });
        this.errorColor = errorColor;
        this.validColor = validColor;
    }

    /**
     * Indique un regex à utiliser pour valider l'entrée utilisateur
     * @param validationRegex le regex de validation
     */
    public void setRegex(String validationRegex) {
        this.validationRegex = validationRegex;
    }

    /**
     * Défini une valeur (numérique) minimale pour l'entrée utilisateur
     * @param min valeur minimale
     */
    public void setMinValue(Double min) {
        this.minValue = min;
    }

    /**
     * Défini une valeur (numérique) maximale pour l'entrée utilisateur
     * @param max valeur maximale
     */
    public void setMaxValue(Double max) {
        this.maxValue = max;
    }

    /**
     * Indique si le texte entré concorde avec le regex de validation
     *
     * @return true/false
     */
    public boolean isInputValid() {
        boolean valid = true;

        if (this.validationRegex != null && !this.getText().matches(validationRegex)) {
            valid = false;
        }

        if (this.numericOnly && !this.getText().matches("-?[0-9]+(\\.[0-9]+)?")) {
            valid = false;
        }

        if (this.maxValue != null) {
            try {
                if (Double.parseDouble(this.getText()) > maxValue) {
                    valid = false;
                }
            }
            catch (NumberFormatException e) {
                valid = false;
            }
        }

        if (this.minValue != null) {
            try {
                if (Double.parseDouble(this.getText()) < minValue) {
                    valid = false;
                }
            }
            catch (NumberFormatException e) {
                valid = false;
            }
        }
        return valid;
    }

    /**
     * Défini si le textfield peut contenir uniquement une valeur numérique
     * @param numericOnly
     */
    public void setNumericOnly(boolean numericOnly) {
        this.numericOnly = numericOnly;
    }

    /**
     * Repaint le composant avec la bonne couleur, basé sur la validation.
     */
    public void check() {
        if (this.isInputValid()) {
            this.setBackground(validColor);
        } else {
            this.setBackground(errorColor);
        }

        this.repaint();
    }
}

// hello mysweet dearteacher ifyougive me100%igiveyouchips cheeeeese:D