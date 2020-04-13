package fr.octo.craft.salle_de_sport.abonnements.domain;

import java.util.Objects;

final class Prix {

    private final Double montant;

    private Prix(Double montant) {
        this.montant = montant;
    }

    Prix(Integer montant) {
        this.montant = montant.doubleValue();
    }

    Prix aprèsRéduction(final Réduction réduction) {
        return new Prix(montant * (1 - réduction.taux()));
    }

    @Override
    public String toString() {
        return String.valueOf(montant.intValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prix prix = (Prix) o;
        return Objects.equals(montant, prix.montant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(montant);
    }
}
