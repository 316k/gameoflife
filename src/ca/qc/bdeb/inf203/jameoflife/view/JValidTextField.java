/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.jameoflife.view;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
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
     * @param validationRegex Regex qui sera utilisé pour déterminer si le
     * composant contient un texte valide.
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

    public void setRegex(String validationRegex) {
        this.validationRegex = validationRegex;
    }

    public void setMinValue(Double min) {
        this.minValue = min;
    }

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
