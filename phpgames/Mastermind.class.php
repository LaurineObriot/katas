<?php

/**
* Class Mastermind enfant de Jeu
*/
class Mastermind extends Jeu{

	public $partie = 0;
	protected $jeu = "MasterMind";
	private $bienPlace;
	private $malPlace;
	private $inexistant;
	protected $essai;
	protected $nombreMax;
	protected $difficultes;


	function niveauDifficultes($prenomJoueur){
		echo "\n=== Niveaux de difficultes disponibles === \n";
		echo "1. Debutant (Le nombre maximum est 4)\n";
		echo "2. Intermediaire (Le nombre maximum est 7)\n";
		echo "3. Expert (Le nombre maximum est 9)\n\n";

		echo "===> Saisissez un niveau de difficultes : ";
		$difficultes=lire();

		switch ($difficultes) {
			case '1':
				$this->nombreMax = 4;
				$this->difficultes = "Debutant";
				break;

			case '2':
				$this->nombreMax = 7;
				$this->difficultes = "Intermediaire";
				break;

			case '3':
				$this->nombreMax = 9;
				$this->difficultes = "Expert";
				break;

			default:
				$this->niveauDifficultes($prenomJoueur);
				break;
		}

		echo "\n";
		$this->debutJeu($prenomJoueur);
	}



	function debutJeu($prenomJoueur){
		$code = array();

		do {

			for ($i=0; $i < $this->nombreMax; $i++) {
				$code[$i] = $i;
			}
			$nombreAleatoire = "";
			$combinaison = "";

			for ($i=0; $i < 4; $i++) {
				$nombreAleatoire = array_rand($code);
				$combinaison[] = $code[$nombreAleatoire];
				unset($code[$nombreAleatoire]);
			}

			$combinaisonString = $combinaison[0].$combinaison[1].$combinaison[2].$combinaison[3];

			// echo "===".$combinaisonString."===\n";
			echo "\n";
			$this->essai = 0;

			do {
				$this->inexistant = 0;
				$this->malPlace = 0;
				$this->bienPlace = 0;

				echo "===> Saisissez une combinaison de 4 chiffres: ";
				$reponseCombinaison=lire();

				for ($i=0; $i < sizeof($combinaison); $i++) {
					if($combinaison[$i] == $reponseCombinaison[$i]){
						$this->bienPlace++;
					}
					elseif(in_array($reponseCombinaison[$i], $combinaison)){
						$this->malPlace++;
					}

					else{
						$this->inexistant++;
					}
				}
				$this->essai++;

				echo "Vous avez $this->bienPlace pion(s) bien place(s)\n";
				echo "Vous avez $this->malPlace pion(s) mal place(s)\n";
				echo "Vous avez $this->inexistant pion(s) incorrect(s)\n";

				if($reponseCombinaison != $combinaisonString){
					echo "\nNouvelle chance: \n";
				}
				else{
					echo "\nFelicitation, vous avez trouve la bonne combinaison mystere ($combinaisonString) en $this->essai essais !\n";

					if($this->essai == 1){$this->score=10;}
					elseif ($this->essai >1 && $this->essai <=3) {$this->score=5;}
					elseif ($this->essai >3 && $this->essai <=5) {$this->score=4;}
					elseif ($this->essai >5 && $this->essai <=7) {$this->score=3;}
					elseif ($this->essai >7 && $this->essai <=9) {$this->score=2;}
					else{$this->score=1;}

					$ecrire = new Score;
					$ecrire->ecrireResultatDansTXT($prenomJoueur, $this->jeu, $this->score, $this->difficultes);
				}


			} while ($reponseCombinaison != $combinaisonString);


			$reponseRejouer = $this->replay($prenomJoueur);


		} while ($reponseRejouer == "oui");
	}




}


?>
