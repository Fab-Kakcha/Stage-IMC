package crud;

import java.util.*;

import org.apache.log4j.Logger;

public class Projet {

	private static Logger logger = Logger.getLogger(Projet.class);
	
	private String nom;
	private String version;
	private String code;
	private Etat etat;

	private HashMap<Integer, GroupeParametre> listGroupeParam;
	private HashMap<String, Modeles> listModele;
	
	
	public Projet(String nom, String version, String code) {

		this.nom = nom;
		this.version = version;
		this.code = code;
		
		listGroupeParam = new HashMap<Integer,GroupeParametre>();
		listModele = new HashMap<String, Modeles>();
	}
	
	public void addGroupeParametre(GroupeParametre groupeParam){
		
		listGroupeParam.put(groupeParam.getId(), groupeParam);
	}
	
	public void removeGroupeParametre(GroupeParametre groupeParam){
		
		if(verifieSiGroupeParametreAppartient(groupeParam))
			listGroupeParam.remove(groupeParam.getId());
	}
	
	public void updateGroupeParametre(GroupeParametre groupeParam){
		
		if(verifieSiGroupeParametreAppartient(groupeParam))
			listGroupeParam.put(groupeParam.getId(), groupeParam);
	}
			
	public void addModele(Modeles modele){
		
		if(modele != null)
			listModele.put(modele.getNom(), modele);
		else
			logger.info("Le modele passe en parametre est nulle");
	}
	
	public void removeModele(Modeles modele){
		
		if(verifieSiModeleAppartient(modele)){
			
			listModele.remove(modele.getNom());
		}
	}
		
	public Modeles getModele(String nomModele){
		
		Modeles modele =  listModele.get(nomModele);
		return modele;				
	}
	
	public GroupeParametre getGroupeParametre(int idGrpeParam){
		
		GroupeParametre grpe = listGroupeParam.get(idGrpeParam);
		return grpe;
	}
	
	public void setEtat(Etat etat){		
		this.etat = etat;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Etat getEtat(){
		return etat;
	}

	public HashMap<Integer, GroupeParametre> getListGroupeParam() {
		return listGroupeParam;
	}

	public void setListGroupeParam(HashMap<Integer, GroupeParametre> listGroupeParam) {
		this.listGroupeParam = listGroupeParam;
	}

	public HashMap<String, Modeles> getListModele() {
		return listModele;
	}

	public void setListModele(HashMap<String, Modeles> listModele) {
		this.listModele = listModele;
	}	
	
	
	public Parametre getParametre(int idParam){
		
		Parametre param = null;
		GroupeParametre grpe = null;
		Set<Integer> groupeParam = listGroupeParam.keySet();
		Iterator<Integer> ite = groupeParam.iterator();
		
		while(ite.hasNext()){
			
			grpe = listGroupeParam.get(ite.next());
			param = grpe.getParametre(idParam);
			if(param != null)
				break;
		}
		
		return param;
	}
	
	public void updateParametre(Parametre parametre){
				
		GroupeParametre grpe = null;
		Set<Integer> groupeParam = listGroupeParam.keySet();
		Iterator<Integer> ite = groupeParam.iterator();
		
		while(ite.hasNext()){
			
			grpe = listGroupeParam.get(ite.next());
			grpe.update(parametre);
			listGroupeParam.put(grpe.getId(), grpe);
		}		
	}
	
	public void removeParametre(Parametre param){
		
		GroupeParametre grpe = null;
		Set<Integer> groupeParam = listGroupeParam.keySet();
		Iterator<Integer> ite = groupeParam.iterator();
		
		while(ite.hasNext()){
			
			grpe = listGroupeParam.get(ite.next());
			grpe.delete(param);
			listGroupeParam.put(grpe.getId(), grpe);
		}	
	}
	
	public boolean verifieSiParamAppartient(Parametre param){
		
		boolean bool = false;
		GroupeParametre grpe = null;
		
		Set<Integer> groupeParam = listGroupeParam.keySet();
		Iterator<Integer> ite = groupeParam.iterator();
		
		while(ite.hasNext()){
			
			grpe = listGroupeParam.get(ite.next());
			if(grpe.verifieParametreAppartient(param))
				bool = true;
		}
								
		return bool;		
	}
	
	public boolean verifieSiModeleAppartient(Modeles modele){
		
		Modeles model = null;
		
		model = listModele.get(modele.getNom());
		if(model != null)
			return true;
		
		return false;
	}
	
	public boolean verifieSiGroupeParametreAppartient(GroupeParametre grpeParam){
		
		GroupeParametre grpe = null;
		
		grpe = listGroupeParam.get(grpeParam.getId());
		if(grpe != null)
			return true;
		
		return false;
	}
	
	public void updateModele(Modeles modele){
		
		if(verifieSiModeleAppartient(modele))
			listModele.put(modele.getNom(), modele);		
	}
	
	@Override
	public String toString() {
		return "Projet [nom=" + nom + ", version=" + version + ", code=" + code
				+ "]";
	}
	
	public void toPrint(){
		
		System.out.println("Description du " + toString());
		System.out.println("---------------------------------------------------------------------");
		System.out.println("---------------- Parametres ---------------------");
		for(GroupeParametre grpeParam : listGroupeParam.values())
			grpeParam.toPrint();
			
		System.out.println("---------------- Modeles ---------------------");
		for(Modeles modele : listModele.values())
			System.out.println(modele.toString());
		
		System.out.println("------------------ Etat -----------------------");
			System.out.println(etat);
			
		System.out.println("------------------------------------------------------------------------");
	}
		
}
