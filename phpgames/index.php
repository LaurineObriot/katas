<?php

// Inclusion dynamique des différentes classes du projet
require_once("fonctions/fonction.inc");
function chargerClasse($classe){
	require_once $classe.'.class.php';
}
spl_autoload_register('chargerClasse');


echo "Bonjour et bienvenue dans mon programme de minijeux en PHP !\n";

// Instanciation de la classe Joueur qui permet de demander le nom d'utilisateur à la personne qui joue
$joueur = new Joueur();
$prenomJoueur = $joueur->setPrenom();

initialisation($prenomJoueur);

// Fonction initialisation, qui affiche le menu principal des jeux
function initialisation($prenomJoueur){

	echo "=========================\n";
	echo "===== MENU DES JEUX =====\n";
	echo "=========================\n";
	echo "Bonjour $prenomJoueur, a quel jeu souhaitez-vous vous mesurer aujourd'hui ?\n";
	echo "1. Nombre Mystere\n";
	echo "2. Mastermind\n";
	echo "3. Vrai ou Faux \n";
	echo "4. Questions/Reponses\n";
	echo "5. Calculatrice basique\n";

	if(is_dir("scores")){
		echo "\n6. Lire le fichier des Scores";
		echo "\n7. Effacer le fichier des Scores\n";
	}
	echo "\n8. A propos";
	echo "\n9. Quitter le jeu et retourner sur le bureau\n";

	echo "\n===> Quel est votre choix ? ";
	$numero=lire();
	echo "\n";

	switch($numero) {
		case '1':
			$nomDuJeu = "Nombre Mystere";
			$regles = "Le but du jeu consiste a trouver le nombre mystere (genere aleatoirement par l'ordinateur) en un minimum d'essais. A chaque tour, l'ordinateur vous indique si le nombre que vous avez saisi est superieur ou inferieur au nombre mystere. Une fois que vous aurez trouve le bon nombre, votre score sera enregistre dans un fichier .txt. Bon jeu !";
			$mystere = new Mystere();
			$mystere->messageBienvenue($prenomJoueur, $nomDuJeu, $regles);
			break;

		case '2':
			$nomDuJeu = "Mastermind";
			$regles = "Le but du jeu consiste a trouver le code secret genere par l'ordinateur. Vous devez proposer une combinaison et l'ordinateur vous indiquera les lettres qui sont correctement placees et celles qui ne le sont pas. Une fois que vous avez trouve la bonne combinaison, votre score sera enregistre dans un fichier .txt. Bon jeu !";
			$mastermind = new Mastermind();
			$mastermind->messageBienvenue($prenomJoueur, $nomDuJeu, $regles);
			break;

		case '3':
			$nomDuJeu = "Vrai ou Faux";
			$regles = "Le but du jeu est de determiner si l'addition des nombres aleatoires generes PAR L'ORDINATEUR est superieur ou inferieur a l'addition des nombres aleatoires generes PAR L'ORDINATEUR, POUR VOUS. Une fois que vous avez trouve la bonne combinaison, votre score sera enregistre dans un fichier .txt. Bon jeu !";
			$vraiOuFaux = new VraiOuFaux();
			$vraiOuFaux->messageBienvenue($prenomJoueur, $nomDuJeu, $regles);
			break;

		case '4':
			$nomDuJeu = "Questions/Reponses";
			$regles = "Le but du jeu est de trouver la reponse a la question posee par l'ordinateur. Attention a bien ecrire chaque mot en minuscule et sans caractere accentue. Un compteur comptera le nombre d'essais que vous avez eu besoin et enregistrera votre score dans un fichier .txt. Bon jeu !";
			$questionReponse = new QuestionReponse();
			$questionReponse->messageBienvenue($prenomJoueur, $nomDuJeu, $regles);
			break;

		case '5':
			$nomDuJeu = "Calculatrice";
			$regles = "Il ne s'agit pas vraiment d'un mini-jeu mais plus d'une mini application qui a une utilite quasi quotidienne. Vous choisissez l'operation que vous souhaitez effectuer, et vous indiquez les nombres que vous souhaitez manipuler. Bon calculs !";
			$calculatrice = new Calculatrice();
			$calculatrice->messageBienvenue($prenomJoueur, $nomDuJeu, $regles);
			break;

		case '6': // Afficher les scores
			if(is_dir("scores")){
				Score::menuScore($prenomJoueur);
				break;
			}
			else{
				echo "Vous ne pouvez pas lire des scores qui n'existent pas !\n\n";
				initialisation($prenomJoueur);
				break;
			}

		case '7':
			if(is_dir("scores")){
				Score::supprimerResultatDansTXT($prenomJoueur);
				break;
			}
			else{
				echo "Vous ne pouvez pas supprimer des scores qui n'existent pas !\n\n";
				initialisation($prenomJoueur);
				break;
			}

		case '8': // A Propos
			echo "====================================\n";
			echo "============= A PROPOS =============\n";
			echo "====================================\n";
			echo "Ce programme de mini-jeux a ete realise par Anthony ROBIN dans le cadre du Cours de PHP du Master1 DNR2I a Caen. Il a ete fait en PHP orientee objet (avec des classes).\nJ'ai utilise l'editeur Sublime Text 2 / 3 pour ce programme.\n";

			do{
				echo "\n==> Saisir \"quitter\" pour revenir au menu principal: ";
				$quitterAPropos=lire();
				if($quitterAPropos=="quitter"){
					echo "\n\n";
					initialisation($prenomJoueur);
				}
			} while($quitterAPropos != "quitter");
			break;

		case '9': // Quitter le programme
			do{
				echo "$prenomJoueur, voulez-vous quitter le jeu et retourner au bureau ? (oui ou non) : ";
				$reponseQuitter=lire();
				if ($reponseQuitter == "oui") {
					echo "\n";
					echo "Merci d'avoir joue $prenomJoueur, a bientot !\n";
					exit;
				}
				elseif ($reponseQuitter == "non") {
					echo "\n";
					initialisation($prenomJoueur);
				}
			} while($reponseQuitter != "oui" && $reponseQuitter != "non");
			break;

		default: // Action à faire si le choix de l'utilisateur ne correspond à aucun des cas précédents
			echo "\nDesole, je n'ai pas compris votre choix.\n";
			initialisation($prenomJoueur);
			break;
	}
}


echo "\n\n";

?>
