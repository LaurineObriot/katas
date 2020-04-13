package fr.octo.craft.salle_de_sport.reporting.useCases;

import fr.octo.craft.salle_de_sport.abonnements.domain.DateCustom;

public final class QuelEstLeChiffreAffairePourUnMoisDonnéQuery {

    public final DateCustom date;

    public QuelEstLeChiffreAffairePourUnMoisDonnéQuery(DateCustom date) {
        this.date = date;
    }
}
