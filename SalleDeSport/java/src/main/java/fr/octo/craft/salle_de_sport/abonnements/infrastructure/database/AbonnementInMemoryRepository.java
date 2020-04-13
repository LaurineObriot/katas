package fr.octo.craft.salle_de_sport.abonnements.infrastructure.database;

import fr.octo.craft.salle_de_sport.abonnements.domain.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public final class AbonnementInMemoryRepository implements AbonnementRepository {

    private final Collection<Abonnement> abonnements = new ArrayList<>();

    @Override
    public AbonnementId nextId() {
        return AbonnementId.fromString(UUID.randomUUID().toString());
    }

    @Override
    public void store(Abonnement abonnement) {
        abonnements.add(abonnement);
    }

    @Override
    public void storeAll(Collection<Abonnement> abonnements) {
        for (Abonnement abonnement : abonnements) {
            store(abonnement);
        }
    }

    @Override
    public Abonnement get(AbonnementId abonnementId) throws AbonnementRepositoryException {

        for (Abonnement abonnement : abonnements) {
            if (abonnement.id().equals(abonnementId)) {
                return abonnement;
            }
        }

        throw AbonnementRepositoryException.introuvable(abonnementId);
    }

    @Override
    public Collection<Abonnement> abonnementsEnCours(DateCustom date) {

        Collection<Abonnement> abonnementsEnCours = new ArrayList<>();

        for (Abonnement abonnement : abonnements) {
            if (abonnement.estEnCours(date)) {
                abonnementsEnCours.add(abonnement);
            }
        }

        return abonnementsEnCours;
    }

    @Override
    public Collection<Abonnement> abonnementsFinisAPartirDe(DateCustom date) {

        Collection<Abonnement> abonnementsFinisAPartirDe = new ArrayList<>();

        for (Abonnement abonnement : abonnements) {
            if (abonnement.seraFini(date)) {
                abonnementsFinisAPartirDe.add(abonnement);
            }
        }

        return abonnementsFinisAPartirDe;
    }

    @Override
    public Collection<Abonnement> abonnementsAnniversaireDes3ansLe(final DateCustom date) {

        Collection<Abonnement> abonnementsAnniversaireDes3ans = new ArrayList<>();

        for (Abonnement abonnement : abonnements) {
            if (abonnement.anniversaireDes3ansLe(date)) {
                abonnementsAnniversaireDes3ans.add(abonnement);
            }
        }

        return abonnementsAnniversaireDes3ans;
    }
}
