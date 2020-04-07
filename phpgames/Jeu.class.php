<?php

/**
* Class Jeu, mère de toutes les classes
*/
abstract class Jeu{

	function messageBienvenue($prenomJoueur, $nomDuJeu, $regles){
		echo "==========================================\n";
		echo "============= $nomDuJeu =============\n";
		echo "==========================================\n";

		echo "Bienvenue au jeu du $nomDuJeu, $prenomJoueur.\n\n";
		do{
			echo "===> Souhaitez-vous lire les regles du jeu ? (oui ou non) : ";
			$reponseRegles=lire();
			if ($reponseRegles=="oui") {
				echo "\n";
				$this->reglesDuJeu($prenomJoueur, $regles);
			}
			elseif($reponseRegles=="non"){
				echo "\n";
				$this->niveauDifficultes($prenomJoueur);
			}
		} while($reponseRegles != "oui" || $reponseRegles != "non");
	}



	function reglesDuJeu($prenomJoueur, $regles){
		echo "\n=== REGLES DU JEU ===\n";
		echo $regles;
		echo "\n";
		do{
			echo "==> Saisir \"quitter\" pour sortir des regles: ";
			$quitterRegles=lire();
			if($quitterRegles=="quitter"){
				echo "\n";
				$this->niveauDifficultes($prenomJoueur);
			}
		} while($quitterRegles != "quitter");
	}


	function replay($prenomJoueur, $jeu=false, $score=false, $scoreFinal=false, $difficultes=false){
		do {
			echo "===> Voulez-vous rejouer ? (oui ou non) : ";
			$reponseRejouer=lire();
			if($reponseRejouer == "oui"){
				$this->partie++;
				break;
			}
			elseif($reponseRejouer == "non"){
				echo "\n";

				if ($jeu=="Questions-Reponses") {

					echo "\nMerci d'avoir joue, votre score cumule est de $this->scoreFinal points.\n\n";
					$ecrire = new Score;
					$ecrire->ecrireResultatDansTXT($prenomJoueur, $this->jeu, $this->scoreFinal,$this->difficultes);
				}

				initialisation($prenomJoueur);
			}
		} while($reponseRejouer!="oui");
		return $reponseRejouer;
	}


}

?>
