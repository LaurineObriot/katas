package fr.octo.craft.salle_de_sport.abonnés.useCases;

import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementRepository;
import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementRepositoryException;
import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementSouscrit;
import fr.octo.craft.salle_de_sport.abonnés.domain.EmailDeBienvenueALaSouscriptionEnvoyé;
import fr.octo.craft.salle_de_sport.abonnés.domain.Mailer;

final class EnvoyerEmailDeBienvenueALaSouscription {

    private final AbonnementRepository abonnementRepository;
    private final Mailer mailer;

    EnvoyerEmailDeBienvenueALaSouscription(AbonnementRepository abonnementRepository, Mailer mailer) {
        this.abonnementRepository = abonnementRepository;
        this.mailer = mailer;
    }

    EmailDeBienvenueALaSouscriptionEnvoyé handle(final AbonnementSouscrit event) throws AbonnementRepositoryException {

        var abonnement = abonnementRepository.get(event.abonnementId);

        mailer.sendEmail(
            event.email,
            "Bienvenu(e) chez CraftGym, profite bien de ton abonnement "+abonnement.descriptionFormuleChoisie()+"."
        );

        return new EmailDeBienvenueALaSouscriptionEnvoyé(
            event.email,
            abonnement
        );
    }
}
