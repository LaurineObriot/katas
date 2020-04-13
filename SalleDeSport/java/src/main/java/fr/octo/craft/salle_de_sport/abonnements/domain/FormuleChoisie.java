package fr.octo.craft.salle_de_sport.abonnements.domain;

import fr.octo.craft.salle_de_sport.formules.domain.DuréeFormule;

public final class FormuleChoisie {

    final String descriptionFormule;
    private final Prix prixDeBase;
    private final Integer duréeEnMois;

    public FormuleChoisie(final String descriptionFormule, final Integer prixDeBase, final Integer duréeEnMois) {
        this.descriptionFormule = descriptionFormule;
        this.prixDeBase = new Prix(prixDeBase);
        this.duréeEnMois = duréeEnMois;
    }

    Boolean estALannée() {
        return DuréeFormule.ANNEE.nbMois().equals(duréeEnMois);
    }

    Prix prixDeBase() {
        return prixDeBase;
    }

    Integer duréeEnMois() {
        return duréeEnMois;
    }
}
