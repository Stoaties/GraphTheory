package util;

import java.awt.geom.AffineTransform;

/**
 * Un objet ModeleAffichage permet de memoriser un ensemble de valeurs pour passer du monde reel vers un composant de dessin dont les 
 * coordonnees sont en pixels. On peut interroger l'objet pour connaitre la matrice de transformation (qui contient un scale), le nombre de pixels par unite, 
 * les dimensions dans le monde reel, etc.
 * 
 * @author Caroline Houle
 * @author Johnatan Gao
 */
 

public class ModeleAffichage {//debut classe
	private double hautUnitesReelles = -1;
	private double largUnitesReelles;
	private double largPixels;
	private double hautPixels;
	private double pixelsParUniteX;
	private double pixelsParUniteY;
	private AffineTransform matMC;
	
	/**
	 * Permet de creer un objet ModeleAffichage, pouvant memoriser la matrice (et autres valeurs) de transformation pour passer du monde vers le composant. La largeur du monde 
	 * reel est passee en parametre. La hauteur sera  calculee de facon a n'introduire aucune distortion.
	 * 
	 * @param largPixels La largeur du composant, en pixels
	 * @param hautPixels La hauteur du composant, en pixels
	 * @param largeurDuMonde La largeur du monde, en unitees reelles
	 */
	//Caroline H
	public ModeleAffichage(double largPixels, double hautPixels, double largeurDuMonde) {//debut constructeur
		
		this.largPixels = largPixels;
		this.hautPixels = hautPixels;
		this.largUnitesReelles = largeurDuMonde;
			
		//on calcule la hauteur correspondante pour eviter toute distortion
		this.hautUnitesReelles = largUnitesReelles * hautPixels/largPixels;
		        
		//calcul du nombre de pixels qu'on aura par unite reelle
		this.pixelsParUniteX = largPixels / largUnitesReelles;
		this.pixelsParUniteY = hautPixels / hautUnitesReelles ;

		//formation de la matrice monde-vers-composant
		AffineTransform mat = new AffineTransform();  //on part d'une matrice identite
		mat.scale( pixelsParUniteX, pixelsParUniteY ); 
		
		//on memorise la matrice (qui pourra etre retournee via le getter associe)
		this.matMC = mat; 
		
	}//fin constructeur
	
	/**
	 * Retourne une copie de la matrice monde-vers-composant qui a ete calculee dans le constructeur
	 * @return La matrice monde-vers-composant
	 */
	//Caroline H
	public AffineTransform getMatMC() {
		//on decide de retourner une copie de celle qui est memorisee, pour eviter qu'elle soit modifiee
		return new AffineTransform (matMC);
	}
	
	/**
	 * Retourne la hauteur du monde, en unites reelles
	 * @return La hauteur du monde, en unitees reelles
	 */
	//Caroline H
	public double getHautUnitesReelles() {
		return hautUnitesReelles;
	}

	/**
	 * Retourne la largeur du monde, en unites reelles
	 * @return La largeur du monde, en unites reelles
	 */
	//Caroline H
	public double getLargUnitesReelles() {
		return largUnitesReelles;
	}

	/**
	 * Retourne le nombre de pixels contenus dans une unite du monde reel, en x
	 * @return Le nombre de pixels contenus dans une unite du monde reel, en x
	 */
	//Caroline H
	public double getPixelsParUniteX() {
		return pixelsParUniteX;
	}

	/**
	 * Retourne le nombre de pixels contenus dans une unite du monde reel, en y
	 * @return Le nombre de pixels contenus dans une unitedu monde reel, en y
	 */
	//Caroline H
	public double getPixelsParUniteY() {
		return pixelsParUniteY;
	}

	/**
	 * Retourne la largeur en pixels du composant auquel s'appliquera la transformation 
	 * @return La largeur en pixels 
	 */
	//Caroline H
	public double getLargPixels() {
		return largPixels;
	}

	/**
	 * Retourne la hauteur en pixels du composant auquel s'appliquera la transformation 
	 * @return La hauteur en pixels 
	 */
	//Caroline H
	public double getHautPixels() {
		return hautPixels;
	}

	/**
	 * Retourne les pixels en X en valeur reelle
	 * @param pixelX Les pixels en X
	 * @return  pixelX / this.pixelsParUniteX La valeur reelle
	 */
	//Johnatan G
	
	public double pixelEnReelleX(double pixelX) {
		return pixelX / this.pixelsParUniteX;
	}
	
	/**
	 * Retourne les pixels en Y en valeur reelle
	 * @param pixelY Les pixels en Y
	 * @return  pixelY / this.pixelsParUniteY La valeur reelle
	 */
	//Johnatan G
	
	public double pixelEnRelleY(double pixelY) {
		return pixelY / this.pixelsParUniteY;
	}
	
}//fin classe
