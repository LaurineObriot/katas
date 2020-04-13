package fr.octo.craft.salle_de_sport.abonnés.domain;

import fr.octo.craft.common.AggregateId;

public final class AbonnéId extends AggregateId {

    private AbonnéId(String id) {
        super(id);
    }

    public static AbonnéId fromString(String id) {
        return new AbonnéId(id);
    }
}
