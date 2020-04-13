package fr.octo.craft.salle_de_sport.abonnés.domain;

public interface Mailer {

    void sendEmail(String email, String message);
}
