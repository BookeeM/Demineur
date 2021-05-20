package demineur.test;

import demineur.metier.Demineur;
import demineur.metier.EtatCase;

public class MainTest {

	public static void main(String[] args) {
		
		// Créer une partie de démineur
		Demineur jeuDemineur = new Demineur(6, 4, 2);
		
		System.out.println("Il y a "+jeuDemineur.getNbMinesATrouver()+" mines à trouver");
		
		// on regarde l'état des cases (et on l'affiche)
		System.out.println("La grille au debut : ");
		for (int hauteur=0; hauteur<4; hauteur++) {
			for (int largeur=0; largeur<6; largeur++) {
				EtatCase etat = jeuDemineur.getEtat(largeur, hauteur);
				switch(etat) {
				case DOUTE:
					System.out.print(" ? ");
					break;
				case FERME:
					System.out.print(" # ");
					break;
				case MINE:
					System.out.print(" X ");
					break;
				case OUVERT:
					System.out.print(" "+jeuDemineur.getValeur(largeur, hauteur)+" ");
					break;
				}
			}
			System.out.println("");
		}
		// on marque 2 cases (au hasard, c'est pour la démo)
		jeuDemineur.marquer(2, 2); // ferme => mine
		jeuDemineur.marquer(2, 2); // mine => doute
		jeuDemineur.marquer(5, 3); // ferme => mine
		// on regarde l'état des cases (et on l'affiche)
		System.out.println("La grille avec 2 marques : ");
		for (int hauteur=0; hauteur<4; hauteur++) {
			for (int largeur=0; largeur<6; largeur++) {
				EtatCase etat = jeuDemineur.getEtat(largeur, hauteur);
				switch(etat) {
				case DOUTE:
					System.out.print(" ? ");
					break;
				case FERME:
					System.out.print(" # ");
					break;
				case MINE:
					System.out.print(" X ");
					break;
				case OUVERT:
					System.out.print(" "+jeuDemineur.getValeur(largeur, hauteur)+" ");
					break;
				}
			}
			System.out.println("");
		}
		// on joue 3 coups (sans regarder le résultat)
		jeuDemineur.retourner(1, 3);
		jeuDemineur.retourner(0, 0);
		jeuDemineur.retourner(5, 2);

		// on regarde a nouveau l'état des cases (et on l'affiche)
		System.out.println("La grille après 3 coups : ");
		for (int hauteur=0; hauteur<4; hauteur++) {
			for (int largeur=0; largeur<6; largeur++) {
				EtatCase etat = jeuDemineur.getEtat(largeur, hauteur);
				switch(etat) {
				case DOUTE:
					System.out.print(" ? ");
					break;
				case FERME:
					System.out.print(" # ");
					break;
				case MINE:
					System.out.print(" X ");
					break;
				case OUVERT:
					System.out.print(" "+jeuDemineur.getValeur(largeur, hauteur)+" ");
					break;
				}
			}
			System.out.println("");
		}
		if (jeuDemineur.isPartieTerminee()) {
			System.out.println("la partie est terminée");
			if (jeuDemineur.isPartiePerdue()) {
				System.out.println("C'est perdu :-(");
			} else {
				System.out.println("C'est gagné :-)");				
			}
		} else {
			System.out.println("la partie n'est pas terminée");
		}
	}

}
