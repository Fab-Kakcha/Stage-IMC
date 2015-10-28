package model;

import java.sql.Connection;

/**
 * Cette classe définit un projet. Un projet est déterminé par un nom et par le chemin du répertoire local qui lui es associé
 * lors de sa création.
 * @author fkakcha
 *
 */
 
public class Projet {
	
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
		StringBuilder builder = new StringBuilder();
		builder.append("Projet [nom=");
		builder.append(nom);
		builder.append(", chemin=");
		builder.append(chemin);
		builder.append("]");
		return builder.toString();
	}		
}
