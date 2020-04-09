class Arbitre {
  constructor(analyseur) {
    this.analyseur = analyseur
  }
  prochainJoueur() {
    return this.analyseur.estCeQuilYAPlusDeJetonsJaune() ? 'ROUGE' : 'JAUNE'
  }
  joueLeCoupDuProchainJoueurEnColonne(numéroDeLaColonne) {
    this.analyseur.grille.ajouteUnJeton(numéroDeLaColonne, this.prochainJoueur())
  }
  étatdeLaPartie() {
    if (this.analyseur.estCeQueLeJoueurAGagnéSurLaGrille()) {
      return 'GAGNEE PAR ' + this.dernierJoueur()
    }
    if (this.analyseur.estCeQueLaPartieEstNulle()) {
      return 'PARTIE NULLE'
    }
    return 'EN COURS'
  }
  dernierJoueur() {
    return this.prochainJoueur() == 'ROUGE' ? 'JAUNE' : 'ROUGE'
  }
}

export { Arbitre }