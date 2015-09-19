package model;

import java.util.HashMap;
import java.util.List;

public class GroupeParametre {
	
	private int id;
	private String nom;		

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
	
	public void toPrint(){
						
		if(listParametre.isEmpty())
			System.out.println("Aucun parametre");
		
		else{
			
			System.out.println("GroupeParametre [id=" + id + ", nom=" + nom + "]");
			System.out.println("Liste des parametres: ");
			for(Parametre param: listParametre.values()){
				
				System.out.println(param.toString());
			}
		}		
	}	
}
