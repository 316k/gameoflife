package ca.qc.bdeb.inf203.jameoflife.model;

/**
 *
 * @author Nicolas Hurtubise
 */
public class RuleSet {

    private String rule;
    private int[] survive = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    private int[] born = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    /**
     * Constructeur
     *
     * @param rule au format "Survive/Born". Par exemple, Conway s'exprime de la
     * façon suivante : 23/3
     * @throws IllegalArgumentException
     */
    public RuleSet(String rule) throws IllegalArgumentException {
        if (!rule.matches("[0-8]{0,9}/[0-8]{0,9}")) {
            throw new IllegalArgumentException("Bad RuleSet string format.");
        }

        this.rule = rule;
        String survive = rule.substring(0, rule.indexOf("/"));
        String born = rule.substring(rule.indexOf("/") + 1);
        for (int i = 0; i < survive.length(); i++) {
            this.survive[i] = Integer.parseInt(survive.substring(i, i + 1));
        }

        for (int i = 0; i < born.length(); i++) {
            this.born[i] = Integer.parseInt(born.substring(i, i + 1));
        }
    }

    /**
     * Donne la valeur à la prochaine génération de la cellule au centre du
     * context passé en argument.
     *
     * @param context fragment de grille (tableau 3x3) contenant l'état des
     * cellules au format booléen
     * @return l'état futur de la cellule au centre
     */
    public boolean getNextValue(boolean context[][]) {
        int voisins = 0;
        boolean nextValue = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(i == 1 && j == 1)) {
                    voisins += context[i][j] ? 1 : 0;
                }
            }
        }

        if (context[1][1]) {
            for (int i = 0; !nextValue && i < survive.length; i++) {
                if (survive[i] == voisins) {
                    nextValue = true;
                }
            }
        } else {
            for (int i = 0; !nextValue && i < born.length; i++) {
                if (born[i] == voisins) {
                    nextValue = true;
                }
            }
        }

        return nextValue;
    }
}
