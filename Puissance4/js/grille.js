import { NB_COLONNES, NB_RANGEE, RANGEE_DU_BAS, CELLULE_VIDE } from './constantes'

class Grille {
  constructor() {
    this.colonnes = []
    for (let indexColonne = 0; indexColonne < NB_COLONNES; indexColonne++) {
      this.colonnes[indexColonne] = []
    for (let indexRangée = 0; indexRangée < NB_RANGEE; indexRangée++) {
        this.colonnes[indexColonne][indexRangée] = CELLULE_VIDE
      }
    }
  }

  donneEtat() {
    return this
  } 

  ajouteUnJeton(numéroDeColonne, couleurDuJeton) {
    if (this.colonnes[numéroDeColonne][RANGEE_DU_BAS] == CELLULE_VIDE) {
      this.colonnes[numéroDeColonne][RANGEE_DU_BAS] = couleurDuJeton
    } else {
      this.ajouteUnJetonSurLaRangée(numéroDeColonne, couleurDuJeton, RANGEE_DU_BAS - 1)
    }
  }

  ajouteUnJetonSurLaRangée(numéroDeColonne, couleurDuJeton, numéroRangée) {
    if (numéroRangée < 0) {
      throw new Error('impossible d‘ajouter un jeton ici')
    }
    if (this.colonnes[numéroDeColonne][numéroRangée] == CELLULE_VIDE) {
      this.colonnes[numéroDeColonne][numéroRangée] = couleurDuJeton
    } else {
      this.ajouteUnJetonSurLaRangée(numéroDeColonne, couleurDuJeton, numéroRangée - 1)
    }
  }

  vider() {
    this.colonnes.forEach(colonne => {
      colonne.forEach((element, index) => {
        colonne[index] = CELLULE_VIDE
      })
    })
  }

  afficher() {
    let grilleAffichée = ''
    let rangées = []
    this.colonnes.forEach(colonne => {
      colonne.forEach((rangée, indexRangée) => {
        if (!rangées[indexRangée]) {
          rangées.push([])
        }
        switch (colonne[indexRangée]) {
          case 'ROUGE':
            rangées[indexRangée].push(' R ');
            break;
          case 'JAUNE':
            rangées[indexRangée].push(' J ');
            break;
          default:
            rangées[indexRangée].push(' . ');
        }
      })
    })

    rangées.forEach(rangée => {
      rangée.forEach(cellule => grilleAffichée = grilleAffichée + cellule)
      grilleAffichée = grilleAffichée + '\n'
    })
    return grilleAffichée
  }
}

export { Grille }