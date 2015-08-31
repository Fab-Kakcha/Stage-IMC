package crud;

import java.util.HashMap;
import java.util.List;

public class GroupeParametre {
	
	private String nom;
	private int id;
	private int generatedKey;
		
	private HashMap<Integer,Parametre> listParametre;
	
	
	public GroupeParametre(int id, String nom) {

		this.nom = nom;
		this.id = id;		
		listParametre = new HashMap<Integer,Parametre>();
	}
	
	public boolean verifieParametreAppartient(Parametre param){
		
		Parametre p = null;
		p = listParametre.get(param.getId());
		if(p != null)
			return true;
					
		return false;
	}
	
	public Parametre getParametre(int id){
		
		Parametre param = null;
		param = listParametre.get(id);
		
		return param;
	}
	
	public void update(Parametre param){
		
		if(verifieParametreAppartient(param))
			listParametre.put(param.getId(), param);
	}
	
	public void add(Parametre param){
		
		listParametre.put(param.getId(), param);
	}
	
	public void delete(Parametre param){
		
		if(verifieParametreAppartient(param))
			listParametre.remove(param.getId());
	}
	
	public HashMap<Integer,Parametre> getAllParametre(){
		
		return listParametre;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getGeneratedKey() {
		return generatedKey;
	}

	public void setGeneratedKey(int generatedKey) {
		this.generatedKey = generatedKey;
	}
	
	public void toPrint(){
						
		if(listParametre.isEmpty())
			System.out.println("Aucun parametre");
		
		else{
			
			System.out.println("GroupeParametre [nom=" + nom + ", id=" + id + "]");
			System.out.println("------------- Liste des parametres ----------------");
			for(Parametre param: listParametre.values()){
				
				System.out.println(param.toString());
			}
			System.out.println("----------------------------------------------------");
		}		
	}	
}
