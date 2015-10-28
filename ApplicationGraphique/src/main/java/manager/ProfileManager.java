package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.Profile;

/**
 * Cette classe interagit avec la BDD. Elle permet de faire le CRUD sur un Profile. 
 * @author fkakcha
 *
 */

public class ProfileManager {
	
	private Connection conn;
	private static Statement statement;
	
	
	/**
	 * Constructeur pour initialiser la valeur du paramètre de connexion à la BDD.
	 * @param conn identifiant de connexion à la BDD
	 */
	
	public ProfileManager(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Sauvegarder en BDD, la valeur d'un Profile.
	 * @param profile Profile à sauvegarder en BDD
	 * @throws SQLException
	 */
	public void save(Profile profile) throws SQLException{
		
		if(profile == null)
			throw new IllegalArgumentException("Argument is null");
		
		String nom = profile.getNom();
		String description = profile.getCheminFichier();
		
		statement = conn.createStatement();
		statement.executeUpdate("insert into profiles values ('" + nom + "','"+ description +"')");
		statement.close();
	}
	
	/**
	 * Mettre à jour en BDD la valeur d'un Profile
	 * @param profile Profile à mettre à jour 
	 * @throws SQLException
	 */
	public void update(Profile profile) throws SQLException{
		
		if(profile == null)
			throw new IllegalArgumentException("Argument is null");
		
		String nomModele = profile.getNom();
		String description = profile.getCheminFichier();
		
		statement = conn.createStatement();
        statement.executeUpdate("update profiles set chemin='"+ description +"' where nom='" + nomModele + "'");
        if(statement != null)
			statement.close();      
	}
	
	/**
	 * Supprimer un Profile de la BDD
	 * @param profile Profile à supprimer
	 * @throws SQLException
	 */
	public void delete(String nomProfile) throws SQLException{
		
		if(nomProfile == null)
			throw new IllegalArgumentException("Argument is null");
		
		statement = conn.createStatement();
		statement.executeUpdate("delete from profiles where nom='" + nomProfile + "'");
		statement.close();		
	}
	
	/**
	 * Retourne la liste de tous les Profiles en BDD
	 * @return une liste de Profiles
	 * @throws SQLException
	 */
	public ArrayList<Profile> get() throws SQLException{
		
		ArrayList<Profile> listModeles = new ArrayList<Profile>();
		Profile modeles = null;

		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from profiles");

		while (rs.next()) {

			modeles = new Profile(rs.getString(1), rs.getString(2));
			listModeles.add(modeles);
		}
		if (statement != null)
			statement.close();

		return listModeles;
   }
	
	/**
	 * Recherche en BDD d'un Profile à partir de son nom.
	 * @param nomProfile nom du Profile
	 * @return un Profile
	 * @throws SQLException
	 */
	public Profile get(String nomProfile) throws SQLException{
		
		if(nomProfile == null)
			throw new IllegalArgumentException("Argument is null");
		
		Profile modeles = null;
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select chemin from profiles where nom='" + nomProfile + "'");
		if(rs.next())
			modeles = new Profile(nomProfile, rs.getString(1));
		
		if (statement != null)
			statement.close();
		
		return modeles;		
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}	
	
}
