package manager;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.Librairie;

/**
 * Cette classe interagit avec la BDD. Elle permet de faire le CRUD sur une Librairie. 
 * @author fkakcha
 *
 */

public class LibrairieManager {
	
	private Connection conn;
	private static Statement statement;
	
	/**
	 * Constructeur pour initialiser la valeur du paramètre de connexion à la BDD.
	 * @param conn identifiant de connexion à la BDD.
	 */
	public LibrairieManager(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Sauvegarde en BDD d'un objet Librairie 
	 * @param librairie objet Librairie à sauvegarder
	 * @throws SQLException
	 */
	public void save(Librairie librairie) throws SQLException{
		
		if(librairie == null)
			throw new IllegalArgumentException("Argument is null");
		
		String nom = librairie.getNom();
		String description = librairie.getCheminFichier();
		
		statement = conn.createStatement();
		statement.executeUpdate("insert into librairies values ('" + nom + "','"+ description +"')");
		statement.close();
	}
	
	/**
	 * Mise à jour en BDD d'un objet Librairie.
	 * @param librairie objet Librairie à mettre en jour.
	 * @throws SQLException
	 */
	public void update(Librairie librairie) throws SQLException{
		
		if(librairie == null)
			throw new IllegalArgumentException("Argument is null");
		
		String nomModele = librairie.getNom();
		String description = librairie.getCheminFichier();
		
		statement = conn.createStatement();
        statement.executeUpdate("update librairies set chemin='"+ description +"' where nom='" + nomModele + "'");
        if(statement != null)
			statement.close();      
	}
	
	/**
	 * Suppression en BDD d'une Librairie.
	 * @param librairie Librairie à supprimer.
	 * @throws SQLException
	 */
	public void delete(String nomLibrairie) throws SQLException{
		
		if(nomLibrairie == null)
			throw new IllegalArgumentException("Argument is null");
		
		statement = conn.createStatement();
		statement.executeUpdate("delete from librairies where nom='" + nomLibrairie + "'");
		statement.close();		
	}
	
	/**
	 * Récupérer de la BDD tous les objets Librairie
	 * @return une liste de Librairie
	 * @throws SQLException
	 */
	public ArrayList<Librairie> getAll() throws SQLException{
		
		ArrayList<Librairie> listModeles = new ArrayList<Librairie>();
		Librairie modeles = null;

		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from librairies");

		while (rs.next()) {

			modeles = new Librairie(rs.getString(1), rs.getString(2));
			listModeles.add(modeles);
		}
		if (statement != null)
			statement.close();

		return listModeles;
   }
	
	/**
	 * Recherche en BDD d'une Librairie à partir de son nom. 
	 * @param nomLibrairie nom de la Librairie
	 * @return une Librairie
	 * @throws SQLException
	 */
	public Librairie get(String nomLibrairie) throws SQLException{
		
		if(nomLibrairie == null)
			throw new IllegalArgumentException("Argument is null");
		
		Librairie modeles = null;
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select chemin from librairies where nom='" + nomLibrairie + "'");
		if(rs.next())
			modeles = new Librairie(nomLibrairie, rs.getString(1));
		
		if (statement != null)
			statement.close();
		
		return modeles;		
	}		
}
