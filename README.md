# katas

Dépot pour garder une trace de tout mes katas & des solutions aux défis codinGame.

## Codingame :
Les solutions sont dans les fichiers Solution.java présent dans les différents réperoires. Ils sont à exécuter dans l'IDE en ligne de http://codingame.com car seul le code est important, les classes n'étant pas toutes nommées Solution.

## Kata potter :
http://codingdojo.org/kata/Potter/

## Fizzbuzz :
http://codingdojo.org/kata/FizzBuzz/

## MasterMind :
http://codingdojo.org/kata/Mastermind/

## RomanNumerals :
http://codingdojo.org/kata/RomanNumerals/

## phpgames
Contient Calculatrice, Nombre à deviner, mastermind & questions culture générale.

## StringCalculator
http://codingdojo.org/kata/StringCalculator/

## Diamond
http://codingdojo.org/kata/Diamond/

## Salle de sport
##### Énoncé
Je peux créer de nouvelles formules, au mois ou à l’année, avec les règles suivantes:

un prix de base est fixé
auquel on applique 30% de réduction si le client souscrit à un abonnement annuel
En tant qu’étudiant, j’ai 20% de réduction sur le prix de mon abonnement

Je peux voir le chiffre d’affaire en fonction du nombre d’abonnements en cours pour un mois donné

Après s’être abonné, l’abonné reçoit un email récapitulatif

Je peux changer le prix d’une formule

Un abonnement se renouvelle automatiquement

Au bout de 3 ans d’abonnement, l’abonné reçoit un email de remerciement

##### EventStorming
<img src="https://github.com/LaurineObriot/katas/blob/master/ressources/oui.jpg">

##### Implémentation
Utilisez le langage que vous voulez

Mais pas de framework
Pas de HTTP, donc pas de contrôleurs

Pas de base de données

Tout en mémoire
Tester toutes les fonctionnalités

Gestionnaire de dépendances autorisé pour ajouter une lib de tests

Le métier ne parle que français, donc le code métier doit être en français

Organiser le code en fonction des Bounded Contexts

On ne veut pas voir d’AbonnementService !


## Puissance 4
L'implémentation proposée consiste en :

Une grille
Un analyseur
Un arbitre
Une application
Une vue
Les responsabilités de ces différents éléments sont :

#### Grille
Une grille de Puissance 4 :

donne l’état de ses 42 cellules (7 colonnes de 6 rangées)
accepte jusqu’à 6 jetons dans une colonne
se vide
se représente sous forme de texte
#### Analyseur
Un Analyseur de Puissance 4 :

collabore avec une grille
sait si un joueur a gagné :
ligne de 4 jetons de même couleur
horizontale, verticale, diagonale vers le haut,
diagonale vers le bas
sait si la partie est nulle (grille est remplie, aucune ligne gagnante)
##### Arbitre
Un Arbitre de Puissance 4 :

dit à qui le tour de jouer (jaune ou rouge)
Jaune commence la partie
entre le coup du joueur courant
sait si la partie est en cours, gagnée (et par quel joueur) ou nulle
#### Application
Une Application Puissance 4 :

produit les affichages sur la console :
la grille
a qui le tour ("JAUNE" ou "ROUGE")
accepte à la console le numéro de colonne à jouer
s’arrête lorsque la partie est gagnée ou nulle
affiche le résultat de la partie (gagnant ou nulle)
#### Vue
Une Vue Puissance 4 :

envoie des messages (String) à la console
reçoit des commandes (String) de la console
Supprimer le couplage entre la classe Application et la Console système

La classe Application :

assure la boucle de jeu
collabore avec un Arbitre, une Grille, une Vue
