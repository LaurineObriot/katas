## Comment ça marche ?

Vous n’avez qu’à lancer ./test.sh pour avoir le résultat des tests :

`Testing scenario #1... success
Testing scenario #3... success
Testing scenario #2... success
Testing scenario #4... success`

Pour chaque fichier d’entrées, test.sh compare les sorties du programme avec les sorties attendues. Bien entendu, il faut que les fichiers respectent les formats suivants :
* in[0-9]+.txt pour les fichiers d’entrées et
* out[0-9]+.txt pour les fichiers de sorties.


Lorsqu’une erreur se produit, la suite de tests est interrompue et un diff est affiché :
`Testing scenario #1... failure
1c1
< 1
---
> 2`

Ici, la réponse attendue est 1 mais la réponse affichée par le script est 2.


## Analyse du problème

Puisque l’inconnue du problème est en morse autant considérer le dictionnaire comme étant lui aussi en morse. Voila un exemple de code permettant de récupérer les entrées.

`$ascii_morse_mapping = {'A'=>'.-', 'B'=>'-...', [...] 'Y'=>'-.--', 'Z'=>'--..'}
$sequence = gets.chomp
$dico = (1..gets.to_i).map do
  result = ''
  gets.chomp.each_char{ |char| result << $ascii_morse_mapping.fetch(char) }
  result
end`

On peut remarquer qu’un mot en morse peut correspondre à des mots différents en ASCII. Par exemple .........- peut se traduire par SHIT ou bien HIV. Ainsi $dico est succeptible de contenir des doublons.

L’objectif est de trouver le nombre de découpages possibles de $sequence en mots de $dico.

## Rentrons dans le tas !
On va d’abord commencer par un algo naïf.

Dans les fichiers de test 1 et 2, la séquence correspond à un seul et unique mot du dictionnaire. En conséquence, la solution suivante va fonctionner :

`$dico.select{|word| word == sequence}.size`
L’exemple n°3 se complique : plusieurs mots forment la séquence. On va donc, pour chaque mot, tenter de le faire correspondre avec le début de la séquence. S’il correspond, alors on recommencera l’opération avec le reste de la séquence. Lorsque la séquence est vide, on a une seule interprétation possible.

Cet algorithme se fait très bien avec une fonction récursive :

`def possibilities(seq)
  return 1 if seq.size == 0

  $dico.reduce(0) do |total, word|
    if word == seq[0, word.size]
      total += possibilities(seq[word.size, seq.size - word.size])
    end
    total
  end
end`
La fonction possibilities donne donc le nombre de combinaisons possibles pour la séquence en paramètre. Lorsque l’on appelle cette fonction avec $sequence en paramètre on a bien le résultat attendu pour les tests 1, 2 et 3. Le test n°4, lui, ne termine pas en un temps acceptable…

## Révélation & Optimisation
L'algorithme de résolution peut être vu comment un arbre :

* Les noeuds de l’arbre contiennent la sequence dont on cherche à déterminer le nombre d’interprétations.
* Les branches de l’arbre représentent la correspondance d’un mot du dictionnaire en tête de cette séquence.
* Les feuilles de l’arbre sont des noeuds dont la séquence est vide.

La racine de l’arbre contient $sequence. La fonction possibilities(seq) retourne le nombre de feuilles pour une séquence donnée. Le nombre de feuilles est calculé récursivement => c’est la somme du nombre de feuilles de chacun des sous-arbres du noeud.

## Memoize
La fonction possibilities est dite pure. En effet, à l’exception de son usage de la variable $dico que l’on considère plutôt comme une constante, possibilities ne tient pas compte du contexte d’exécution mais uniquement de ses paramètres.. Pour les mêmes paramètres, elle retournera toujours le même résultat.

Cette propriété va permettre de conserver en mémoire les résultats des appels à la fonction. De cette manière, on ne calculera pas un sous arbre déjà calculé.

`$possibilities_memory = {}
def possibilities(seq)
  return 1 if seq.size == 0
  return $possibilities_memory[seq] if $possibilities_memory.has_key?(seq)

  result = $dico.reduce(0) do |total, word|
    if word == seq[0, word.size]
      total += possibilities(seq[word.size, seq.size - word.size])
    end
    total
  end

  $possibilities_memory[seq] = result
end`
Cette optimisation permet de terminer dans un temps raisonnable l’exemple n°4 (~7 secondes). L’algorithme n’a quasiment pas changé !

