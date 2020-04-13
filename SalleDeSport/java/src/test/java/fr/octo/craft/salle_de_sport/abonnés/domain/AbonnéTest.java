package fr.octo.craft.salle_de_sport.abonnés.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AbonnéTest {

    @Test
    public void un_abonné_peut_être_créé_avec_un_email_et_un_nom() {
        var abonné = new Abonné(
            AbonnéId.fromString("abcdef"),
            "bob@octo.com",
            "Bob"
        );

        assertEquals("bob@octo.com", abonné.email());
        assertEquals("Bob", abonné.prénom());
    }

    @Test
    public void un_abonné_n_est_pas_étudiant_par_défaut() {
        var abonné = new Abonné(
            AbonnéId.fromString("abcdef"),
            "bob@octo.com",
            "Bob"
        );

        assertFalse(abonné.estEtudiant());
    }
}
