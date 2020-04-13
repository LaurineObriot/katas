package fr.octo.craft.salle_de_sport.abonnements.useCases;

import fr.octo.craft.salle_de_sport.abonnements.domain.DateCustom;

public final class RenouvellerLesAbonnementsAutomatiquementCommand {

    public final DateCustom àPartirDe;

    public RenouvellerLesAbonnementsAutomatiquementCommand(DateCustom àPartirDe) {
        this.àPartirDe = àPartirDe;
    }
}
