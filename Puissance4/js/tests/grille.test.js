import { Grille } from '../grille'

test('devrait avoir 7 colonnes et 6 rangées ; 6 emplacements vides dans chaque colonne', () => {
  // Given
  const grille = new Grille()
  // When
  const état = grille.donneEtat()
  // Then
  expect(état.colonnes.length).toBe(7)
  état.colonnes.forEach(colonne => {
    expect(colonne.length).toBe(6)
    colonne.forEach(emplacement => {
      expect(emplacement).toBe('')
    })
  })
})

test('devrait ajouter un pion rouge dans la première colonne', () => {
  // Given
  const couleurDuPion = 'ROUGE'
  const numéroDeColonne = 0
  const grille = new Grille()

  // When
  grille.ajouteUnJeton(numéroDeColonne, couleurDuPion)

  // Then
  const pionRougeTrouvé = grille.colonnes[0].find(element => element == 'ROUGE')
  expect(pionRougeTrouvé).toBeDefined()
})

test('devrait ajouter un pion jaune dans la 2ème colonne', () => {
  // Given
  const couleurDuPion = 'JAUNE'
  const numéroDeColonne = 1
  const grille = new Grille()

  // When
  grille.ajouteUnJeton(numéroDeColonne, couleurDuPion)

  // Then
  const pionRougeTrouvé = grille.colonnes[1].find(element => element == 'JAUNE')
  expect(pionRougeTrouvé).toBeDefined()
})

test('devrait ajouter un pion rouge sur la dernière rangée', () => {
  // Given
  const couleurDuPion = 'ROUGE'
  const numéroDeColonne = 0
  const grille = new Grille()

  // When
  grille.ajouteUnJeton(numéroDeColonne, couleurDuPion)

  // Then
  const pionRougeTrouvé = grille.colonnes[0][5]
  expect(pionRougeTrouvé).toBe('ROUGE')
})

test('devrait ajouter un pion rouge sur un autre pion rouge', () => {
  // Given
  const couleurDuPion = 'ROUGE'
  const numéroDeColonne = 0
  const grille = new Grille()

  // When
  grille.ajouteUnJeton(numéroDeColonne, couleurDuPion)
  grille.ajouteUnJeton(numéroDeColonne, couleurDuPion)

  // Then
  const pionRougeTrouvé = grille.colonnes[0][5]
  const pionRougeTrouvé2 = grille.colonnes[0][4]
  expect(pionRougeTrouvé).toBe('ROUGE')
  expect(pionRougeTrouvé2).toBe('ROUGE')
})

test('ne devrait pas ajouter plus de 6 pions', () => {
  // Given
  const couleurDuPion = 'ROUGE'
  const numéroDeColonne = 0
  const grille = new Grille()
  for (let index = 0; index < 6; index++) {
    grille.ajouteUnJeton(numéroDeColonne, couleurDuPion)
  }
  // Then
  expect(() => {
    grille.ajouteUnJeton(numéroDeColonne, couleurDuPion)
  }).toThrow()
})

test('devrait vider la grille quand elle contient un jeton', () => {
  // Given
  const couleurDuPion = 'ROUGE'
  const numéroDeColonne = 0
  const grille = new Grille()
  grille.ajouteUnJeton(numéroDeColonne, couleurDuPion)

  // When
  grille.vider()

  // Then
  grille.colonnes.forEach((colonne) => {
    colonne.forEach((element) => {
      expect(element).toBe('')
    })
  })
})

test('devrait pouvoir afficher une grille vide', () => {
  // Given
  const grille = new Grille()
  // When
  const grilleAffichée = grille.afficher()
  // Then
  expect(grilleAffichée).toBe(' .  .  .  .  .  .  . \n .  .  .  .  .  .  . \n .  .  .  .  .  .  . \n .  .  .  .  .  .  . \n .  .  .  .  .  .  . \n .  .  .  .  .  .  . \n')
})

test('devrait pouvoir afficher une grille avec un jeton rouge', () => {
  // Given
  const grille = new Grille()
  grille.ajouteUnJeton(0, 'ROUGE')
  // When
  const grilleAffichée = grille.afficher()
  // Then
  expect(grilleAffichée).toBe(' .  .  .  .  .  .  . \n .  .  .  .  .  .  . \n .  .  .  .  .  .  . \n .  .  .  .  .  .  . \n .  .  .  .  .  .  . \n R  .  .  .  .  .  . \n')
})