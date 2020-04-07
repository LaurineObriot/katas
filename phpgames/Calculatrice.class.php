<?php

/**
* Class Calculatrice fille de Jeu
*/
class Calculatrice extends Jeu{

	private $nombre1;
	private $nombre2;

	function niveauDifficultes($prenomJoueur){
		echo "\n=== Operations Mathematiques disponibles === \n";
		echo "1. Addition\n";
		echo "2. Soustraction\n";
		echo "3. Multiplication\n";
		echo "4. Division\n";

		echo "\n===> Saisissez une operation a effectuer: ";
		$operation=lire();
		echo "\n";

		switch ($operation) {
			case '1': // Addition
				$operationMaths = "Addition";
				break;

			case '2': // Soustraction
				$operationMaths = "Soustraction";
				break;

			case '3': // Multiplication
				$operationMaths = "Multiplication";
				break;

			case '4': // Division
				$operationMaths = "Division";
				break;

			default:
				echo "Je n'ai pas compris votre reponse: \n";
				$this->niveauDifficultes($prenomJoueur);
				break;

		}

		echo "================\n";
		echo "=== $operationMaths ===\n";
		echo "================\n";

		$raccourci = lcfirst($operationMaths);
		$this->$raccourci($prenomJoueur);
	}




	function addition($prenomJoueur){

		do{
			$nombre1 = 0;
			$nombre2 = 0;

			echo "===> Saisissez un 1er nombre: ";
			$this->nombre1=lire();
			echo "===> Saisissez un 2eme nombre a additionner: ";
			$this->nombre2=lire();

			$addition = $this->nombre1 + $this->nombre2;

			echo "====> L'addition de $this->nombre1 + $this->nombre2 vaut $addition\n\n";

			$reponseRejouer = $this->replay($prenomJoueur);

		} while ($reponseRejouer == "oui");
	}


	function soustraction($prenomJoueur){
		do{
			$nombre1 = 0;
			$nombre2 = 0;

			echo "===> Saisissez un 1er nombre: ";
			$this->nombre1=lire();
			echo "===> Saisissez un 2eme nombre a soustraire: ";
			$this->nombre2=lire();

			$soustraction = $this->nombre1 - $this->nombre2;

			echo "====> La soustraction de $this->nombre1 - $this->nombre2 vaut $soustraction\n\n";

			$reponseRejouer = $this->replay($prenomJoueur);

		} while ($reponseRejouer == "oui");
	}


	function multiplication($prenomJoueur){
		do{
			$nombre1 = 0;
			$nombre2 = 0;

			echo "===> Saisissez un 1er nombre: ";
			$this->nombre1=lire();
			echo "===> Saisissez un 2eme nombre a multiplier: ";
			$this->nombre2=lire();

			$multiplication = $this->nombre1 * $this->nombre2;

			echo "====> La multiplication de $this->nombre1 x $this->nombre2 vaut $multiplication\n\n";

			$reponseRejouer = $this->replay($prenomJoueur);

		} while ($reponseRejouer == "oui");
	}


	function division($prenomJoueur){
		do{
			$nombre1 = 0;
			$nombre2 = 0;

			echo "===> Saisissez un 1er nombre: ";
			$this->nombre1=lire();
			echo "===> Saisissez un 2eme nombre a diviser: ";
			$this->nombre2=lire();

			$division = $this->nombre1 / $this->nombre2;

			echo "====> La division de $this->nombre1 / $this->nombre2 vaut $division\n\n";

			$reponseRejouer = $this->replay($prenomJoueur);

		} while ($reponseRejouer == "oui");
	}



}

?>
