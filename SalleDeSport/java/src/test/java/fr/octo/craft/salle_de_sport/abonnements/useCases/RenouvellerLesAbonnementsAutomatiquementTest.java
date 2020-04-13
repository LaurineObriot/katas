package fr.octo.craft.salle_de_sport.abonnements.useCases;

import fr.octo.craft.salle_de_sport.abonnements.domain.Abonnement;
import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementRepositoryException;
import fr.octo.craft.salle_de_sport.abonnements.domain.DateCustom;
import fr.octo.craft.salle_de_sport.abonnements.infrastructure.database.AbonnementInMemoryRepository;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class RenouvellerLesAbonnementsAutomatiquementTest {

    @Test
    public void handle() throws AbonnementRepositoryException {

        var abonnementRepository = new AbonnementInMemoryRepository();

        var abonnement = new Abonnement(
            abonnementRepository.nextId(),
            false,
            "description",
            200,
            1,
            new DateCustom("2018-06-09")
        );

        abonnementRepository.store(abonnement);

        var tested = new RenouvellerLesAbonnementsAutomatiquement(
            abonnementRepository
        );

        tested.handle(
            new RenouvellerLesAbonnementsAutomatiquementCommand(
                new DateCustom("2018-07-09")
            )
        );

        var dateEnCoursAprèsRenouvellement = new DateCustom("2018-08-01");
        assertTrue(abonnementRepository.get(abonnement.id()).estEnCours(dateEnCoursAprèsRenouvellement));

        var dateAprèsLaFinAprèsRenouvellement = new DateCustom("2018-08-10");
        assertTrue(abonnementRepository.get(abonnement.id()).seraFini(dateAprèsLaFinAprèsRenouvellement));
    }
}
