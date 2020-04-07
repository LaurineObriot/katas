<?php


/**
* Class Score
*/
class Score{

	public static function menuScore($prenomJoueur){
		do {
			echo "===========================\n";
			echo "===== MENU DES SCORES =====\n";
			echo "===========================\n";
			if (file_exists("scores/Nombre Mystere.txt")) {
				echo "1. Nombre Mystere\n";
			}
			if (file_exists("scores/MasterMind.txt")) {
				echo "2. Mastermind\n";
			}
			if (file_exists("scores/Vrai Ou Faux.txt")) {
				echo "3. Vrai ou Faux \n";
			}
			if (file_exists("scores/Questions-Reponses.txt")) {
				echo "4. Questions/Reponses\n";
			}

			echo "\n5. Quitter les scores\n";
			echo "\n===> Choisissez le numéro du jeu dont vous souhaitez voir les scores: ";
			$choixScoreJeu=lire();
			echo "\n";

			switch ($choixScoreJeu) {
				case '1':
					if (file_exists("scores/Nombre Mystere.txt")) {
						$jeu="Nombre Mystere";
					}
					else{
						echo "Vous ne pouvez pas lire les scores d'un jeu qui n'a pas encore ete joue. \n\n";
						Score::menuScore($prenomJoueur);
					}
					break;

				case '2':
					if (file_exists("scores/MasterMind.txt")) {
							$jeu="MasterMind";
					}
					else{
						echo "Vous ne pouvez pas lire les scores d'un jeu qui n'a pas encore ete joue. \n\n";
						Score::menuScore($prenomJoueur);
					}
					break;

				case '3':
					if (file_exists("scores/Vrai Ou Faux.txt")) {
						$jeu="Vrai Ou Faux";
					}
					else{
						echo "Vous ne pouvez pas lire les scores d'un jeu qui n'a pas encore ete joue. \n\n";
						Score::menuScore($prenomJoueur);
					}
					break;

				case '4':
					if (file_exists("scores/Questions-Reponses.txt")) {
						$jeu="Questions-Reponses";
					}
					else{
						echo "Vous ne pouvez pas lire les scores d'un jeu qui n'a pas encore ete joue. \n\n";
						Score::menuScore($prenomJoueur);
					}
					break;

				case '5':
					initialisation($prenomJoueur);
					break;

				default:
					echo "Je n'ai pas compris votre choix, essayer encore.";
					break;
			}

		} while ( $choixScoreJeu != '1' && $choixScoreJeu != '2' && $choixScoreJeu != '3' && $choixScoreJeu != '4');


		$score = new Score();
		$score->lireLesScores($prenomJoueur, $jeu);
	}


	function lireLesScores($prenomJoueur, $jeu){
		$lireScore = fopen("scores/$jeu.txt", "r");

		echo "============= $jeu =============\n";
		while ($ligne = trim(fgets($lireScore))) {
			$afficherScore = explode(":", $ligne);

			$jeu = $afficherScore[0];
			$joueur = $afficherScore[1];
			$score = $afficherScore[2];
			$difficulte = $afficherScore[3];

			date_default_timezone_set('Europe/Paris');

			$jour = array("Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi");
			$mois = array("","Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre");
			$date = $jour[date("w")]." ".date("d")." ".$mois[date("n")]." ".date("Y")." a ".date("H:i");

			echo "- $joueur a obtenu $score point(s) dans une difficulte \"$difficulte\" le $date en jouant a(u) $jeu\n";
		}
		fclose($lireScore);

		do {
			echo "\n===> Souhaitez-vous lire les scores d'un autre jeu ou quitter ? \n";
			echo "Ecrivez \"score\" pour voir les autres scores et \"quitter\" pour retourner au menu principal: ";
			$quitterScore=lire();
			if ($quitterScore == "quitter") {
				echo "\n";
				initialisation($prenomJoueur);
			}
			elseif($quitterScore == "score"){
				echo "\n";
				$this->menuScore($prenomJoueur);
			}
		} while($quitterScore != "quitter");

	}


	public function ecrireResultatDansTXT($prenomJoueur, $jeu, $score, $difficultes){
		$dir="scores";
		if(!is_dir($dir)){
			mkdir($dir);
		}
		$ecrireScore = fopen("$dir/$jeu.txt", "a+");
		fputs($ecrireScore,  "$jeu:$prenomJoueur:$score:$difficultes\r\n");
		fclose($ecrireScore);
	}

	public static function supprimerResultatDansTXT($prenomJoueur){
		do {
			echo "===> ETES-VOUS SUR DE VOULOIR SUPPRIMER LE REPERTOIRE DES SCORES ? CELA REINITIALISERA A ZERO TOUS LES RESULTATS QUI AVAIENT ETE STOCKES ET CETTE OPERATION EST IRREVERSIBLE. (oui ou non) ";
			$reponseSupprimer=lire();
			echo "\n";

			if ($reponseSupprimer=="oui") {

				$repertoireASupprimer = "scores";
				$supprimerTout = glob($repertoireASupprimer."/*");

				foreach($supprimerTout AS $fichierTXT) {
					unlink($fichierTXT);
				}
				rmdir($repertoireASupprimer);

				echo "Le repertoire de scores a bien ete supprime.\n\n";
				initialisation($prenomJoueur);

			}
			elseif ($reponseSupprimer=="non") {
				echo "Aucun fichier n'a ete supprime.\n\n";
				initialisation($prenomJoueur);
			}

		} while ($reponseSupprimer != "oui" && $reponseSupprimer != "non");
	}



}

?>
