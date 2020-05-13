
<!-- Un kata est un exercice dans lequel on vous donne un ensemble spécifique de règles et il peut devenir aussi spécifique que d'avoir un problème de domaine isolé. Cela peut être utilisé pour apprendre les concepts utiles nécessaires pour résoudre les problèmes dans ce domaine. Le faire à plusieurs reprises et essayer une solution différente à chaque fois permet de mieux comprendre comment résoudre cet ensemble de problèmes.

Dans cet article, je vais passer en revue les kata de Mars Rover et la façon dont je les ai utilisés pour apprendre à la fois le développement classique piloté par les tests ainsi que la refonte des choses et la façon d'appliquer les modèles de conception Command et State. L'application de modèles à un problème aussi simple est une ingénierie excessive. Cependant, cela fournit un guide pour apprendre sur la refactorisation et pour former un sens où ils peuvent être appliqués, c'est un très bon exercice. -->

Il s'inspire en partie de deux livres, TDD By Example de Kent Beck et 4 Rules of Simple Design de Corey Haines.

Dans TDD by Example, Kent Beck guide le lecteur à travers une description détaillée des avantages et de la prise de décision test par test, ainsi que certains principes de conception. Il suggère de faire une liste de choses à faire des cas de test comme un pointeur vers ce que l'on veut réaliser. Cela illustre l'intérêt d'avoir toujours un plan et d'éviter la programmation par coïncidence. Ce livre enseigne également quels tests choisir et quelle voie suivre en fonction des limites de la conception en cours de route. Il présente également le modèle de conception Value Object, ainsi que la technique de refactorisation rouge-vert.

Dans 4 Rules of Simple Design, Corey Haines fait la distinction entre les tests d'état et les tests de comportement, ainsi que la façon dont les tests pilotent la conception et la conception du code alimentent la façon dont nous écrivons les tests.

### Règles métier et premiers tests

Une façon d'examiner les règles métier consiste à les diviser en deux catégories: indicative et optative. Il s'agit d'une idée de Richard Wild.

Les règles indicatives sont des spécifications qui ne changent pas ou sont faciles à changer, comme l'état mort et vivant d'une cellule dans le jeu de la vie de Conway, ou le monde a une taille dans le cas de Mars Rover.

Les règles facultatives sont celles qui changent et contiennent souvent «si» et «devrait» dans le contenu de leur formulation. Ce sont les règles que nous devons coder et qui influencent notre conception. L'ordre dans lequel on les met en œuvre influence également la façon dont nous concevrions les choses. Selon le kata, il est souvent préférable de choisir la règle la plus simple et de commencer à partir de là, en particulier lorsque d'autres règles dépendent d'autres règles à mettre en œuvre en premier. Dans d'autres cas, le choix d'une règle différente entraînera une mise en œuvre différente et souvent originale de l'exercice.

Dans le cas de ce kata, les règles indicatives sont celles concernant la taille du monde, ainsi que le nombre de commandes. Les règles optatives concernent les commandes elles-mêmes.

Après un petit débat intérieur, j'ai choisi de tester que le mobile reste dans la même position avec une commande vide. Cela prépare le terrain pour le prochain test, car j'ai décidé de modifier les coordonnées avant de concevoir l'algorithme de rotation.
Après TPP, on renvoie un littéral comme l'étape la plus simple pour réussir le test.
On est passé à un test paramétré car on va avoir des cas de test très similaires et notre intention serait plus explicite.
A décidé que la chose la plus simple, et celle qui ferait un plus grand saut serait de renvoyer une variable. Après cela, nous avons également extrait la mise en forme de la chaîne affichée et l'avons gardée cohérente avec les règles métier. Nous avons également décidé que la position initiale serait injectée dans le constructeur.

### Codage vers la première abstraction

À ce stade, on peut commencer à construire l'algorithme de rotation ou l'algorithme de déplacement. Nous avons décidé d'aller avec le déménagement. Une fois que nous avons commencé, le prochain dilemme était soit de commencer à parcourir le monde, le chemin "pluvieux" où nous devions commencer à concevoir un cas de bord ou le chemin "heureux", pour aller dans d'autres directions.

Nous avons opté pour le chemin heureux. Il introduit une certaine duplication. Cela nous indique que nous pourrions utiliser une abstraction pour déplacer le rover. Dans un contexte plus large, cela est pertinent pour la user story que l'on met en œuvre. Mettons-nous en œuvre quelque chose de plus «livrable» et qui fonctionne tout de suite. Il s'agit d'une utilisation immédiate pour un propriétaire de produit ou l'entreprise. Opter pour le cas de bord serait de rendre les choses plus robustes. Donc, une chose importante à prendre en compte dans le monde réel est ce dont l'entreprise a besoin en ce moment et prioriser quelle fonctionnalité est mise en œuvre en premier.

