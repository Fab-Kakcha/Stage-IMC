package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Etat;

public class EtatManager {

	private Connection conn;
	private static Statement statement;


	public EtatManager(Connection conn) {
		this.conn = conn;
	}

	public void save(Etat etat) throws SQLException{
		
		String code = etat.getCode();
		String intitule = etat.getIntitule();
		
		statement = conn.createStatement();
		statement.executeUpdate("insert into etat values ('" + code + "','"+ intitule +"')");
		statement.close();
	}
	
	public void delete(Etat etat) throws SQLException{
		
		String code = etat.getCode();
		
		statement = conn.createStatement();
		statement.executeUpdate("delete from etat where code_etat='" + code + "'");
		statement.close();
	}
	
	public Etat get(String nomProjet) throws SQLException{
		
		Etat etat = null;
		
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from projetetat where nom_projet='" + nomProjet + "'");
		if(rs.next()){
			
			String codeEtat = rs.getString(4);
			rs = statement.executeQuery("select * from etat where code_etat='" + codeEtat + "'");
			
			if(rs.next()){
				
				String intitule = rs.getString(2);
				etat = new Etat(codeEtat, intitule);
			}						
		}
			
		statement.close();;
		return etat;
	}
	
}
