package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Projet;

/**
 * Cette classe interagit avec la BDD. Elle permet de faire le CRUD sur un Projet.
 * @author fkakcha
 *
 */

public class ProjetManager {

	private Connection conn;
	private static Statement statement;
	
	
	/**
	 * Constructeur pour initialiser la valeur du paramètre de connexion à la BDD.
	 * @param conn identifiant de connexion à la BDD
	 */
	public ProjetManager(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Sauvegarder en BDD un Projet
	 * @param projet Projet à sauvegarder
	 * @throws SQLException
	 */
	public void save(Projet projet) throws SQLException{
		
		if(projet == null)
			throw new IllegalArgumentException("Argument is null");
		
		String nom = projet.getNom();
		String version = projet.getChemin();			
		
		statement = conn.createStatement();		
		statement.executeUpdate("insert into projets values ('"+ nom + "','" + version + "')");						
		
		if(statement != null)
			statement.close();					
	}	
	
	/**
	 * Récupère un Projet de la BDD
	 * @param nomProjet nom du projet
	 * @return Projet 
	 * @throws SQLException
	 */
	public Projet get(String nomProjet) throws SQLException{
		
		if(nomProjet == null)
			throw new IllegalArgumentException("Argument is null");
		
		Projet projet = null;		
		
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from projets where nom='" + nomProjet + "'");
		if (rs.next()){			
			projet = new Projet(rs.getString(1), rs.getString(2));		
		}
				
		if (statement != null)
			statement.close();
		
		return projet;				
	}
	
	/**
	 * Supprimer un Projet de la BDD
	 * @param nomProjet nom du projet à supprimer
	 * @throws SQLException
	 */	
	public void delete(String nomProjet) throws SQLException{
		
		if(nomProjet == null)
			throw new IllegalArgumentException("Argument is null");
		
		statement = conn.createStatement();	
		statement.executeUpdate("delete from modelbox where nom='" + nomProjet + "'");
		statement.executeUpdate("delete from projets where nom='" + nomProjet + "'");
		
		if(statement != null)
			statement.close();
	}	
	
	/**
	 * Retourne la liste de tous les projets en BDD
	 * @return une liste de Projet
	 * @throws SQLException
	 */
	public ArrayList<Projet> getAll() throws SQLException{
		
		ArrayList<Projet> listProjet = new ArrayList<Projet>();
		Projet projet = null;
		
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from projets");

		while (rs.next()) {

			projet = new Projet(rs.getString(1), rs.getString(2));
			listProjet.add(projet);
		}
		if (statement != null)
			statement.close();

		return listProjet;
	}

}
