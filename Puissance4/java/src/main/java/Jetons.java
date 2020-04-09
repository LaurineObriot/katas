public enum Jetons {
    JAUNE("JAUNE", 'J'),
    ROUGE("ROUGE", 'R');

    private final String couleur;
    private final Character représentation;

    Jetons(String couleur, Character représentation) {
        this.couleur = couleur;
        this.représentation = représentation;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public Character getReprésentation() {
        return représentation;
    }
}
