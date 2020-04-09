import { Analyseur } from '../analyseur'
import { Grille } from '../grille'

test('devrait savoir qu‘un joueur n‘a pas gagné sur une grille vide', () => {
  // Given
  const grille = new Grille()
  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLeJoueurAGagnéSurLaGrille()

  // Then
  expect(result).toBe(false)
})

test('devrait savoir qu\‘un joueur a gagné quand il y a 4 jetons rouges et 2 jetons jaunes sur la première colonne', () => {
  // Given
  const grille = new Grille()
  grille.ajouteUnJeton(0, 'JAUNE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'JAUNE')
  
  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLeJoueurAGagnéSurLaGrille()

  // Then
  expect(result).toBe(true)
})

test('devrait savoir qu‘un joueur n‘a pas gagné quand il n‘y a que 3 jetons rouges sur la première colonne', () => {
  // Given
  const grille = new Grille()
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLeJoueurAGagnéSurLaGrille()

  // Then
  expect(result).toBe(false)
})

test('devrait savoir qu‘un joueur a gagné quand il n‘y a que des jetons rouges sur la dernière colonne', () => {
  // Given
  const grille = new Grille()
  grille.colonnes[5].forEach((el, index) => grille.colonnes[5][index] = 'ROUGE')
  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLeJoueurAGagnéSurLaGrille()

  // Then
  expect(result).toBe(true)
})

test('devrait savoir qu‘un joueur a gagné quand il n‘y a que des jetons rouges sur la première ligne', () => {
  // Given
  const grille = new Grille()
  grille.colonnes.forEach((colonne, index) => colonne[0] = 'ROUGE')
  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLeJoueurAGagnéSurLaGrille()

  // Then
  expect(result).toBe(true)
})

test('devrait savoir qu‘un joueur a gagné quand il n‘y a que des jetons rouges sur la deuxième ligne', () => {
  // Given
  const grille = new Grille()
  grille.colonnes.forEach((colonne, index) => colonne[1] = 'ROUGE')
  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLeJoueurAGagnéSurLaGrille()

  // Then
  expect(result).toBe(true)
})

test('devrait savoir que personne n‘a gagné quand il 6 jetons (RJRJRJ) sur la première ligne', () => {
  // Given
  const grille = new Grille()
  grille.colonnes[0][0] ='ROUGE'
  grille.colonnes[1][0] ='JAUNE'
  grille.colonnes[2][0] ='ROUGE'
  grille.colonnes[3][0] ='JAUNE'
  grille.colonnes[4][0] ='ROUGE'
  grille.colonnes[5][0] ='JAUNE'
  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLeJoueurAGagnéSurLaGrille()

  // Then
  expect(result).toBe(false)
})

test('devrait savoir qu‘un joueur a gagné quand il y a une diagonale de 4 rouges en partant de la première colonne première rangée (direction haut-droit)', () => {
  // Given
  const grille = new Grille()
  grille.colonnes[0][0] ='ROUGE'
  grille.colonnes[1][0] ='JAUNE'
  grille.colonnes[1][1] ='ROUGE'
  grille.colonnes[2][0] ='JAUNE'
  grille.colonnes[2][1] ='JAUNE'
  grille.colonnes[2][2] ='ROUGE'
  grille.colonnes[3][0] ='JAUNE'
  grille.colonnes[3][1] ='ROUGE'
  grille.colonnes[3][2] ='JAUNE'
  grille.colonnes[3][3] ='ROUGE'
  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLeJoueurAGagnéSurLaGrille()

  // Then
  expect(result).toBe(true)
})

test('devrait savoir qu‘un joueur a gagné quand il y a une diagonale de 4 rouges en partant de la deuxième colonne deuxième rangée (direction haut-droit)', () => {
  // Given
  const grille = new Grille()
  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(2, 'JAUNE')
  grille.ajouteUnJeton(2, 'ROUGE')
  grille.ajouteUnJeton(3, 'ROUGE')
  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'ROUGE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'JAUNE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(5, 'ROUGE')
  grille.ajouteUnJeton(5, 'ROUGE')
  grille.ajouteUnJeton(5, 'JAUNE')
  grille.ajouteUnJeton(5, 'ROUGE')
  grille.ajouteUnJeton(5, 'ROUGE')
  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLeJoueurAGagnéSurLaGrille()

  // Then
  expect(result).toBe(true)
})

test('devrait savoir qu‘un joueur a gagné quand il y a une diagonale de 4 rouges en partant de la dernière colonne première rangée (direction haut-gauche)', () => {
  // Given
  const grille = new Grille()
  grille.ajouteUnJeton(6, 'ROUGE')
  grille.ajouteUnJeton(5, 'JAUNE')
  grille.ajouteUnJeton(5, 'ROUGE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'JAUNE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'ROUGE')
  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'ROUGE')
  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLeJoueurAGagnéSurLaGrille()

  // Then
  expect(result).toBe(true)
})