Nous revenons à notre décision initiale de commencer à tester pour changer les coordonnées étant donné une direction. Initialement, le test échoue et introduit un conditionnel afin d'incrémenter la variable Y, ainsi que de refactoriser la commande dans un champ.
Le test échoue avec plusieurs commandes de déplacement, nous décidons donc de diviser l'entrée et d'analyser plusieurs commandes.
Nous décidons de dupliquer la condition if afin de pouvoir nous déplacer vers le sud.
Code de nettoyage nous refactorisons les littéraux Nord et Sud aux champs
Clarifier l'intention de la façon dont nous vérifions la direction en appliquant le SRP
Clarifier les concepts autour de ce qui est entré et de ce qu'est une commande en renommant les variables
Clarifier l'intention en extrayant une méthode qui gère le déplacement en appliquant SRP
Nettoyage du code à l'intérieur de MarsRover - formatage des coordonnées extraites dans le champ. Suppression de la duplication et clarification de l'intention de se déplacer verticalement.
Implémentation de la méthode de déplacement horizontal et extraction qui exprime l'intention des mouvements horizontaux.
Code de nettoyage - clarifie la façon dont la chaîne est divisée en caractères individuels.
À ce stade, nous avons une classe plus grande qui doit être refactorisée. Donc, la décision à ce stade est de savoir si l'on en a assez pour une abstraction pour la logique en mouvement ou pour continuer afin de découvrir un autre modèle dans le code. Nous avons décidé de poursuivre la mise en œuvre de la logique de retournement, car nous pouvions toujours revenir à l'abstraction du passage en classe qui saurait par lui-même la voie à suivre.
Continuant en encapsulant la logique de coordonnées dans une classe. Nous utilisons "Extraire un objet de paramètre" d'IntelliJ afin de refactoriser automatiquement le constructeur pour utiliser également Coordinate dans le test. Renommé Coordinate to Position Suppression de l'ancienne implémentation et délégation de toutes les responsabilités de coordonnées à l'objet Position Ajout d'un test d'échec pour tourner à droite La chose la plus simple pour réussir le test Responsabilité refactorisée de Position Implemented tournant à droite deux fois. Répéter pour toutes les possibilités de virage jusqu'à ce que les virages à droite et à gauche soient mis en œuvre. Test ajouté pour les deux directions passant. À ce stade, il semble y avoir beaucoup d'envie de fonctionnalités entre l'objet Position et le Mars Rover, nous renommons donc Position en Rover et MarsRover en MarsRoverController. Refactorisé pour utiliser l'immuabilité et renommé la coordonnée vers le mobile car il a un comportement et la position indiquerait simplement être un wrapper.

### Refactorisation des modèles d'état et de commande
Nous avons décidé d'abstraire les détails de commutation Cardinal dans une classe autonome. Déléguer un appel à ses propres méthodes right () et left () utiliserait le modèle State et permettrait au cardinal de gérer la commutation. D'une certaine manière, cela ressemble à une molécule d'eau qui a un gros atome au milieu et deux voisins à sa gauche et sa droite. Nommer les méthodes du sous-type Cardinal State à gauche () et à droite () éloigne l'implémentation de la rotation et la rend plus réutilisable dans un autre contexte. Il fait disparaître le commutateur et confie également la responsabilité de la commutation de l'état au cardinal et non au mobile. La charge cognitive de la classe est moindre car avant la refactorisation, la classe Rover devait connaître tous les mappages et maintenant les mappages sont autonomes.

L'interface Cardinal avec des implémentations et teste son comportement. Chaque point cardinal est autonome, ne connaissant que ses coordonnées droite et gauche.
Refactorisation pour utiliser le Cardinal dans le constructeur et initialiser en utilisant une méthode d'usine dans le test. Remplacement de la chaîne par la classe Cardinal dans le Rover
Déléguer la logique en mouvement pour être autonome dans le Cardinal. Observation de l'implémentation le long de l'ancienne dans chacune des conditions jusqu'à ce que nous puissions avoir un appel unique et que tous les tests soient réussis avec l'appel refactorisé.
Ça aussi

Refacteur complet de Rover en appel polymorphe au cardinal avec motif d'état. Renommer MarsRover en MarsRoverController et Cardinal en Rover Le code est plus facile à suivre et à lire.
Nettoyer le code, déplacer les choses localement pour plus de lisibilité et pour garder les choses près de l'endroit où le comportement est implémenté
La dernière étape consiste à montrer l'utilisation du modèle de commande en faisant abstraction des appels d'exécution de commande dans des objets de commande. Nous utilisons une interface de commande et une CommandFactory. Commencement de la refactorisation de l'invocation aux commandes au lieu du contrôleur. L'observation pour vérifier les tests passe toujours avec le nouveau refactor.
Touche finale: déplacer les commandes dans leur propre dossier, changer le conditionnel pour utiliser un HashMap pour stocker les commandes et nommer les chaînes de commande en conséquence.
Et c'est tout, en condensant les concepts appris au cours des deux premières semaines d'apprentissage à l'aide du kata Mars Rover.

Cela a été fait au cours de quelques séances de programmation en binôme par Simion Iulian Belea et Sam Davies.
