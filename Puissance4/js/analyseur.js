import { NB_COLONNES, NB_RANGEE, CELLULE_VIDE, JETON_ROUGE, JETON_JAUNE } from './constantes'

class Analyseur {
  constructor(grille) {
    this.grille = grille
  }

  estCeQueLeJoueurAGagnéSurLaGrille() {
    let rangées = [];
    let joueurAGagne = false;
    this.grille.colonnes.forEach((colonne, indexColonne) => {
      if (this.estCeQuilYA4MemesJetonsALaSuite(colonne)) {
        joueurAGagne = true;
      }

      colonne.forEach((rangée, indexRangée) => {
        if (!rangées[indexRangée]) {
          rangées.push([])
        }
        rangées[indexRangée].push(colonne[indexRangée]);
        if (
          this.joueurAGagneSurLaDiagonaleHautGaucheDepuisElement(
            indexColonne,
            indexRangée
          ) ||
          this.joueurAGagneSurLaDiagonaleHautDroiteDepuisElement(
            indexColonne,
            indexRangée
          )
        ) {
          joueurAGagne = true;
        }
      });
    });

    rangées.forEach(rangée => {
      if (this.estCeQuilYA4MemesJetonsALaSuite(rangée)) {
        joueurAGagne = true;
      }
    })
    
    return joueurAGagne;
  }

  estCeQuilYA4MemesJetonsALaSuite(listeDeJetons) {
    let nombreDeJetonsDeMemeCouleurALaSuite = 1;
    let couleurEnCours = listeDeJetons[0];
    let résultat = false
    listeDeJetons.forEach(jeton => {
      if (couleurEnCours == jeton && jeton != CELLULE_VIDE) {
        nombreDeJetonsDeMemeCouleurALaSuite++;
        if (nombreDeJetonsDeMemeCouleurALaSuite >= 4) {
          résultat = true
        }
      } else {
        nombreDeJetonsDeMemeCouleurALaSuite = 1;
      }
      couleurEnCours = jeton;
    });

    return résultat;
  }

  joueurAGagneSurLaDiagonaleHautGaucheDepuisElement(
    indexColonne,
    indexRangée
  ) {
    const jetonCourant = this.grille.colonnes[indexColonne][indexRangée];
    if (
      indexColonne + 3 >= NB_COLONNES ||
      indexRangée + 3 >= NB_RANGEE ||
      jetonCourant == CELLULE_VIDE
    ) {
      return false;
    }

    return (
      jetonCourant == this.grille.colonnes[indexColonne + 1][indexRangée + 1] &&
      jetonCourant == this.grille.colonnes[indexColonne + 2][indexRangée + 2] &&
      jetonCourant == this.grille.colonnes[indexColonne + 3][indexRangée + 3]
    );
  }

  joueurAGagneSurLaDiagonaleHautDroiteDepuisElement(
    indexColonne,
    indexRangée
  ) {
    const jetonCourant = this.grille.colonnes[indexColonne][indexRangée];

    if (
      indexColonne - 3 < 0 ||
      indexRangée + 3 >= NB_RANGEE ||
      jetonCourant == CELLULE_VIDE
    ) {
      return false;
    }

    return (
      jetonCourant == this.grille.colonnes[indexColonne - 1][indexRangée + 1] &&
      jetonCourant == this.grille.colonnes[indexColonne - 2][indexRangée + 2] &&
      jetonCourant == this.grille.colonnes[indexColonne - 3][indexRangée + 3]
    );
  }

  estCeQueLaPartieEstNulle() {
    return !this.estCeQuilResteDeLaPlaceSurLaGrille() && !this.estCeQueLeJoueurAGagnéSurLaGrille()
  }

  estCeQuilResteDeLaPlaceSurLaGrille() {
    return this.grille.colonnes.map(colonne => colonne.includes('')).filter(Boolean).length > 0
  }

  estCeQuilYAPlusDeJetonsJaune() {
    let nombreDeJetonsJaunes = 0
    let nombreDeJetonsRouges = 0

    this.grille.colonnes.forEach(colonne => {
      colonne.forEach((rangée, index) => {
        if (colonne[index] == JETON_ROUGE)
          nombreDeJetonsRouges++
        if (colonne[index] == JETON_JAUNE)
          nombreDeJetonsJaunes++
      })
    })
    return nombreDeJetonsJaunes > nombreDeJetonsRouges
  }
}

export { Analyseur };
