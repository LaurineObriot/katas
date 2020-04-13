package fr.octo.craft.salle_de_sport.formules.useCases;

import fr.octo.craft.salle_de_sport.formules.domain.FormuleRepository;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleRepositoryException;
import fr.octo.craft.salle_de_sport.formules.domain.PrixFormuleChangé;

final class ChangerLePrixDUneFormule {

    private final FormuleRepository formuleRepository;

    ChangerLePrixDUneFormule(FormuleRepository formuleRepository) {
        this.formuleRepository = formuleRepository;
    }

    PrixFormuleChangé handle(ChangerLePrixDUneFormuleCommand command) throws FormuleRepositoryException {

        var formule = formuleRepository.get(command.formuleId);

        var ancienPrix = formule.prixDeBase();

        formule.changeDePrix(command.nouveauPrix);

        formuleRepository.store(formule);

        return new PrixFormuleChangé(
            formule,
            ancienPrix
        );
    }
}
