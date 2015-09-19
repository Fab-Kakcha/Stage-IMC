package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import model.Etat;
import model.GroupeParametre;
import model.Modeles;
import model.Parametre;
import model.Projet;

/**
 * Récupérer les modèles, le groupe de paramètre, et les paramètres d'un projet à partir du nom du projet.
 * @author fkakcha
 *
 */

public class ProjetManager {

	private Connection conn;
	private static Statement statement;
	
	private HashMap<Integer, GroupeParametre> listGroupeParam;
	private HashMap<String, Modeles> listModele;
	private Etat etat;
	
	public ProjetManager(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 
	 * @param projet
	 * @throws SQLException
	 */
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
	
	/**
	 * Récupère de la BDD les principales caractéristriques(nom, version, code, etat) du projet
	 * @param nomProjet le nom du projet à récupérer
	 * @return projet
	 * @throws SQLException
	 */
	public Projet get(String nomProjet) throws SQLException{
		
		Projet projet = null;		
		
		statement = conn.createStatement();
		Statement st1 = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from projetetat where nom_projet='" + nomProjet + "'");
		if (rs.next()){
			
			projet = new Projet(rs.getString(1), rs.getString(2), rs.getString(3));
			ResultSet rs1 = st1.executeQuery("select intitule from etat where code_etat='" + rs.getString(4) + "'");
			if(rs1.next())
				projet.setEtat(new Etat(rs.getString(4), rs1.getString(1)));			
		}
				
		if (statement != null)
			statement.close();
		if(st1 != null)
			st1.close();
		
		return projet;				
	}
	
	/**
	 * 
	 * @param projet
	 * @throws SQLException
	 */
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
	
	/**
	 * 
	 * @param projet
	 * @param etat
	 * @throws SQLException
	 */
	public void updateEtat(Projet projet, Etat etat) throws SQLException{
		
		String nomProjet = projet.getNom();	
		String code = etat.getCode();
		
		statement = conn.createStatement();
		statement.executeUpdate("update projetetat set code_etat='" + code + "' where nom_projet='" + nomProjet + "'");		
		if(statement != null)
			statement.close();
		
		projet.setEtat(etat);
	}
		
	/**
	 * 
	 * @param projet
	 * @param modele
	 * @throws SQLException
	 */
	public void updateModele(Projet projet, Modeles modele) throws SQLException{
		
		if(projet.verifieSiModeleAppartient(modele)){
			
			ModelesManager modeleManager = new ModelesManager(conn);
			modeleManager.update(modele);
			
			projet.updateModele(modele);
		}	
	}
	
	/**
	 * 
	 * @param projet
	 * @param param
	 * @throws SQLException
	 */
	public void updateValeurParametre(Projet projet, Parametre param) throws SQLException{
		
		if(projet.verifieSiParamAppartient(param)){
			
			ParametreManager paramManager = new ParametreManager(conn);
			paramManager.updateValeurParametre(param);
			
			projet.updateParametre(param);
		}
	}
	
	/**
	 * 
	 * @param projet
	 * @param param
	 * @throws SQLException
	 */
	public void updateNomParametre(Projet projet, Parametre param) throws SQLException{
		
		if(projet.verifieSiParamAppartient(param)){
			
			ParametreManager paramManager = new ParametreManager(conn);
			paramManager.updateNomParametre(param);
			
			projet.updateParametre(param);
		}		
	}
	
	/**
	 * 
	 * @param projet
	 * @param param
	 * @throws SQLException
	 */
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
		
	/**
	 * 
	 * @param projet
	 * @param modele
	 * @throws SQLException
	 */
	public void deleteModele(Projet projet, Modeles modele) throws SQLException{
		
		String nomProjet = projet.getNom();
		String nomModele = modele.getNom();
		
		statement = conn.createStatement();
        statement.executeUpdate("delete from projetmodeles where nom_projet ='" + nomProjet +"' and nom_modele='" + nomModele + "'");
        if(statement != null)
			statement.close();		
        
        projet.removeModele(modele);
	}
	
	/**
	 * 
	 * @param projet
	 * @param groupeParam
	 * @throws SQLException
	 */
	public void deleteGroupeParametre(Projet projet, GroupeParametre groupeParam) throws SQLException{
		
		String nomProjet = projet.getNom();
		int idGrpe = groupeParam.getId();
		
		statement = conn.createStatement();
        statement.executeUpdate("delete from projetgroupeparametre where nom_projet ='" + nomProjet +"' and id_grpeParam=" + idGrpe + "");
        if(statement != null)
			statement.close();
		
		projet.removeGroupeParametre(groupeParam);
	}
	
	/**
	 * 
	 * @param projet
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, Modeles> getModele(Projet projet) throws SQLException{
		
		//ArrayList<Modeles> listModeles = new ArrayList<Modeles>();
		HashMap<String, Modeles> listModeles = new HashMap<String, Modeles>();
		Modeles modeles = null;
		Statement st = null;
		ResultSet rs1;
		String nomModele, descriptionModele;
		String nomProjet = projet.getNom();
		
		statement = conn.createStatement();
		st = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from projetmodeles where nom_projet='" + nomProjet +"'");
		
		while(rs.next()){
			
			nomModele = rs.getString(2);
			rs1 = st.executeQuery("select * from modeles where nom_modele='" + nomModele + "'");
			if(rs1.next()){
				
				descriptionModele = rs1.getString(2);	
				modeles = new Modeles(nomModele, descriptionModele);
				listModeles.put(nomModele, modeles);
			}
		}
				
		return listModeles;				
	}
	
	/**
	 * 
	 * @param projet
	 * @return
	 * @throws SQLException
	 */
	public HashMap<Integer,GroupeParametre> getGroupeParametre(Projet projet) throws SQLException{
		
		//ArrayList<GroupeParametre> listGroupeParametre = new ArrayList<GroupeParametre>();
		HashMap<Integer,GroupeParametre> listGroupeParametre = new HashMap<Integer,GroupeParametre>();
		GroupeParametre grpeParam;
		Parametre parametre;
		ResultSet rs1, rs2, rs3;
		String nomProjet = projet.getNom();
		String nomParam, valeurParam;
		int id, idParam;
		
		statement = conn.createStatement();
		Statement statement1 = conn.createStatement();
		Statement st = conn.createStatement();
		Statement statement2 = conn.createStatement();
		ResultSet rs = statement.executeQuery("select id_grpeParam from projetgroupeparametre where nom_projet='" + nomProjet +"'");
		
		while(rs.next()){
			
			id = rs.getInt(1);
			rs1 = statement1.executeQuery("select nom from groupeparametre where id_grpeParam="+ id + "");
			if(rs1.next()){
				
				grpeParam = new GroupeParametre(id, rs1.getString(1));
				//Récupérer l'id de tous les paramètres dans la table listeparametresgroupes
				rs2 = st.executeQuery("select id_param from listeparametresgroupes where id_grpeParam="+ id + "");
				while (rs2.next()) {

					idParam = rs2.getInt(1);					
					rs3 = statement2.executeQuery("select * from parametre where id_param="+ idParam + "");
					
					if(rs3.next()){
						nomParam = rs3.getString(2);
						valeurParam = rs3.getString(3);
						
						parametre = new Parametre(idParam, nomParam, valeurParam);
						grpeParam.add(parametre);
					}
				}
				
				listGroupeParametre.put(id, grpeParam);
			}				
		}
		
		if (st != null)
			st.close();
		if (statement != null)
			statement.close();
		if(statement1 != null)
			statement1.close();
		if(statement2 != null)
			statement2.close();
				
		return listGroupeParametre;
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


