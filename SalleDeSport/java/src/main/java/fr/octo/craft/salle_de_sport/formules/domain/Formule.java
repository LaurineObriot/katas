package fr.octo.craft.salle_de_sport.formules.domain;

public final class Formule {

    private final FormuleId formuleId;
    private Integer prixDeBase;
    private final Integer duréeEnMois;

    public Formule(FormuleId formuleId, Integer prixDeBase, DuréeFormule duréeEnMois) {
        this.formuleId = formuleId;
        this.prixDeBase = prixDeBase;
        this.duréeEnMois = duréeEnMois.nbMois();
    }

    public FormuleId id() {
        return formuleId;
    }

    public Integer prixDeBase() {
        return prixDeBase;
    }

    public void changeDePrix(final Integer nouveauPrix) {
        prixDeBase = nouveauPrix;
    }

    public Integer duréeEnMois() {
        return duréeEnMois;
    }

    Boolean estALannée() {
        return DuréeFormule.ANNEE.nbMois().equals(duréeEnMois);
    }

    public String description() {
        return "Formule "+duréeEnMois+" mois à "+prixDeBase+" euros";
    }
}
