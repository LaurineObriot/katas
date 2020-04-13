package fr.octo.craft.salle_de_sport.formules.infrastructure.database;

import fr.octo.craft.salle_de_sport.formules.domain.Formule;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleId;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleRepository;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleRepositoryException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public final class FormuleInMemoryRepository implements FormuleRepository {

    private final Collection<Formule> formules = new ArrayList<>();

    @Override
    public FormuleId nextId() {
        return FormuleId.fromString(UUID.randomUUID().toString());
    }

    @Override
    public void store(Formule formule) {
        formules.add(formule);
    }

    @Override
    public Formule get(FormuleId formuleId) throws FormuleRepositoryException {
        for (Formule formule : formules) {
            if (formule.id().equals(formuleId)) {
                return formule;
            }
        }

        throw FormuleRepositoryException.introuvable(formuleId);
    }
}
