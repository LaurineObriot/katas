package fr.octo.craft.salle_de_sport.abonnements.domain;

final class Réduction {

    private Double taux = 0.0;

    Réduction(Double taux) {
        this.taux = taux;
    }

    Réduction(final Boolean formuleChoisieEstALannée, final Boolean estEtudiant) {
        if (estEtudiant) {
            taux += 0.2;
        }

        if (formuleChoisieEstALannée) {
            taux += 0.3;
        }
    }

    Double taux() {
        return taux;
    }
}
