<?php


/**
* Class VraiOuFaux, fille de Jeu
*/
class VraiOuFaux extends Jeu{

	private $nombreMax;
	private $nombres;
	protected $partie = 1;
	private $jeu = "Vrai Ou Faux";

	function niveauDifficultes($prenomJoueur){
		echo "\n=== Niveaux de difficultes disponibles === \n";
		echo "1. Debutant (3 nombres entre 0 et 10)\n";
		echo "2. Intermediaire (4 nombres entre 0 et 50)\n";
		echo "3. Expert (5 nombres entre 0 et 100)\n\n";

		echo "===> Saisissez un niveau de difficultes : ";
		$difficultes=lire();

		switch ($difficultes) {
			case '1':
				$this->nombres = 3;
				$this->nombreMax = 10;
				$this->difficultes = "Debutant";
				break;

			case '2':
				$this->nombres = 4;
				$this->nombreMax = 50;
				$this->difficultes = "Intermediaire";
				break;

			case '3':
				$this->nombres = 5;
				$this->nombreMax = 100;
				$this->difficultes = "Expert";

				break;

			default:
				echo "\nDesole, je n'ai pas compris votre choix. \n";
				$this->niveauDifficultes($prenomJoueur);
				break;
		}

		$this->debutJeu($prenomJoueur);
		echo "\n";
	}


	function debutJeu($prenomJoueur){

		do {

			$nombreAleatoireOrdi = array();
			$nombreAleatoireJoueur = array();
			$sommeOrdi = 0;
			$sommeJoueur = 0;

			echo "\nPartie numero $this->partie\n";
			echo "================\n";


			for ($i=1; $i <= $this->nombres ; $i++) {
				$nombreAleatoireOrdi[$i] = rand(1, $this->nombreMax);
				$sommeOrdi += $nombreAleatoireOrdi[$i];
			}

			for ($i=1; $i <= $this->nombres ; $i++) {
				$nombreAleatoireJoueur[$i] = rand(1, $this->nombreMax);
				$sommeJoueur += $nombreAleatoireJoueur[$i];
			}


			echo "La somme des $this->nombres nombres aleatoires generes par l'ordinateur est: $sommeOrdi\n";


			do {
				echo "Pensez-vous que votre nombre aleatoire est SUPERIEUR a celui de l'ordinateur ? (vrai ou faux) : ";
				$reponseVraiOuFaux=lire();

				if($reponseVraiOuFaux=="vrai" && ($sommeJoueur > $sommeOrdi) ||
				   $reponseVraiOuFaux=="faux" && ($sommeJoueur < $sommeOrdi))
				{
					if($reponseVraiOuFaux=="faux"){$comparatif = "INFERIEUR";}
					elseif($reponseVraiOuFaux=="vrai"){$comparatif = "SUPERIEUR";}

					echo "\nBravo ! Votre nombre aleatoire est bien $comparatif a celui de l'ordinateur !\n";
					echo "Votre nombre: $sommeJoueur | Celui de l'ordinateur: $sommeOrdi\n";

					$score = 5;

					$ecrire = new Score;
					$ecrire->ecrireResultatDansTXT($prenomJoueur, $this->jeu, $score, $this->difficultes);

				}

				elseif($reponseVraiOuFaux=="faux" && ($sommeJoueur > $sommeOrdi) ||
					   $reponseVraiOuFaux=="vrai" && ($sommeJoueur < $sommeOrdi))
				{
					if($reponseVraiOuFaux=="faux"){$comparatif = "SUPERIEUR";}
					elseif($reponseVraiOuFaux=="vrai"){$comparatif = "INFERIEUR";}

					echo "\nDommage ! Votre nombre aleatoire etait bien $comparatif a celui de l'ordinateur !\n";
					echo "Votre nombre: $sommeJoueur | Celui de l'ordinateur: $sommeOrdi\n\n";

					$score = 0;

					$ecrire = new Score;
					$ecrire->ecrireResultatDansTXT($prenomJoueur, $this->jeu, $score, $this->difficultes);

				}

			} while ( $reponseVraiOuFaux != "vrai" && $reponseVraiOuFaux != "faux" );

			$reponseRejouer = $this->replay($prenomJoueur);

		} while($reponseRejouer == "oui");


	}

}


?>
