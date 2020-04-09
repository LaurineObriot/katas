import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.reverse;

public class Analyseur {

    public boolean partieNulle(Grille grille) {
        return grilleRemplie(grille) && !partieGagnée(grille);
    }

    private boolean grilleRemplie(Grille grille) {
        Jetons[][] etatGrille = grille.etat();
        boolean vide = true;

        for (Jetons[] colonne : etatGrille) {
            if (colonne[5] == null) {
                vide = false;
                break;
            }
        }

        return vide;
    }

    public boolean partieGagnée(Grille grille) {
        Jetons[][] etatGrille = grille.etat();

        boolean victoireSurColonne = victoireSurColonne(etatGrille);
        boolean victoireSurLigne = victoireSurLigne(etatGrille);
        boolean victoireSurDiagonale = victoireSurDiagonale(etatGrille);

        return victoireSurColonne || victoireSurLigne || victoireSurDiagonale;
    }

    private boolean victoireSurColonne(Jetons[][] etatGrille) {
        boolean gagné = false;
        for (Jetons[] colonne : etatGrille) {
            Jetons dernièreCouleur = null;
            int nombreJetonsConsécutifs = 0;
            for (Jetons cellule : colonne) {
                if (cellule != null) {
                    if (dernièreCouleur == cellule) {
                        nombreJetonsConsécutifs++;
                    } else {
                        dernièreCouleur = cellule;
                        nombreJetonsConsécutifs = 1;
                    }
                }

                if (nombreJetonsConsécutifs == 4) {
                    gagné = true;
                    break;
                }
            }
        }
        return gagné;
    }

    private boolean victoireSurLigne(Jetons[][] etatGrille) {
        boolean gagné = false;
        for (int indexLigne = 0; indexLigne < Grille.NOMBRE_LIGNES; indexLigne++) {
            Jetons dernièreCouleur = null;
            int nombreJetonsConsécutifs = 0;
            for (int indexColonne = 0; indexColonne < Grille.NOMBRE_COLONNES; indexColonne++) {
                Jetons cellule = etatGrille[indexColonne][indexLigne];
                if (cellule != null) {
                    if (dernièreCouleur == cellule) {
                        nombreJetonsConsécutifs++;
                    } else {
                        dernièreCouleur = cellule;
                        nombreJetonsConsécutifs = 1;
                    }
                }

                if (nombreJetonsConsécutifs == 4) {
                    gagné = true;
                    break;
                }
            }
        }
        return gagné;
    }

    private boolean victoireSurDiagonale(Jetons[][] etatGrille) {
        boolean victoireSurDiagonaleMontante = victoireSurDiagonaleMontante(etatGrille);
        boolean victoireSurDiagonaleDescendante = victoireSurDiagonaleMontante(symétrieVerticaleDeLaGrille(etatGrille));
        return victoireSurDiagonaleMontante || victoireSurDiagonaleDescendante;
    }

    private Jetons[][] symétrieVerticaleDeLaGrille(Jetons[][] etatGrille) {
        List grilleList = asList(etatGrille);
        reverse(grilleList);
        return (Jetons[][]) grilleList.toArray(new Jetons[Grille.NOMBRE_COLONNES][Grille.NOMBRE_LIGNES]);
    }

    private boolean victoireSurDiagonaleMontante(Jetons[][] etatGrille) {
        boolean gagné = false;
        for (int supplémentLigne = 0; supplémentLigne < 3; supplémentLigne++) {
            Jetons dernièreCouleur = null;
            int nombreJetonsConsécutifs = 0;
            for (int indexColonne = 0; indexColonne < Grille.NOMBRE_LIGNES - supplémentLigne; indexColonne++) {
                Jetons cellule = etatGrille[indexColonne][indexColonne + supplémentLigne];
                if (cellule != null) {
                    if (dernièreCouleur == cellule) {
                        nombreJetonsConsécutifs++;
                    } else {
                        dernièreCouleur = cellule;
                        nombreJetonsConsécutifs = 1;
                    }
                }

                if (nombreJetonsConsécutifs == 4) {
                    gagné = true;
                    break;
                }
            }
        }
        for (int supplémentLigne = 1; supplémentLigne < 4; supplémentLigne++) {
            Jetons dernièreCouleur = null;
            int nombreJetonsConsécutifs = 0;
            for (int indexColonne = supplémentLigne; indexColonne < Grille.NOMBRE_COLONNES; indexColonne++) {
                Jetons cellule = etatGrille[indexColonne][indexColonne - supplémentLigne];
                if (cellule != null) {
                    if (dernièreCouleur == cellule) {
                        nombreJetonsConsécutifs++;
                    } else {
                        dernièreCouleur = cellule;
                        nombreJetonsConsécutifs = 1;
                    }
                }

                if (nombreJetonsConsécutifs == 4) {
                    gagné = true;
                    break;
                }
            }
        }
        return gagné;
    }
}
