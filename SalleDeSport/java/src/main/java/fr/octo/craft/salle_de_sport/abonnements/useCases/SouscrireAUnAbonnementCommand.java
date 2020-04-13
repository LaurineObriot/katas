package fr.octo.craft.salle_de_sport.abonnements.useCases;

import fr.octo.craft.salle_de_sport.abonnements.domain.AbonnementId;
import fr.octo.craft.salle_de_sport.abonnements.domain.DateCustom;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleId;

public class SouscrireAUnAbonnementCommand {
    public final AbonnementId abonnementId;
    public final String email;
    public final Boolean estEtudiant;
    public final FormuleId formuleId;
    public final DateCustom dateDeDébut;

    public SouscrireAUnAbonnementCommand(AbonnementId abonnementId, String email, Boolean estEtudiant, FormuleId formuleId, DateCustom dateDeDébut) {
        this.abonnementId = abonnementId;
        this.email = email;
        this.estEtudiant = estEtudiant;
        this.formuleId = formuleId;
        this.dateDeDébut = dateDeDébut;
    }
}
