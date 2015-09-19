package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Librairie;
import model.Projet;

/**
 * Récupérer les modèles, le groupe de paramètre, et les paramètres d'un projet à partir du nom du projet.
 * @author fkakcha
 *
 */

public class ProjetManager {

	private Connection conn;
	private static Statement statement;
	
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
		String version = projet.getChemin();			
		
		statement = conn.createStatement();		
		statement.executeUpdate("insert into projets values ('"+ nom + "','" + version + "')");						
		
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
		ResultSet rs = statement.executeQuery("select * from projets where nom='" + nomProjet + "'");
		if (rs.next()){			
			projet = new Projet(rs.getString(1), rs.getString(2));		
		}
				
		if (statement != null)
			statement.close();
		
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
		statement.executeUpdate("delete from projets where nom_='" + nom + "'");
		
		if(statement != null)
			statement.close();
	}	
	
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


