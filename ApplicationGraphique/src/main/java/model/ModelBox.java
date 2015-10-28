package model;

/**
 * Cette clase définit le nom et le chemin des fichiers qui sont utlisés lors de la configurations d'un projet. 
 * Ces fichiers de configurations peuvent être utilisés par plusieurs projets différents.
 * @author fkakcha
 *
 */

public class ModelBox {
	
	private String nom;
	private String chemin;	
	private String nomProjet;
	
	public ModelBox(String nom, String chemin){
		this.nom = nom;
		this.chemin = chemin;
	}
	
	public ModelBox(String nom, String chemin, String nomProjet) {

		this.nom = nom;
		this.chemin = chemin;
		this.nomProjet = nomProjet;
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
	
	public String getNomProjet() {
		return nomProjet;
	}

	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}

	@Override
	public String toString() {
		return "ModelBox [nom=" + nom + ", chemin=" + chemin + ", nomProjet="
				+ nomProjet + "]";
	}		
}
