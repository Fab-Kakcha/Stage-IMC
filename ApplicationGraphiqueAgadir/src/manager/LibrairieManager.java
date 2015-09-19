package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.Librairie;

public class LibrairieManager {
	
	private Connection conn;
	private static Statement statement;
	
	
	public LibrairieManager(Connection conn) {
		this.conn = conn;
	}

	public void save(Librairie modeles) throws SQLException{
		
		String nom = modeles.getNom();
		String description = modeles.getCheminFichier();
		
		statement = conn.createStatement();
		statement.executeUpdate("insert into librairies values ('" + nom + "','"+ description +"')");
		statement.close();
	}
	
	public void update(Librairie modele) throws SQLException{
		
		String nomModele = modele.getNom();
		String description = modele.getCheminFichier();
		
		statement = conn.createStatement();
        statement.executeUpdate("update from librairies set chemin='"+ description +"' where nom='" + nomModele + "'");
        if(statement != null)
			statement.close();      
	}
	
	public void delete(Librairie modeles) throws SQLException{
		
		String nom = modeles.getNom();
		statement = conn.createStatement();
		statement.executeUpdate("delete from librairies where nom='" + nom + "'");
		statement.close();		
	}
	
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
	
	public Librairie get(String nomLibrairie) throws SQLException{
		
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
