package fr.octo.craft.salle_de_sport.abonnés.domain;

public final class AbonnéRepositoryException extends Throwable {

    private AbonnéRepositoryException(String message) {
        super(message);
    }

    public static AbonnéRepositoryException introuvable(final AbonnéId abonnéId) {
        return new AbonnéRepositoryException("Abonné ["+ abonnéId +"] introuvable.");
    }
}
