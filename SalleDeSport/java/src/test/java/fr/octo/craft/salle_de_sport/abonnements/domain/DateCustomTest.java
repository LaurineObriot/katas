package fr.octo.craft.salle_de_sport.abonnements.domain;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DateCustomTest {

    @Test
    public void fromString() {
        var tested = new DateCustom("2018-06-16");

        assertEquals("2018-06-16", tested.toString());
    }

    @Test
    public void aprèsOuEgale() {
        var tested = new DateCustom("2018-06-15");

        assertTrue(tested.aprèsOuÉgale(new DateCustom("2018-06-15")));
    }

    @Test
    public void avant() {
        var tested = new DateCustom("2018-06-16");

        assertTrue(tested.avant(new DateCustom("2018-06-17")));
    }

    @Test
    public void plusXMois() {
        var tested = new DateCustom("2018-06-16");

        assertEquals("2018-09-16", tested.plusXMois(3).toString());
    }

    @Test
    public void jourDAvant() {
        var tested = new DateCustom("2018-06-01");

        assertEquals("2018-05-31", tested.jourDAvant().toString());
    }

    @Test
    public void jourSuivant() {
        var tested = new DateCustom("2018-06-16");

        assertEquals("2018-06-17", tested.jourSuivant().toString());
    }
}
