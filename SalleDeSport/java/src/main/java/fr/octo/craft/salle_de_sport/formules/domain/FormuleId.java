package fr.octo.craft.salle_de_sport.formules.domain;

import fr.octo.craft.common.AggregateId;

public final class FormuleId extends AggregateId {

    private FormuleId(String id) {
        super(id);
    }

    public static FormuleId fromString(String id) {
        return new FormuleId(id);
    }
}
