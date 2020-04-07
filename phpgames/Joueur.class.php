<?php

/**
* Class Joueur
*/
class Joueur{

	public $prenom;

	public function setPrenom(){
		echo "Quel est votre prenom ? ";
		$prenom=lire();
		echo "\n";
		return $prenom;
	}

}

?>
