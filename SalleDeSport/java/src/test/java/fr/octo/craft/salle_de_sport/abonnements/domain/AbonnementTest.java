package fr.octo.craft.salle_de_sport.abonnements.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbonnementTest {

    @Test
    public void prix_de_base_pour_une_souscription_d_un_mois() {
        var abonnementSansRéduc = new Abonnement(
            AbonnementId.fromString("abcdef"),
            false,
            "description",
            300,
            1,
            cinqJuin()
        );

        assertEquals("300", abonnementSansRéduc.prix);
    }

    @Test
    public void moins_30_pourcent_pour_une_souscription_à_l_année() {
        var abonnementAvecRéducAnnée = new Abonnement(
            AbonnementId.fromString("abcdef"),
            false,
            "description",
            100,
            12,
            cinqJuin()
        );

        assertEquals("70", abonnementAvecRéducAnnée.prix);
    }

    @Test
    public void moins_20_pourcent_pour_la_souscription_d_un_étudiant() {
        var abonnementEtudiantAuMois = new Abonnement(
            AbonnementId.fromString("abcdef"),
            true,
            "description",
            100,
            1,
            cinqJuin()
        );
        assertEquals("80", abonnementEtudiantAuMois.prix);

        var abonnementEtudiantAnnée = new Abonnement(
            AbonnementId.fromString("abcdef"),
            true,
            "description",
            100,
            12,
            cinqJuin()
        );
        assertEquals("50", abonnementEtudiantAnnée.prix);
    }

    @Test
    public void un_abonnement_peut_être_en_cours() {
        var abonnementEnCours = new Abonnement(
            AbonnementId.fromString("abcdef"),
            false,
            "description",
            100,
            1,
            cinqJuin()
        );

        var dateCourantJuin = new DateCustom("2018-06-19");

        assertTrue(abonnementEnCours.estEnCours(dateCourantJuin));
    }

    @Test
    public void permet_de_déterminer_s_il_sera_fini_à_une_date() {
        var abonnementFiniFinJuin = new Abonnement(
            AbonnementId.fromString("abcdef"),
            false,
            "description",
            100,
            1,
            cinqJuin()
        );

        assertFalse(abonnementFiniFinJuin.seraFini(new DateCustom("2018-07-04")));
        assertTrue(abonnementFiniFinJuin.seraFini(new DateCustom("2018-07-05")));
    }

    @Test
    public void peut_être_renouvellé() {
        var abonnement = new Abonnement(
            AbonnementId.fromString("abcdef"),
            false,
            "description",
            100,
            1,
            cinqJuin()
        );

        assertFalse(abonnement.seraFini(new DateCustom("2018-07-04")));
        assertTrue(abonnement.seraFini(new DateCustom("2018-07-05")));

        abonnement.renouveller();

        assertFalse(abonnement.seraFini(new DateCustom("2018-08-04")));
        assertTrue(abonnement.seraFini(new DateCustom("2018-08-05")));
    }

    @Test
    public void permet_de_déterminer_s_il_dure_depuis_3_ans() {
        var abonnement = new Abonnement(
            AbonnementId.fromString("abcdef"),
            false,
            "description",
            100,
            12,
            troisAnsAvantLeCinqJuin()
        );

        assertFalse(abonnement.anniversaireDes3ansLe(new DateCustom("2018-06-04")));
        assertTrue(abonnement.anniversaireDes3ansLe(cinqJuin()));
        assertFalse(abonnement.anniversaireDes3ansLe(new DateCustom("2018-07-06")));
    }

    private DateCustom cinqJuin() {
        return new DateCustom("2018-06-05");
    }

    private DateCustom troisAnsAvantLeCinqJuin() {
        return cinqJuin().moinsXMois(3 * 12);
    }
}
