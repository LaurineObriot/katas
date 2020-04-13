package fr.octo.craft.salle_de_sport.abonnements.domain;

import fr.octo.craft.common.event.Event;

public final class AbonnementSouscrit extends Event {

    public final AbonnementId abonnementId;
    public final String descriptionFormuleChoisie;
    public final String email;

    public AbonnementSouscrit(final Abonnement abonnement, final String email) {
        this.abonnementId = abonnement.id();
        this.descriptionFormuleChoisie = abonnement.descriptionFormuleChoisie();
        this.email = email;
    }
}
