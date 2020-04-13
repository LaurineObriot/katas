package fr.octo.craft.salle_de_sport.abonnements.domain;

import java.util.ArrayList;
import java.util.List;

public final class Abonnement {

    private final AbonnementId id;
    private final FormuleChoisie formuleChoisie;
    final String prix;
    private final List<Période> périodes = new ArrayList<>();

    public Abonnement(AbonnementId abonnementId, Boolean estEtudiant, String description, Integer prixDeBase, Integer duréeEnMois, DateCustom dateDeDébut) {

        this.id = abonnementId;

        this.formuleChoisie = new FormuleChoisie(description, prixDeBase, duréeEnMois);

        var réduction = new Réduction(formuleChoisie.estALannée(), estEtudiant);
        this.prix = formuleChoisie.prixDeBase().aprèsRéduction(réduction).toString();

        this.périodes.add(
            new Période(dateDeDébut, formuleChoisie.duréeEnMois())
        );
    }

    public AbonnementId id() {
        return id;
    }

    public String descriptionFormuleChoisie() {
        return formuleChoisie.descriptionFormule;
    }

    public Double restantDu() {
        return Double.valueOf(prix);
    }

    private Période dernièrePériode() {
        return périodes.get(périodes.size() - 1);
    }

    public Boolean estEnCours(final DateCustom date) {
        return dernièrePériode().contient(date);
    }

    public Boolean seraFini(final DateCustom àPartirDe) {
        return dernièrePériode().avantLaDate(àPartirDe);
    }

    public void renouveller() {
        périodes.add(
            dernièrePériode().suivante()
        );
    }

    public Boolean anniversaireDes3ansLe(final DateCustom date) {
        return date.moinsXMois(3 * 12).mêmeQue(périodes.get(0).dateDeDébut);
    }
}
