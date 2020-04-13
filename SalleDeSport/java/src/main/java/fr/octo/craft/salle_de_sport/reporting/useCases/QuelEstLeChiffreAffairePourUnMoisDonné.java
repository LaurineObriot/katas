package fr.octo.craft.salle_de_sport.reporting.useCases;

import fr.octo.craft.salle_de_sport.abonnements.domain.Abonnement;
import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementRepository;

final class QuelEstLeChiffreAffairePourUnMoisDonné {

    private final AbonnementRepository abonnementRepository;

    QuelEstLeChiffreAffairePourUnMoisDonné(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }

    Double handle(QuelEstLeChiffreAffairePourUnMoisDonnéQuery query) {

        var chiffreAffaire = 0.0;

        for (Abonnement abonnementEnCours : abonnementRepository.abonnementsEnCours(query.date)) {
            chiffreAffaire += abonnementEnCours.restantDu();
        }

        return chiffreAffaire;
    }
}
