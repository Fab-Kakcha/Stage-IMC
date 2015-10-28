package manager;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ModelBox;


/**
 * Cette classe interagit avec la BDD. Elle permet de faire le CRUD sur une ModelBox.
 * @author fkakcha
 *
 */

public class ModelBoxManager {

	private Connection conn;
	private static Statement statement;
	
	/**
	 * Constructeur pour initialiser la valeur du paramètre de connexion à la BDD.
	 * @param conn identifiant de connexion à la BDD
	 */
	public ModelBoxManager(Connection conn) {
		this.conn = conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Sauvegarder une ModelBox en BDD
	 * @param modelBox Modelbox à sauvegarder
	 * @throws SQLException
	 */
	public void save(ModelBox modelBox) throws SQLException{
		
		if(modelBox == null)
			throw new IllegalArgumentException("Argument is null");
		
		String nom = modelBox.getNom();
		String version = modelBox.getChemin();	
		String nomProjet = modelBox.getNomProjet();
		
		statement = conn.createStatement();		
		//statement.executeUpdate("insert into modelbox values ('"+ nom + "','" + version + "','" + nomProjet + "')");	
		statement.executeUpdate("insert into modelbox values ('"+ nom + "','" + version + "')");						
		
		if(statement != null)
			statement.close();					
	}	
	
	/**
	 * Mettre à jour une ModelBox en BDD
	 * @param modelBox ModelBox à mettre à jour
	 * @throws SQLException
	 */
	public void update(ModelBox modelBox) throws SQLException{
		
		if(modelBox == null)
			throw new IllegalArgumentException("Argument is null");
		
		String nom = modelBox.getNom();
		String nomProjet = modelBox.getNomProjet();
		String chemin = modelBox.getChemin();	
		
		statement = conn.createStatement();
		statement.executeUpdate("update modelbox set chemin='"+ chemin + "' where nom='" + nomProjet + "' and nomModel='" + nom + "'");
		if(statement != null)
			statement.close();		
	}
		
	/**
	 * Récupère une ModelBox de la BDD
	 * @param nomModelBox nom du ModelBox
	 * @return une ModelBox
	 * @throws SQLException
	 */
	public ModelBox get(String nomModelBox) throws SQLException{
		
		if(nomModelBox == null)
			throw new IllegalArgumentException("Argument is null");
		
		ModelBox modelBox = null;		
		
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from modelbox where nomModel='" + nomModelBox + "'");
		if (rs.next()){			
			//modelBox = new ModelBox(rs.getString(1), rs.getString(2), rs.getString(3));	
			modelBox = new ModelBox(rs.getString(1), rs.getString(2));	
		}				
		if (statement != null)
			statement.close();
		
		return modelBox;				
	}
	
	/**
	 * Supprime une ModelBox de la BDD
	 * @param nomModelBox nom du modelBox
	 * @throws SQLException
	 */
	public void delete(String nomModelBox) throws SQLException{
		
		if(nomModelBox == null)
			throw new IllegalArgumentException("Argument is null");
		
		statement = conn.createStatement();		
		statement.executeUpdate("delete from modelbox where nomModel='" + nomModelBox + "'");
		
		if(statement != null)
			statement.close();
	}		
	
	/**
	 * Retourne tous les ModelBox sauvegardées en BDD
	 * @return une liste de ModelBox
	 * @throws SQLException
	 */
	public ArrayList<ModelBox> getAll() throws SQLException{
		
		ArrayList<ModelBox> listProjet = new ArrayList<ModelBox>();
		ModelBox projet = null;
		
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from modelbox");

		while (rs.next()) {

			//projet = new ModelBox(rs.getString(1), rs.getString(2), rs.getString(3));
			projet = new ModelBox(rs.getString(1), rs.getString(2));
			listProjet.add(projet);
		}
		if (statement != null)
			statement.close();

		return listProjet;
	}
	
	/**
	 * Retourne toutes les ModelBox d'un Projet
	 * @param nomProjet nom du Projet
	 * @return une liste de ModelBox appartenant à un Projet
	 * @throws SQLException
	 */
	public ArrayList<ModelBox> getAll(String nomProjet) throws SQLException{
		
		if(nomProjet == null)
			throw new IllegalArgumentException("Argument is null");
		
		ArrayList<ModelBox> listProjet = new ArrayList<ModelBox>();
		ModelBox projet = null;
		
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from modelbox where nom='" + nomProjet + "'");

		while (rs.next()) {

			projet = new ModelBox(rs.getString(1), rs.getString(2), nomProjet);
			listProjet.add(projet);
		}
		if (statement != null)
			statement.close();

		return listProjet;
	}
	
}
