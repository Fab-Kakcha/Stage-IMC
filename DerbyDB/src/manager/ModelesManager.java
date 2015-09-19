package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.Modeles;

public class ModelesManager {
	
	private Connection conn;
	private static Statement statement;
	
	
	public ModelesManager(Connection conn) {
		this.conn = conn;
	}

	public void save(Modeles modeles) throws SQLException{
		
		String nom = modeles.getNom();
		String description = modeles.getDescription();
		
		statement = conn.createStatement();
		statement.executeUpdate("insert into modeles values ('" + nom + "','"+ description +"')");
		statement.close();
	}
	
	public void update(Modeles modele) throws SQLException{
		
		String nomModele = modele.getNom();
		String description = modele.getDescription();
		
		statement = conn.createStatement();
        statement.executeUpdate("update from modeles set description='"+ description +"' where nom_modele='" + nomModele + "'");
        if(statement != null)
			statement.close();      
	}
	
	public void delete(Modeles modeles) throws SQLException{
		
		String nom = modeles.getNom();
		statement = conn.createStatement();
		statement.executeUpdate("delete from modeles where nom_modele='" + nom + "'");
		statement.close();		
	}
	
	public ArrayList<Modeles> getModeles() throws SQLException{
		
		ArrayList<Modeles> listModeles = new ArrayList<Modeles>();
		Modeles modeles = null;

		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from modeles");

		while (rs.next()) {

			modeles = new Modeles(rs.getString(1), rs.getString(2));
			listModeles.add(modeles);
		}
		if (statement != null)
			statement.close();

		return listModeles;
   }
	
	public Modeles getModeles(String nomModele) throws SQLException{
		
		Modeles modeles = null;
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select description from modeles where nom_modele='" + nomModele + "'");
		if(rs.next())
			modeles = new Modeles(nomModele, rs.getString(1));
		
		if (statement != null)
			statement.close();
		
		return modeles;		
	}
		
}
