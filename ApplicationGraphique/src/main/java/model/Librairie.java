package model;

public class Librairie {

	
	private String nom;
	private String cheminFichier;
	
	
	public Librairie(String nom, String cheminFichier) {
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
	
	public void setDescription(String cheminFichier) {
		this.cheminFichier = cheminFichier;
	}
	
	@Override
	public String toString() {
		return "Modeles [nom=" + nom + ", cheminFichier=" + cheminFichier + "]";
	}
	
	
}
