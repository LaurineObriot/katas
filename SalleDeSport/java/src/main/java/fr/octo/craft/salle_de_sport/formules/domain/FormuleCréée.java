package fr.octo.craft.salle_de_sport.formules.domain;

import fr.octo.craft.common.event.Event;

public final class FormuleCréée extends Event {

    public final FormuleId formuleId;

    public FormuleCréée(final Formule formule) {
        this.formuleId = formule.id();
    }
}
