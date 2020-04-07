<?php

/**
* classe Questions Réponses enfant de Jeu
*/
class QuestionReponse extends Jeu{

	private $fichierAOuvrir;
	protected $jeu = "Questions-Reponses";
	protected $coup;
	protected $score;
	protected $scoreFinal;
	protected $partie = 1;

	function niveauDifficultes($prenomJoueur){

		echo "\n=== Niveaux de difficultes disponibles === \n";
		echo "1. Debutant (questions faciles)\n";
		echo "2. Intermediaire (questions moyennes)\n";
		echo "3. Expert (questions difficiles)\n\n";

		echo "===> Saisissez un niveau de difficultes : ";
		$difficultes=lire();

		switch ($difficultes) {
			case '1':
				$this->fichierAOuvrir = "questions/questions_reponses_facile.txt";
				$this->difficultes = "Debutant";
				break;

			case '2':
				$this->fichierAOuvrir = "questions/questions_reponses_moyenne.txt";
				$this->difficultes = "Intermediaire";
				break;

			case '3':
				$this->fichierAOuvrir = "questions/questions_reponses_difficile.txt";
				$this->difficultes = "Expert";
				break;

			default:
				echo "Je n'ai pas compris votre reponse: \n";
				$this->niveauDifficultes($prenomJoueur);
				break;
		}

		$this->debutJeu($prenomJoueur);
		echo "\n";
	}


	function debutJeu($prenomJoueur){

		do {

			$lireQuestionReponse=fopen($this->fichierAOuvrir, "r");

			while ($ligne = trim(fgets($lireQuestionReponse))) {

				$explode = explode("|", $ligne);

				$questionMiniJeu = $explode[0];
				$reponseMiniJeu = $explode[1];

				echo "\nVoici la question proposee par le mini-jeu. Saurez-vous trouver la reponse ? \n";

				$this->coup=0;

				do {
					echo "\nQuestion numero $this->partie\n";
					echo "==================\n";

					echo "=> $questionMiniJeu \n";
					echo "===> ";
					$reponseQuestion=lire();
					$this->score=0;
					$this->coup++;


					if($reponseQuestion != $reponseMiniJeu){
						echo "\nDesole, ce n'est pas la bonne reponse, essaye encore !\n";
					}
					else{

						if($this->coup == 1){$this->score=3;}
						elseif ($this->coup >1 && $this->coup <=3) {$this->score=2;}
						else{$this->score=1;}

						echo "\nBravo ! Vous avez devine la reponse en $this->coup essai(s) ! Vous avez gagne $this->score points.\n";

						$this->scoreFinal+=$this->score;

						$reponseRejouer = $this->replay($prenomJoueur, $this->jeu, $this->scoreFinal, $this->difficultes);
					}
				} while ($reponseQuestion != $reponseMiniJeu);


			}
			fclose($lireQuestionReponse);

		} while ($reponseRejouer=="oui");

	}
}

?>
