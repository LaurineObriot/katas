import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnalyseurTest {

    private final Analyseur analyseur = new Analyseur();

    @Test
    public void partie_nest_pas_gagnée_lorsque_grille_vide() {
        // Given
        Grille grille = new Grille();

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isFalse();
    }

    @Test
    public void partie_est_gagnée_lorsque_première_colonne_de_4_rouges() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_deuxième_colonne_de_4_rouges() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_nest_pas_gagnée_lorsque_grille_contient_colonne_de_4_rouges_avec_1_jaune_au_milieu() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.JAUNE);
        grille.ajouterJeton(1, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isFalse();
    }

    @Test
    public void partie_nest_pas_gagnée_lorsque_grille_contient_ligne_de_4_rouges_avec_1_jaune_au_milieu() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isFalse();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_ligne_de_4_rouges() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_milieu_montante_de_4_rouges_dans_coin_inférieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_milieu_montante_de_4_rouges_dans_coin_supérieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.JAUNE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.JAUNE);
        grille.ajouterJeton(6, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_supérieure_montante_de_4_rouges_dans_coin_inférieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_supérieure_montante_de_4_rouges_dans_coin_supérieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);


        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_supérieure_supérieure_montante_de_4_rouges() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.JAUNE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_inférieure_montante_de_4_rouges_dans_coin_inférieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_inférieure_montante_de_4_rouges_dans_coin_supérieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.JAUNE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.ROUGE);


        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_inférieure_inférieure_montante_de_4_rouges_coin_inférieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.JAUNE);
        grille.ajouterJeton(6, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_inférieure_inférieure_montante_de_4_rouges_coin_supérieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.JAUNE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_inférieure_inférieure_inférieure_montante_de_4_rouges() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.JAUNE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_milieu_descendante_de_4_rouges_dans_coin_supérieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.JAUNE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_milieu_descendante_de_4_rouges_dans_coin_inférieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_supérieure_descendante_de_4_rouges_dans_coin_supérieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_supérieure_descendante_de_4_rouges_dans_coin_inférieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.JAUNE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.ROUGE);


        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_supérieure_supérieure_descendante_de_4_rouges() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.JAUNE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_supérieure_supérieure_supérieure_descendante_de_4_rouges() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.JAUNE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_inférieure_descendante_de_4_rouges_dans_coin_supérieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.JAUNE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_inférieure_descendante_de_4_rouges_dans_coin_inférieur() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);

        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_gagnée_lorsque_grille_contient_diagonale_inférieure_inférieure_descendante_de_4_jaunes() throws Exception {
        // Given
        Grille grille = new Grille();
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.JAUNE);


        // When
        boolean partieGagnée = analyseur.partieGagnée(grille);

        // Then
        assertThat(partieGagnée).isTrue();
    }

    @Test
    public void partie_est_non_nulle_lorsque_la_grille_est_non_remplie() throws Exception {
        // Given
        Grille grille = new Grille();

        // When
        boolean partieNulle = analyseur.partieNulle(grille);

        // Then
        assertThat(partieNulle).isFalse();
    }

    @Test
    public void partie_est_nulle_lorsque_la_grille_est_remplie_sans_ligne_gagnante() throws Exception {
        // Given
        Grille grille = génererGrilleNulle();

        // When
        boolean partieNulle = analyseur.partieNulle(grille);

        // Then
        assertThat(partieNulle).isTrue();
    }

    @Test
    public void partie_est_non_nulle_lorsque_la_grille_est_remplie_avec_ligne_gagnante() throws Exception {
        // Given
        Grille grille = génererGrilleRemplieAvecLigneGagnante();

        // When
        boolean partieNulle = analyseur.partieNulle(grille);

        // Then
        assertThat(partieNulle).isFalse();
    }

    private Grille génererGrilleNulle() throws Exception {
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.JAUNE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.JAUNE);
        grille.ajouterJeton(1, Jetons.JAUNE);
        grille.ajouterJeton(1, Jetons.ROUGE);

        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);

        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);

        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);

        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);

        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.JAUNE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.JAUNE);

        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.ROUGE);

        return grille;
    }

    private Grille génererGrilleRemplieAvecLigneGagnante() throws Exception {
        Grille grille = new Grille();
        grille.ajouterJeton(1, Jetons.JAUNE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.ROUGE);
        grille.ajouterJeton(1, Jetons.JAUNE);
        grille.ajouterJeton(1, Jetons.JAUNE);
        grille.ajouterJeton(1, Jetons.ROUGE);

        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.ROUGE);
        grille.ajouterJeton(2, Jetons.JAUNE);

        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);
        grille.ajouterJeton(3, Jetons.JAUNE);
        grille.ajouterJeton(3, Jetons.ROUGE);

        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.JAUNE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);
        grille.ajouterJeton(4, Jetons.ROUGE);

        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.JAUNE);
        grille.ajouterJeton(5, Jetons.ROUGE);
        grille.ajouterJeton(5, Jetons.ROUGE);

        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.JAUNE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);
        grille.ajouterJeton(6, Jetons.ROUGE);

        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.ROUGE);
        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.JAUNE);
        grille.ajouterJeton(7, Jetons.ROUGE);

        return grille;
    }
}