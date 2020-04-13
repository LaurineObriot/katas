package fr.octo.craft.salle_de_sport.abonnements.useCases;

import fr.octo.craft.salle_de_sport.abonnements.domain.Abonnement;
import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementRepository;
import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementSouscrit;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleRepository;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleRepositoryException;

final class SouscrireAUnAbonnement {

    private final FormuleRepository formuleRepository;
    private final AbonnementRepository abonnementRepository;

    SouscrireAUnAbonnement(FormuleRepository formuleRepository, AbonnementRepository abonnementRepository) {
        this.formuleRepository = formuleRepository;
        this.abonnementRepository = abonnementRepository;
    }

    public AbonnementSouscrit handle(SouscrireAUnAbonnementCommand command) throws FormuleRepositoryException {

        var formule = formuleRepository.get(command.formuleId);

        Abonnement abonnement = new Abonnement(
            command.abonnementId,
            command.estEtudiant,
            formule.description(),
            formule.prixDeBase(),
            formule.duréeEnMois(),
            command.dateDeDébut
        );

        abonnementRepository.store(abonnement);

        return new AbonnementSouscrit(abonnement, command.email);
    }
}
