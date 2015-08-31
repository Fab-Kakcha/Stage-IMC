package crud;

public class Parametre {
	
	private int id;
	private String nom;
	private String valeur;
	
	
	public Parametre(int id, String nom, String valeur) {
		super();
		this.nom = nom;
		this.valeur = valeur;
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getValeur() {
		return valeur;
	}
	
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Parametre [id=" + id + ", nom=" + nom + ", valeur=" + valeur
				+ "]";
	}	
}
