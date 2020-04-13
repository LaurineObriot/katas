package fr.octo.craft.salle_de_sport.abonnés.domain;

public final class Abonné {

    private final AbonnéId id;
    private final String email;
    private final String prénom;
    private final Boolean estEtudiant;

    public Abonné(AbonnéId abonnéId, String email, String prénom) {
        this.id = abonnéId;
        this.email = email;
        this.prénom = prénom;
        this.estEtudiant = false;
    }

    public AbonnéId id() {
        return id;
    }

    public String email() {
        return email;
    }

    public String prénom() {
        return prénom;
    }

    public Boolean estEtudiant() {
        return estEtudiant;
    }
}