test('devrait savoir que la partie est nulle quand la grille est pleine et qu‘aucun joueur n‘a gagné', () => {
  // Given
  const grille = new Grille()
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'JAUNE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'JAUNE')

  grille.ajouteUnJeton(2, 'ROUGE')
  grille.ajouteUnJeton(2, 'ROUGE')
  grille.ajouteUnJeton(2, 'JAUNE')
  grille.ajouteUnJeton(2, 'ROUGE')
  grille.ajouteUnJeton(2, 'ROUGE')
  grille.ajouteUnJeton(2, 'JAUNE')

  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'JAUNE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'JAUNE')

  grille.ajouteUnJeton(6, 'ROUGE')
  grille.ajouteUnJeton(6, 'ROUGE')
  grille.ajouteUnJeton(6, 'JAUNE')
  grille.ajouteUnJeton(6, 'ROUGE')
  grille.ajouteUnJeton(6, 'ROUGE')
  grille.ajouteUnJeton(6, 'JAUNE')


  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'ROUGE')
  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'ROUGE')

  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'ROUGE')
  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'ROUGE')

  grille.ajouteUnJeton(5, 'JAUNE')
  grille.ajouteUnJeton(5, 'JAUNE')
  grille.ajouteUnJeton(5, 'ROUGE')
  grille.ajouteUnJeton(5, 'JAUNE')
  grille.ajouteUnJeton(5, 'JAUNE')
  grille.ajouteUnJeton(5, 'ROUGE')

  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLaPartieEstNulle()

  // Then
  expect(result).toBe(true)
})

test('devrait savoir que la partie n‘est pas nulle quand il reste au moins une case vide et qu‘il n‘y a pas de ligne', () => {
  // Given
  const grille = new Grille()
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'JAUNE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'JAUNE')

  grille.ajouteUnJeton(3, 'ROUGE')
  grille.ajouteUnJeton(3, 'ROUGE')
  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'ROUGE')
  grille.ajouteUnJeton(3, 'ROUGE')
  grille.ajouteUnJeton(3, 'JAUNE')

  grille.ajouteUnJeton(5, 'ROUGE')
  grille.ajouteUnJeton(5, 'ROUGE')
  grille.ajouteUnJeton(5, 'JAUNE')
  grille.ajouteUnJeton(5, 'ROUGE')
  grille.ajouteUnJeton(5, 'ROUGE')
  grille.ajouteUnJeton(5, 'JAUNE')

  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'ROUGE')
  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'ROUGE')

  grille.ajouteUnJeton(4, 'JAUNE')
  grille.ajouteUnJeton(4, 'JAUNE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'JAUNE')
  grille.ajouteUnJeton(4, 'JAUNE')
  grille.ajouteUnJeton(4, 'ROUGE')

  grille.ajouteUnJeton(6, 'JAUNE')
  grille.ajouteUnJeton(6, 'JAUNE')
  grille.ajouteUnJeton(6, 'ROUGE')
  grille.ajouteUnJeton(6, 'JAUNE')


  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLaPartieEstNulle()

  // Then
  expect(result).toBe(false)
})

test('devrait savoir que la partie n‘est pas nulle quand la grille est remplie et qu‘un joueur a gagné', () => {
  // Given
  const grille = new Grille()
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'ROUGE')
  grille.ajouteUnJeton(0, 'JAUNE')

  grille.ajouteUnJeton(2, 'ROUGE')
  grille.ajouteUnJeton(2, 'ROUGE')
  grille.ajouteUnJeton(2, 'JAUNE')
  grille.ajouteUnJeton(2, 'ROUGE')
  grille.ajouteUnJeton(2, 'ROUGE')
  grille.ajouteUnJeton(2, 'JAUNE')

  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'JAUNE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'ROUGE')
  grille.ajouteUnJeton(4, 'JAUNE')

  grille.ajouteUnJeton(6, 'ROUGE')
  grille.ajouteUnJeton(6, 'ROUGE')
  grille.ajouteUnJeton(6, 'JAUNE')
  grille.ajouteUnJeton(6, 'ROUGE')
  grille.ajouteUnJeton(6, 'ROUGE')
  grille.ajouteUnJeton(6, 'JAUNE')


  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'ROUGE')
  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'JAUNE')
  grille.ajouteUnJeton(1, 'ROUGE')

  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'ROUGE')
  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'JAUNE')
  grille.ajouteUnJeton(3, 'ROUGE')

  grille.ajouteUnJeton(5, 'JAUNE')
  grille.ajouteUnJeton(5, 'JAUNE')
  grille.ajouteUnJeton(5, 'ROUGE')
  grille.ajouteUnJeton(5, 'JAUNE')
  grille.ajouteUnJeton(5, 'JAUNE')
  grille.ajouteUnJeton(5, 'ROUGE')

  const analyseur = new Analyseur(grille)

  // When
  const result = analyseur.estCeQueLaPartieEstNulle()

  // Then
  expect(result).toBe(false)
})

test('devrait me dire qu‘il n‘y a plus de jetons jaunes que de rouges sur une grille vide', () => {
  const grille = new Grille()
  const analyseur = new Analyseur(grille)
  
  const plusDeJetons = analyseur.estCeQuilYAPlusDeJetonsJaune()

  expect(plusDeJetons).toBe(false)
})

test('devrait me dire qu‘il y a de jetons jaunes que de rouges sur une grille avec un jeton rouge', () => {
  const grille = new Grille()
  const analyseur = new Analyseur(grille)
  grille.ajouteUnJeton(5, 'JAUNE')
  
  const plusDeJetons = analyseur.estCeQuilYAPlusDeJetonsJaune()

  expect(plusDeJetons).toBe(true)
})