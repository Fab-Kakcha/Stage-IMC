package crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EtatManager {

	private Connection conn;
	private static Statement statement;


	public EtatManager(Connection conn) {
		this.conn = conn;
	}

	public void saveEtat(Etat etat) throws SQLException{
		
		String code = etat.getCode();
		String intitule = etat.getIntitule();
		
		statement = conn.createStatement();
		statement.executeUpdate("insert into etat values ('" + code + "','"+ intitule +"')");
		statement.close();
	}
	
	public void deleteEtat(Etat etat) throws SQLException{
		
		String code = etat.getCode();
		
		statement = conn.createStatement();
		statement.executeUpdate("delete from etat where code_etat='" + code + "'");
		statement.close();
	}
	
	public Etat getEtat(String nomProjet) throws SQLException{
		
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
