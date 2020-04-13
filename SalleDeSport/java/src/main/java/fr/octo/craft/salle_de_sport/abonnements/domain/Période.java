package fr.octo.craft.salle_de_sport.abonnements.domain;

import java.time.Period;

final class Période {

    private static final String AU = " au ";

    final DateCustom dateDeDébut;
    private final DateCustom dateDeFin;

    Période(DateCustom dateDeDébut, Integer nbMois) {
        this.dateDeDébut = dateDeDébut;
        this.dateDeFin = dateDeDébut.plusXMois(nbMois).jourDAvant();
    }

    Période(String période) {
        this(
            new DateCustom(période.split(AU)[0]),
            new DateCustom(période.split(AU)[1])
        );
    }

    private Période(DateCustom dateDeDébut, DateCustom dateDeFin) {
        this.dateDeDébut = dateDeDébut;
        this.dateDeFin = dateDeFin;
    }

    Boolean contient(final DateCustom date) {
        return date.aprèsOuÉgale(dateDeDébut) && date.avant(dateDeFin);
    }

    Boolean avantLaDate(final DateCustom date) {
        return date.après(dateDeFin);
    }

    Période suivante() {
        var nbMoisPériodeCourante = Period.between(dateDeDébut.toLocalDate(), dateDeFin.jourSuivant().toLocalDate()).getMonths();

        return new Période(
            dateDeFin.jourSuivant(),
            nbMoisPériodeCourante
        );
    }

    @Override
    public String toString() {
        return dateDeDébut + AU + dateDeFin;
    }
}
