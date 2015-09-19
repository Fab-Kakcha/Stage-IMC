package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.*;

public class ProfileManager {
	
	private Connection conn;
	private static Statement statement;
	
	
	public ProfileManager(Connection conn) {
		this.conn = conn;
	}

	public void save(Profile modeles) throws SQLException{
		
		String nom = modeles.getNom();
		String description = modeles.getCheminFichier();
		
		statement = conn.createStatement();
		statement.executeUpdate("insert into profiles values ('" + nom + "','"+ description +"')");
		statement.close();
	}
	
	public void update(Profile modele) throws SQLException{
		
		String nomModele = modele.getNom();
		String description = modele.getCheminFichier();
		
		statement = conn.createStatement();
        statement.executeUpdate("update from profiles set chemin='"+ description +"' where nom='" + nomModele + "'");
        if(statement != null)
			statement.close();      
	}
	
	public void delete(Profile modeles) throws SQLException{
		
		String nom = modeles.getNom();
		statement = conn.createStatement();
		statement.executeUpdate("delete from profiles where nom='" + nom + "'");
		statement.close();		
	}
	
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
	
	public Librairie get(String nomLibrairie) throws SQLException{
		
		Librairie modeles = null;
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select chemin from profiles where nom='" + nomLibrairie + "'");
		if(rs.next())
			modeles = new Librairie(nomLibrairie, rs.getString(1));
		
		if (statement != null)
			statement.close();
		
		return modeles;		
	}		
}
