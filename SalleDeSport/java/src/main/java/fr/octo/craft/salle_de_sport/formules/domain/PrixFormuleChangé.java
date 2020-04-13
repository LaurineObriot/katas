package fr.octo.craft.salle_de_sport.formules.domain;

import fr.octo.craft.common.event.Event;

public final class PrixFormuleChangé extends Event {

    public final FormuleId formuleId;
    public final int ancienPrix;
    public final int nouveauPrix;

    public PrixFormuleChangé(final Formule formule, final Integer ancienPrix) {
        this.formuleId = formule.id();
        this.ancienPrix = ancienPrix;
        this.nouveauPrix = formule.prixDeBase();
    }
}