## Dictionnaire
Pour optimiser à nouveau le programme, il faut regarder d’un peu plus près son fonctionnement interne. Pour cela, on peut instrumenter le code pour obtenir certaines métriques sur les opérations les plus courantes. On peut également faire une analyse de complexité. Je vais utiliser l’instrumentation.

Voici ce que j’obtiens sur l’exemple n°4 :

`{ :calls   =>     1284,  # Nombre d'appel à la fonction
  :nodes   =>     1194,  # Nombre de sous-arbres parcourus
  :cuts    =>       89,  # Nombre de sous-arbres non recalculés
  :match   =>     1283,  # Nombre de branches crées
  :unmatch => 11274853 } # Nombre de branches non-crées
`
On peut voir que l'algorithme calcule assez peu de sous arbres (de l’ordre d’un millier). Par contre il fait beaucoup d’essais pour faire correspondre les mots du dictionnaire au début de la séquence (de l’ordre d’une dizaine de millions). Pour chaque noeud calculé on va tester chaque mot du dictionnaire avec le début de la séquence. Il y a 9444 mots dans le dictionnaire, on a donc 9444 * 1194 comparaisons.

Pour éviter de parcourir le dictionnaire à chaque noeud, on va prendre l’approche inverse. On va chercher à faire correspondre le début de la séquence à un mot du dictionnaire. Pour cela on va parcourir le début de la séquence, et vérifier si oui ou non il existe une correspondance dans le dictionnaire.

Pour limiter le nombre d’appel au dictionnaire on ne s’intéresse qu’aux séquences pouvant effectivement être un mot. Cette limite est mise en place en ne prenant que des préfixes de la taille des mots du dictionnaire.

Voici le corps de ce que pourrait être notre nouvelle méthode possibilities.

`result = 0
min    = $dico.min_size
max    = [$dico.max_size, seq.size].min
for size in (min..max)
  if $dico.exists?(seq[0, size])
    result += possibilities(seq[size, seq.size - size])
  end
end
result`

L’une des premières choses que l’on remarque c’est que $dico ne semble plus être une simple liste. Comme on l’a vu plus tôt, plusieurs mots peuvent avoir une même représentation en morse. En plus de savoir si un préfixe de seq est dans le dictionnaire, il faut savoir combien de fois il y est. L’algorithme change légèrement afin de prendre en compte ce détail :

`result = 0
min    = $dico.min_size
max    = [$dico.max_size, seq.size].min
for size in (min..max)
  count = $dico.count(seq[0, size])
  if count > 0
    result += count * possibilities(seq[size, seq.size - size])
  end
end
result`
Voici maintenant le code du dictionnaire :

`class Dictionnary
  def initialize
    @_hash = {}
  end

  def push(morse)
    @_max_size = nil
    @_min_size = nil
    @_hash[morse] = count(morse) + 1
  end

  def count(morse)
    @_hash[morse] || 0
  end

  def max_size
    @_max_size ||= @_hash.keys.max_by(&:size).size
  end

  def min_size
    @_min_size ||= @_hash.keys.min_by(&:size).size
  end
end`

Cette implémentation permet de résoudre le test n°4 instantanément (~20 millisecondes). Voici les résultats donnés par le même type d’instrumentation que précédemment :

`{ :calls   =>  1284,  # Nombre d'appel à la fonction
  :nodes   =>  1224,  # Nombre de sous-arbres parcourus
  :cuts    =>    59,  # Nombre de sous-arbres non recalculés
  :match   =>  1283,  # Nombre de branches crées
  :unmatch => 48901 } # Nombre de branches non-crées`

Le nombre de recherches dans le dictionnaire qui n’aboutissent pas est beaucoup moins élevé (il s’agit d’un facteur 1 000).

La différence de sous arbres non recalculé vient probablement de la différence dans l’ordre de parcours. Cette différence laisse penser que l’on pourrait ordonner l’exploration pour maximiser le nombre de sous-arbres non explorés.

Pour plus de lisibilité, le code est refactoré en trois classes : Dictionnary, Word et Problem.

## Mémoire
Un dernière optimisation consiste à ne plus utiliser des chaines de caractères lors des récursions ou lors de la mémoization. En effet, on préfèrera utiliser un indice associé à une variable d’instance de la classe Problem. De cette manière Problem reste fonctionnel, dans le sens ou le résultats de ses méthodes ne dépendent que de lui et de ses propres variables.
