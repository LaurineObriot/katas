package fr.octo.craft.salle_de_sport.formules.useCases;

import fr.octo.craft.salle_de_sport.formules.domain.Formule;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleCréée;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleRepository;

final class CréerUneNouvelleFormule {

    private final FormuleRepository formuleRepository;

    CréerUneNouvelleFormule(FormuleRepository formuleRepository) {
        this.formuleRepository = formuleRepository;
    }

    FormuleCréée handle(CréerUneNouvelleFormuleCommand command) {

        var formule = new Formule(
            command.formuleId,
            command.prixDeBase,
            command.duréeFormule
        );

        formuleRepository.store(formule);

        return new FormuleCréée(formule);
    }
}
