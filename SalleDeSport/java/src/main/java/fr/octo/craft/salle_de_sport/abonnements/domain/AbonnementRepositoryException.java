package fr.octo.craft.salle_de_sport.abonnements.domain;

public final class AbonnementRepositoryException extends Throwable {

    private AbonnementRepositoryException(String message) {
        super(message);
    }

    public static AbonnementRepositoryException introuvable(final AbonnementId abonnementId) {
        return new AbonnementRepositoryException("Abonnement ["+abonnementId+"] introuvable.");
    }
}
