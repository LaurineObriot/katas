import java.util.List;

import static java.util.Arrays.asList;

public class Grille {

    public static final int NOMBRE_LIGNES = 6;
    public static final int NOMBRE_COLONNES = 7;
    private static final Object CELLULE_VIDE = null;
    private final Jetons[][] etat;

    public Grille() {
        etat = new Jetons[NOMBRE_COLONNES][NOMBRE_LIGNES];
    }

    public Jetons[][] etat() {
        return etat;
    }

    public void ajouterJeton(int numéroColonne, Jetons jeton) throws ColonnePleineException, ColonneInexistante {
        if (numéroColonne > NOMBRE_COLONNES || numéroColonne <= 0) {
            throw new ColonneInexistante();
        }

        int indexColonne = numéroColonne - 1;
        List colonne = asList(etat[indexColonne]);

        int premièreCelluleVide = colonne.indexOf(CELLULE_VIDE);

        if (premièreCelluleVide == -1) {
            throw new ColonnePleineException();
        }

        etat[indexColonne][premièreCelluleVide] = jeton;
    }

    public void vider() {
        for (int colonneIndex = 0; colonneIndex < NOMBRE_COLONNES; colonneIndex++) {
            for (int ligneIndex = 0; ligneIndex < NOMBRE_LIGNES; ligneIndex++) {
                etat[colonneIndex][ligneIndex] = null;
            }
        }
    }

    public String afficher() {
        StringBuilder affichageBuilder = new StringBuilder();
        for (int indexLigne = NOMBRE_LIGNES - 1; indexLigne >= 0; indexLigne--) {
            for (int indexColonne = 0; indexColonne < NOMBRE_COLONNES; indexColonne++) {
                Jetons cellule = etat[indexColonne][indexLigne];
                if (cellule == null) {
                    affichageBuilder.append(".");
                } else {
                    affichageBuilder.append(cellule.getReprésentation());
                }
                affichageBuilder.append(" ");
            }
            affichageBuilder.replace(affichageBuilder.length() - 1, affichageBuilder.length(), "\n");
        }

        affichageBuilder.delete(affichageBuilder.length() - 1, affichageBuilder.length());

        return affichageBuilder.toString();
    }
}
