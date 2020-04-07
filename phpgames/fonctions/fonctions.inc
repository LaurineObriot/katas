<?php
	/** Fonction de lecture au clavier : **/
	/**************************************/
	function lire() {
		$descritpeur= fopen("php://stdin", "r");
		$entree= trim( fgets( $descritpeur ) );
		fclose($descritpeur);

		return $entree;
	};
?>
