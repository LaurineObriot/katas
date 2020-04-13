package fr.octo.craft.salle_de_sport.abonnements.domain;

import fr.octo.craft.common.AggregateId;

public final class AbonnementId extends AggregateId {

    private AbonnementId(String id) {
        super(id);
    }

    public static AbonnementId fromString(String id) {
        return new AbonnementId(id);
    }
}
