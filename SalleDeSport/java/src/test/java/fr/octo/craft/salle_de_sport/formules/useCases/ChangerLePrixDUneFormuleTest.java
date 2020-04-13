package fr.octo.craft.salle_de_sport.formules.useCases;

import fr.octo.craft.salle_de_sport.formules.domain.DuréeFormule;
import fr.octo.craft.salle_de_sport.formules.domain.Formule;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleId;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleRepositoryException;
import fr.octo.craft.salle_de_sport.formules.infrastructure.database.FormuleInMemoryRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangerLePrixDUneFormuleTest {

    @Test
    public void handle() throws FormuleRepositoryException {
        var formuleId = FormuleId.fromString("abcdef");
        var formule = new Formule(formuleId, 450, DuréeFormule.ANNEE);

        var formuleRepository = new FormuleInMemoryRepository();
        formuleRepository.store(formule);

        var tested = new ChangerLePrixDUneFormule(formuleRepository);

        var event = tested.handle(
            new ChangerLePrixDUneFormuleCommand(
                FormuleId.fromString("abcdef"),
                400
            )
        );

        assertEquals(450, event.ancienPrix);
        assertEquals(400, event.nouveauPrix);
    }
}
