package model;

/**
 * Cette classe définit le nom et le chemin des fichiers utilisés pour la configuration du générateur
 * @author fkakcha
 *
 */

public class Profile {
	
	private String nom;
	private String cheminFichier;
	
	
	public Profile(String nom, String cheminFichier) {
		this.nom = nom;
		this.cheminFichier = cheminFichier;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getCheminFichier() {
		return cheminFichier;
	}
	
	public void setCheminFichier(String cheminFichier) {
		this.cheminFichier = cheminFichier;
	}
	
	@Override
	public String toString() {
		return "Parametre [nom=" + nom + ", cheminFichier=" + cheminFichier + "]";
	}	
}
