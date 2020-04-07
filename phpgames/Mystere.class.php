<?php

/**
* Class Mystère (nombre mystère) enfant de Jeu
*/
class Mystere extends Jeu{

	protected $partie = 1;
	private $jeu = "Nombre Mystere";
	private $nombreMax;
	private $min = 0;
	private $max;
	private $score;


	function niveauDifficultes($prenomJoueur){
		echo "\n=== Niveaux de difficultes disponibles === \n";
		echo "1. Debutant (entre 0 et 100)\n";
		echo "2. Intermediaire (entre 0 et 500)\n";
		echo "3. Expert (entre 0 et un nombre que vous choisirez vous-meme)\n";

		echo "===> Saisissez un niveau de difficultes : ";
		$difficultes=lire();

		switch ($difficultes) {
			case '1':
				$this->nombreMax = 100;
				$this->difficultes = "Debutant";
				break;

			case '2':
				$this->nombreMax = 500;
				$this->difficultes = "Intermediaire";
				break;

			case '3':
				echo "\nVous avez selectionne le mode expert.\n===> Veuillez saisir votre nombre maximum: ";
				$this->difficultes = "Expert";
				$this->nombreMax=lire();
				do {
					if($this->nombreMax<=500){
						echo "\nSi vous souhaitez un nombre inferieur a 500, veuillez choisir un niveau de difficultes inferieur.\n";
						$this->niveauDifficultes($prenomJoueur);
					}
				} while($this->nombreMax <= 500);
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

		$ecrire = new Score;

		do{
			$this->score = 0;
			$this->min = 0;
			$this->max = $this->nombreMax;

			echo "\nPartie numero $this->partie\n";
			echo "================\n";

			$mystere= rand()%$this->nombreMax;
			$nbEssai= 1;

			do{
				echo "Entrez un nombre entre $this->min et $this->max : ";
				$proposition= (int)( lire() );
				echo "\n";

				if ( $proposition > $mystere ){
					echo "Le nombre mystere est plus PETIT que $proposition. \n";
					$this->max = $proposition;
				}
				elseif ( $proposition < $mystere ){
					echo "Le nombre mystere est plus GRAND que $proposition. \n";
					$this->min = $proposition;
				}
				else{
					if ($nbEssai>10){$this->score=0;}
					elseif ($nbEssai<=10 && $nbEssai >=8){$this->score=1;}
					elseif ($nbEssai<8 && $nbEssai >=6){$this->score=2;}
					elseif ($nbEssai<6 && $nbEssai >=5){$this->score=3;}
					elseif ($nbEssai<5 && $nbEssai >=2){$this->score=4;}
					elseif ($nbEssai<2 && $nbEssai > 0){$this->score=5;}

					echo "Felicitation, vous avez trouve le nombre mystere ! ($proposition) en $nbEssai essais. Vous avez un score de $this->score points ! \n\n";

					$ecrire->ecrireResultatDansTXT($prenomJoueur, $this->jeu, $this->score, $this->difficultes);
				}

				$nbEssai++;

			} while(  $proposition != $mystere && $nbEssai < 500 );

			$reponseRejouer = $this->replay($prenomJoueur);

		} while( $reponseRejouer == "oui" );

	}

}


?>
