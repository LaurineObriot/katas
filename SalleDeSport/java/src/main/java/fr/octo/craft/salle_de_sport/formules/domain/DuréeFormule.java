package fr.octo.craft.salle_de_sport.formules.domain;

public enum DuréeFormule {
    MOIS(1),
    ANNEE(12);

    private final Integer nbMois;

    DuréeFormule(Integer nbMois) {
        this.nbMois = nbMois;
    }

    public Integer nbMois() {
        return nbMois;
    }
}
