package fr.octo.craft.salle_de_sport.abonnements.domain;

import java.util.Collection;

public interface AbonnementRepository {

    AbonnementId nextId();

    void store(Abonnement abonnement);

    void storeAll(Collection<Abonnement> abonnements);

    Abonnement get(AbonnementId abonnementId) throws AbonnementRepositoryException;

    Collection<Abonnement> abonnementsEnCours(DateCustom date);

    Collection<Abonnement> abonnementsFinisAPartirDe(DateCustom date);

    Collection<Abonnement> abonnementsAnniversaireDes3ansLe(DateCustom date);
}
