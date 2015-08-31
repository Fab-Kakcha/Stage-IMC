package crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ModelesManager {
	
	private Connection conn;
	private static Statement statement;
	
	public ModelesManager(Connection conn) {
		this.conn = conn;
	}


	public void saveModele(Modeles modeles) throws SQLException{
		
		String nom = modeles.getNom();
		String description = modeles.getDescription();
		
		statement = conn.createStatement();
		statement.executeUpdate("insert into modeles values ('" + nom + "','"+ description +"')");
		statement.close();
	}
	
	public void updateModele(Modeles modele) throws SQLException{
		
		String nomModele = modele.getNom();
		String description = modele.getDescription();
		
		statement = conn.createStatement();
        statement.executeUpdate("update from modeles set description='"+ description +"' where nom_modele='" + nomModele + "'");
        if(statement != null)
			statement.close();      
	}
	
	public void deleteModele(Modeles modeles) throws SQLException{
		
		String nom = modeles.getNom();
		statement = conn.createStatement();
		statement.executeUpdate("delete from modeles where nom_modele='" + nom + "'");
		statement.close();		
	}
	
	public ArrayList<Modeles> getModele(String nomProjet) throws SQLException{
		
		ArrayList<Modeles> listModeles = new ArrayList<Modeles>();		
		Modeles modeles = null;
		Statement st = null;
		ResultSet rs1;
		String nomModele, descriptionModele;
		
		statement = conn.createStatement();
		st = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from projetmodeles where nom_projet='" + nomProjet +"'");
		
		while(rs.next()){
			
			nomModele = rs.getString(2);
			rs1 = st.executeQuery("select * from modeles where nom_modele='" + nomModele + "'");
			if(rs1.next()){
				
				descriptionModele = rs1.getString(2);	
				modeles = new Modeles(nomModele, descriptionModele);
				listModeles.add(modeles);
			}
		}
				
		return listModeles;				
	}
		
}
