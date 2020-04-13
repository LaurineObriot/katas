package fr.octo.craft.salle_de_sport.abonnements.useCases;

import fr.octo.craft.salle_de_sport.abonnements.domain.Abonnement;
import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementRepository;
import fr.octo.craft.salle_de_sport.abonnements.domain.DateCustom;
import fr.octo.craft.salle_de_sport.formules.domain.DuréeFormule;
import fr.octo.craft.salle_de_sport.formules.domain.Formule;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleRepositoryException;
import fr.octo.craft.salle_de_sport.formules.infrastructure.database.FormuleInMemoryRepository;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class SouscrireAUnAbonnementTest {

    @Test
    public void handle() throws FormuleRepositoryException {

        var formuleRepository = new FormuleInMemoryRepository();
        var abonnementRepository = mock(AbonnementRepository.class);

        var formuleId = formuleRepository.nextId();
        var formule = new Formule(
            formuleId,
            500,
            DuréeFormule.ANNEE
        );
        formuleRepository.store(formule);

        var tested = new SouscrireAUnAbonnement(
            formuleRepository,
            abonnementRepository
        );

        var event = tested.handle(
            new SouscrireAUnAbonnementCommand(
                abonnementRepository.nextId(),
                "bob@mail.com",
                false,
                formule.id(),
                new DateCustom("2018-06-10")
            )
        );

        verify(abonnementRepository).store(any(Abonnement.class));

        assertEquals(event.descriptionFormuleChoisie, event.descriptionFormuleChoisie);
        assertEquals("bob@mail.com", event.email);
    }
}
