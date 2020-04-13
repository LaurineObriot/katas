package fr.octo.craft.salle_de_sport.formules.useCases;

import fr.octo.craft.salle_de_sport.formules.domain.DuréeFormule;
import fr.octo.craft.salle_de_sport.formules.domain.FormuleId;

public final class CréerUneNouvelleFormuleCommand {
    public final FormuleId formuleId;
    public final Integer prixDeBase;
    public final DuréeFormule duréeFormule;

    public CréerUneNouvelleFormuleCommand(FormuleId formuleId, Integer prixDeBase, DuréeFormule duréeFormule) {
        this.formuleId = formuleId;
        this.prixDeBase = prixDeBase;
        this.duréeFormule = duréeFormule;
    }
}
