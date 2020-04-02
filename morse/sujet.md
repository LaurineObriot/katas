## Sujet :
Ecrire une fonction qui sache décoder du Morse : les courts bips seront indiqués par des points (.) et les longs bips seront indiqués par des tirets (-). Chaque lettre doit être espacée de la suivante par un espace, et chaque mot doit être espacé du suivant par trois espaces. Se limiter aux lettres de l'alphabet et aux chiffres de 0 à 9

decoder("... --- ...") = "sos" decoder("-... --- -. .--- --- ..- .-.") = "bonjour"

Voici l'alphabet morse :

A : .- B : -... C : -.-. D : -.. E : . F : ..-. G : --. H : .... I : .. J : .--- K : -.- L : .-.. M : -- N : -. O : --- P : .--. Q : --.- R : .-. S : ... T : - U : ..- V : ...- W : .-- X : -..- Y : -.-- Z : --..

1 .---- 2 ..--- 3 ...-- 4 ....- 5 ..... 6 -.... 7 --...8 ---.. 9 ----. 0 -----.

## Contraintes
Mémoire RAM disponible : 256Mo
Durée maximum d’exécution : 6 secondes
Le programme doit lire les entrées depuis l’entrée standard
Le programme doit écrire la réponse dans la sortie standard
