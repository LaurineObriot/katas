package fr.octo.craft.salle_de_sport.abonnés.domain;

import fr.octo.craft.common.event.Event;
import fr.octo.craft.salle_de_sport.abonnements.domain.Abonnement;
import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementId;

public final class EmailDeBienvenueALaSouscriptionEnvoyé extends Event {

    public final String email;
    public final AbonnementId abonnementId;

    public EmailDeBienvenueALaSouscriptionEnvoyé(final String email, final Abonnement abonnement) {
        this.email = email;
        this.abonnementId = abonnement.id();
    }
}
