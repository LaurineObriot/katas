package fr.octo.craft.salle_de_sport.formules.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class FormuleTest {

    @Test
    public void on_peut_creer_une_nouvelle_formule_avec_un_prix_de_base_et_et_une_durée() {
        var formule = new Formule(
            FormuleId.fromString("abcdef"),
            400,
            DuréeFormule.MOIS
        );

        assertTrue(formule.id() instanceof FormuleId);
        assertEquals(400, (int) formule.prixDeBase());
        assertFalse(formule.estALannée());
    }
}
