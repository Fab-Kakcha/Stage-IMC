package model;

import org.apache.log4j.Logger;

public class Projet {

	private static Logger logger = Logger.getLogger(Projet.class);
	
	private String nom;
	private String chemin;		
	
	public Projet(String nom, String chemin) {

		this.nom = nom;
		this.chemin = chemin;		
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getChemin() {
		return chemin;
	}
	
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	
	@Override
	public String toString() {
		return "Projet [nom=" + nom + ", chemin=" + chemin + "]";
	}		
}
