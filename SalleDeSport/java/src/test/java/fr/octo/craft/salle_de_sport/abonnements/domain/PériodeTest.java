package fr.octo.craft.salle_de_sport.abonnements.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PériodeTest {

    @Test
    public void contient_une_date() {
        var tested = new Période("2018-06-01 au 2018-08-01");

        assertTrue(tested.contient(new DateCustom("2018-06-01")));
        assertTrue(tested.contient(new DateCustom("2018-06-02")));

        assertTrue(tested.contient(new DateCustom("2018-07-31")));
        assertFalse(tested.contient(new DateCustom("2018-08-01")));
    }

    @Test
    public void est_avant_une_date() {
        var tested = new Période(new DateCustom("2018-06-01"), 2);

        assertFalse(tested.avantLaDate(new DateCustom("2018-07-31")));
        assertTrue(tested.avantLaDate(new DateCustom("2018-08-01")));
    }

    @Test
    public void suivante() {
        var tested = new Période(new DateCustom("2018-06-01"), 2);

        assertEquals("2018-08-01 au 2018-09-30", tested.suivante().toString());
    }
}
