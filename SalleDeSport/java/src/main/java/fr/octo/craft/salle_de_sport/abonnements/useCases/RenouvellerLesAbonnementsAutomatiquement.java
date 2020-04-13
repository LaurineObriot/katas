package fr.octo.craft.salle_de_sport.abonnements.useCases;

import fr.octo.craft.salle_de_sport.abonnements.domain.Abonnement;
import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementRepository;
import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementsRenouvellésAutomatiquement;

final class RenouvellerLesAbonnementsAutomatiquement {

    private final AbonnementRepository abonnementRepository;

    RenouvellerLesAbonnementsAutomatiquement(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;
    }

    AbonnementsRenouvellésAutomatiquement handle(RenouvellerLesAbonnementsAutomatiquementCommand command) {

        var abonnementsFinisAPartirDe = abonnementRepository.abonnementsFinisAPartirDe(command.àPartirDe);

        for (Abonnement abonnement : abonnementsFinisAPartirDe) {
            abonnement.renouveller();
        }

        abonnementRepository.storeAll(abonnementsFinisAPartirDe);

        return new AbonnementsRenouvellésAutomatiquement();
    }
}
