package fr.octo.craft.salle_de_sport.formules.useCases;

import fr.octo.craft.salle_de_sport.formules.domain.FormuleId;

public class ChangerLePrixDUneFormuleCommand {
    public final FormuleId formuleId;
    public final Integer nouveauPrix;

    public ChangerLePrixDUneFormuleCommand(FormuleId formuleId, Integer nouveauPrix) {
        this.formuleId = formuleId;
        this.nouveauPrix = nouveauPrix;
    }
}
