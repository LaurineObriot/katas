package fr.octo.craft.salle_de_sport.formules.domain;

public interface FormuleRepository {

    FormuleId nextId();

    void store(Formule formule);

    Formule get(FormuleId formuleId) throws FormuleRepositoryException;
}
