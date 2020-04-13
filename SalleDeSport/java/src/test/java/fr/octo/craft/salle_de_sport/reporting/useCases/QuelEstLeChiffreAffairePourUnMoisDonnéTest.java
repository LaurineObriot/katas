package fr.octo.craft.salle_de_sport.reporting.useCases;

import fr.octo.craft.salle_de_sport.abonnements.domain.Abonnement;
import fr.octo.craft.salle_de_sport.abonnements.domain.DateCustom;
import fr.octo.craft.salle_de_sport.abonnements.infrastructure.database.AbonnementInMemoryRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuelEstLeChiffreAffairePourUnMoisDonnéTest {

    @Test
    public void chiffre_d_affaire_pour_un_mois_donné_avec_abonnements_en_cours() {

        var abonnementRepository = new AbonnementInMemoryRepository();

        var aujourdhui = new DateCustom("2018-06-09");
        var dansUnMois = new DateCustom("2018-07-09");

        abonnementRepository.store(
            new Abonnement(
                abonnementRepository.nextId(),
                false,
                "description",
                50,
                1,
                aujourdhui
            )
        );
        abonnementRepository.store(
            new Abonnement(
                abonnementRepository.nextId(),
                false,
                "description",
                500,
                12,
                dansUnMois
            )
        );

        var tested = new QuelEstLeChiffreAffairePourUnMoisDonné(
            abonnementRepository
        );

        assertEquals(50, tested.handle(new QuelEstLeChiffreAffairePourUnMoisDonnéQuery(aujourdhui)), 0);
        assertEquals(1, abonnementRepository.abonnementsEnCours(aujourdhui).size());

        assertEquals(350, tested.handle(new QuelEstLeChiffreAffairePourUnMoisDonnéQuery(dansUnMois)), 0);
        assertEquals(1, abonnementRepository.abonnementsEnCours(dansUnMois).size());
    }
}
