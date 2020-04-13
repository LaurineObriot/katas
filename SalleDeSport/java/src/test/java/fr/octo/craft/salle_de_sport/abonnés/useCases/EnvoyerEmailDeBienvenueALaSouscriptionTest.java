package fr.octo.craft.salle_de_sport.abonnés.useCases;

import fr.octo.craft.salle_de_sport.abonnements.domain.*;
import fr.octo.craft.salle_de_sport.abonnés.domain.Mailer;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class EnvoyerEmailDeBienvenueALaSouscriptionTest {

    @Test
    public void handle() throws AbonnementRepositoryException {

        var abonnement = new Abonnement(
            AbonnementId.fromString("abcdef"),
            false,
            "description",
            500,
            12,
            new DateCustom()
        );
        var abonnementRepository = mock(AbonnementRepository.class);
        when(abonnementRepository.get(abonnement.id())).thenReturn(abonnement);

        var mailer = mock(Mailer.class);

        var tested = new EnvoyerEmailDeBienvenueALaSouscription(
            abonnementRepository,
            mailer
        );

        tested.handle(
            new AbonnementSouscrit(abonnement, "bob@octo.com")
        );

        verify(mailer).sendEmail(anyString(), anyString());
    }
}
