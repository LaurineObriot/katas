package fr.octo.craft.salle_de_sport.abonnements.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RéductionTest {

    @Test
    public void moins_30_pourcent_à_l_année() {

        final var estALannée = true;
        final var estEtudiant = false;
        var réductionAbonnementAnnuel = new Réduction(estALannée, estEtudiant);

        assertEquals(0.3, réductionAbonnementAnnuel.taux(), 0);
    }

    @Test
    public void moins_20_pourcent_pour_les_étudiants() {

        final var estALannée = false;
        final var estEtudiant = true;
        var réductionAbonnementEtudiant = new Réduction(estALannée, estEtudiant);

        assertEquals(0.2, réductionAbonnementEtudiant.taux(), 0);
    }

    @Test
    public void moins_50_pourcent_pour_les_étudiants_à_l_année() {

        final var estALannée = true;
        final var estEtudiant = true;
        var réductionAbonnementEtudiantAnnuel = new Réduction(estALannée, estEtudiant);

        assertEquals((Double) 0.2 + (Double) 0.3, réductionAbonnementEtudiantAnnuel.taux(), 0);
    }
}
