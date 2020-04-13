package fr.octo.craft.salle_de_sport.formules.useCases;

import fr.octo.craft.salle_de_sport.formules.domain.DuréeFormule;
import fr.octo.craft.salle_de_sport.formules.infrastructure.database.FormuleInMemoryRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CréerUneNouvelleFormuleTest {

    @Test
    public void creer_une_nouvelle_formule() {

        var formuleRepository = new FormuleInMemoryRepository();
        var formuleId = formuleRepository.nextId();

        var tested = new CréerUneNouvelleFormule(formuleRepository);

        var event = tested.handle(
            new CréerUneNouvelleFormuleCommand(
                formuleId,
                300,
                DuréeFormule.MOIS
            )
        );

        assertEquals(event.formuleId, formuleId);
    }
}
