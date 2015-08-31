package crud;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ProjetManager {

	private Connection conn;
	private static Statement statement;
	
	private HashMap<Integer, GroupeParametre> listGroupeParam;
	private HashMap<String, Modeles> listModele;
	private Etat etat;
	
	public ProjetManager(Connection conn) {
		this.conn = conn;
	}

	public void save(Projet projet) throws SQLException{
		
		String nom = projet.getNom();
		String version = projet.getVersion();
		String code = projet.getCode();		
		
		listGroupeParam = projet.getListGroupeParam();
		listModele = projet.getListModele();
		etat = projet.getEtat();		
		
		statement = conn.createStatement();
		
		statement.executeUpdate("insert into projetetat values ('"+ nom + "','" + version + "','"+ code+ "','" 
				+ etat.getCode() + "')");
		
		for(GroupeParametre groupeParam : listGroupeParam.values()){
			
			statement.executeUpdate("insert into projetgroupeparametre values ('" + nom + "'," + groupeParam.getId() + ")");			
			//UpdateProjectNameInParametre(groupeParam, nom);
		}	
				
		statement = conn.createStatement();
		for(Modeles modele : listModele.values()){
			
			//statement.executeUpdate("insert into modeles values ('" + modele.getNom() + "','" + modele.getDescription() +"')");
			statement.executeUpdate("insert into projetmodeles values ('" + nom + "','" + modele.getNom()+ "')");
		}							
		
		if(statement != null)
			statement.close();					
	}	
	
	public void delete(Projet projet) throws SQLException{
		
		String nom = projet.getNom();
		statement = conn.createStatement();
		
		statement.executeUpdate("delete from projetgroupeparametre where nom_projet='" + nom + "'");
		statement.executeUpdate("delete from projetmodeles where nom_projet='" + nom + "'");
		statement.executeUpdate("delete from projetetat where nom_projet='" + nom + "'");
		
		projet.setEtat(null);
		projet.setListModele(null);
		projet.setListGroupeParam(null);
		
		if(statement != null)
			statement.close();
	}	
	
	public void updateEtat(Projet projet, Etat etat) throws SQLException{
		
		String nomProjet = projet.getNom();	
		String code = etat.getCode();
		
		statement = conn.createStatement();
		statement.executeUpdate("update projetetat set code_etat='" + code + "' where nom_projet='" + nomProjet + "'");		
		if(statement != null)
			statement.close();
		
		projet.setEtat(etat);
	}
		
	public void updateModele(Projet projet, Modeles modele) throws SQLException{
		
		if(projet.verifieSiModeleAppartient(modele)){
			
			ModelesManager modeleManager = new ModelesManager(conn);
			modeleManager.updateModele(modele);
			
			projet.updateModele(modele);
		}	
	}
	
	public void updateValeurParametre(Projet projet, Parametre param) throws SQLException{
		
		if(projet.verifieSiParamAppartient(param)){
			
			ParametreManager paramManager = new ParametreManager(conn);
			paramManager.updateValeurParametre(param);
			
			projet.updateParametre(param);
		}
	}
	
	public void updateNomParametre(Projet projet, Parametre param) throws SQLException{
		
		if(projet.verifieSiParamAppartient(param)){
			
			ParametreManager paramManager = new ParametreManager(conn);
			paramManager.updateNomParametre(param);
			
			projet.updateParametre(param);
		}		
	}
	
	public void deleteParametre(Projet projet, Parametre param) throws SQLException{
				
		int idParam = param.getId();
		
		if(projet.verifieSiParamAppartient(param)){
			
			statement = conn.createStatement();
			statement.executeUpdate("delete from listeparametresgroupes where id_param=" + idParam + "");
	        if(statement != null)
				statement.close();
						
			projet.removeParametre(param);
		}		
	}
	
	public void deleteModele(Projet projet, Modeles modele) throws SQLException{
		
		String nomProjet = projet.getNom();
		String nomModele = modele.getNom();
		
		statement = conn.createStatement();
        statement.executeUpdate("delete from projetmodeles where nom_projet ='" + nomProjet +"' and nom_modele='" + nomModele + "'");
        if(statement != null)
			statement.close();		
        
        projet.removeModele(modele);
	}
	
	public void deleteGroupeParametre(Projet projet, GroupeParametre groupeParam) throws SQLException{
		
		String nomProjet = projet.getNom();
		int idGrpe = groupeParam.getId();
		
		statement = conn.createStatement();
        statement.executeUpdate("delete from projetgroupeparametre where nom_projet ='" + nomProjet +"' and id_grpeParam=" + idGrpe + "");
        if(statement != null)
			statement.close();
		
		projet.removeGroupeParametre(groupeParam);
	}
	
	
	
	/*private void UpdateProjectNameInParametre(GroupeParametre groupeParam, String nomProjet) throws SQLException{
		
		HashMap<Integer,Parametre> allParametres = groupeParam.getAllParametre();
		statement = conn.createStatement();
		
		for(Parametre param : allParametres.values()){						
			statement.executeUpdate("update parametre set nom_projet='" + nomProjet + "' where id_param=" + param.getId());
		}
		
		if(statement != null)
			statement.close();
	}*/

}


