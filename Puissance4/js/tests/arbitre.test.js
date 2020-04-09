import { Arbitre } from '../arbitre'
import { Analyseur } from '../analyseur'
import { Grille } from '../grille'

test('devrait dire que c‘est à jaune de jouer quand la grille est vide', () => {
  // Given
  const grille = new Grille()
  const analyseur = new Analyseur(grille)
  const arbitre = new Arbitre(analyseur)

  // When
  const prochainJoueur = arbitre.prochainJoueur()

  // Then
  expect(prochainJoueur).toBe('JAUNE')
})

test('devrait dire que c‘est à rouge de jouer au deuxième tour', () => {
  // Given
  const grille = new Grille()
  const analyseur = new Analyseur(grille)
  const arbitre = new Arbitre(analyseur)
  grille.ajouteUnJeton(0, 'JAUNE')

  // When
  const prochainJoueur = arbitre.prochainJoueur()
  //analyseur.estCeQuilYAPlusDeJetonsJaune(analyseur)

  // Then
  expect(prochainJoueur).toBe('ROUGE')
})

test('devrait avoir ajouté un jeton jaune dans la première colonne quand le premier joueur joue sur la première colonne', () => {
  // Given
  const grille = new Grille()
  const analyseur = new Analyseur(grille)
  const arbitre = new Arbitre(analyseur)

  // When
  arbitre.joueLeCoupDuProchainJoueurEnColonne(0)
  //analyseur.estCeQuilYAPlusDeJetonsJaune(analyseur)

  // Then
  expect(grille.colonnes[0][5]).toBe('JAUNE')
})

test('devrait avoir ajouté un jeton rouge dans la première colonne quand le 2eme joueur joue sur la première colonne', () => {
  // Given
  const grille = new Grille()
  const analyseur = new Analyseur(grille)
  const arbitre = new Arbitre(analyseur)

  // When
  arbitre.joueLeCoupDuProchainJoueurEnColonne(0)
  arbitre.joueLeCoupDuProchainJoueurEnColonne(0)

  // Then
  expect(grille.colonnes[0][5]).toBe('JAUNE')
  expect(grille.colonnes[0][4]).toBe('ROUGE')
})

test('devrait dire que la partie est en cours après 3 coups', () => {
  // Given
  const grille = new Grille()
  const analyseur = new Analyseur(grille)
  const arbitre = new Arbitre(analyseur)

  // When
  arbitre.joueLeCoupDuProchainJoueurEnColonne(0)
  arbitre.joueLeCoupDuProchainJoueurEnColonne(0)
  arbitre.joueLeCoupDuProchainJoueurEnColonne(0)

  // Then
  expect(arbitre.étatdeLaPartie()).toBe('EN COURS')
})

test('devrait dire que la partie est gagnée par le joueur jaune après son coup gagnant', () => {
  // Given
  const grille = new Grille()
  const analyseur = new Analyseur(grille)
  const arbitre = new Arbitre(analyseur)

  // When
  arbitre.joueLeCoupDuProchainJoueurEnColonne(0)
  arbitre.joueLeCoupDuProchainJoueurEnColonne(1)
  arbitre.joueLeCoupDuProchainJoueurEnColonne(0)
  arbitre.joueLeCoupDuProchainJoueurEnColonne(1)
  arbitre.joueLeCoupDuProchainJoueurEnColonne(0)
  arbitre.joueLeCoupDuProchainJoueurEnColonne(1)
  arbitre.joueLeCoupDuProchainJoueurEnColonne(0)

  // Then
  expect(arbitre.étatdeLaPartie()).toBe('GAGNEE PAR JAUNE')
})

test('devrait dire que la partie est nulle quand un joueur a posé la dernière pièce', () => {
  // Given
  const grille = new Grille()
  const analyseur = new Analyseur(grille)
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
  const arbitre = new Arbitre(analyseur)


  // Then
  expect(arbitre.étatdeLaPartie()).toBe('PARTIE NULLE')
})
