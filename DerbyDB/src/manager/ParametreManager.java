package manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.GroupeParametre;
import model.Parametre;

import org.apache.log4j.Logger;

public class ParametreManager {

	private static Logger logger = Logger.getLogger(ParametreManager.class);
	private Connection conn;
	private static Statement statement;
	private HashMap<Integer, Parametre> listParametre;
	private GroupeParametre groupeParam = null;
	private Parametre parametre = null;
	
	public ParametreManager(Connection conn){
		
		this.conn = conn;
	}
		
	public void save(Parametre param) throws SQLException{
		
		String nom, valeur;
		int id;
		
		nom = param.getNom();
		valeur = param.getValeur();
		id = param.getId();
		
		statement = conn.createStatement();
		statement.executeUpdate("insert into parametre values ("+id + ",'" + nom + "','"+ valeur +"')");
		statement.close();
	}
	
	public Parametre get(int id) throws SQLException{
		
		Parametre parametre = null;		
		ResultSet rs = statement.executeQuery("select * from parametre where id_param=" + id + "");
		if(rs.next()){
			
			parametre = new Parametre(id, rs.getString(2), rs.getString(3));
		}		
		if (statement != null)
			statement.close();
		
		return parametre;
	}
	
	public ArrayList<Parametre> getList() throws SQLException{
		
		ArrayList<Parametre> listParametre = new ArrayList<Parametre>();
		Parametre parametre = null;
		
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from parametre");

		while (rs.next()) {

			parametre = new Parametre(rs.getInt(1), rs.getString(2), rs.getString(3));
			listParametre.add(parametre);
		}
		if (statement != null)
			statement.close();

		return listParametre;
	}
	
	//Vérifier que le param appartient bien au projet avant de l'updater
	public void updateNomParametre(Parametre param) throws SQLException{		
		
		String nomParam = param.getNom();
		int id = param.getId();
		
		statement = conn.createStatement();
		statement.executeUpdate("update parametre set nom='"+ nomParam + "' where id_param=" + id + "");
		statement.close();
	}
	
	public void updateValeurParametre(Parametre param) throws SQLException{
		
		String valeurParam = param.getNom();
		int id = param.getId();
		
		statement = conn.createStatement();
		statement.executeUpdate("update parametre set valeur='"+ valeurParam + "' where id_param=" + id + "");
		statement.close();
	}
	
	public void delete(Parametre param) throws SQLException{
		
		String tableName;
		int id;
		
		id = param.getId();
		tableName = "parametre";
		
		statement = conn.createStatement();
		statement.executeUpdate("delete from "+tableName+" where id_param=" + id);
		statement.close();
	}
	
		
	public void save(GroupeParametre groupeParam) throws SQLException{
		
		int id = groupeParam.getId();
		String nom = groupeParam.getNom();
		String tableName = "groupeparametre";
			
		listParametre = groupeParam.getAllParametre();
		statement = conn.createStatement();
		statement.executeUpdate("insert into "+tableName+" values ("+ id + ",'" + nom + "')");

		for(Parametre param : listParametre.values()){
						
			//statement.executeUpdate("insert into "+tableName+"(id_grpeParam, nom, id_param) values ("+ id + ",'" + nom + "',"+param.getId()+")", 
				//	Statement.RETURN_GENERATED_KEYS);
			
			statement.executeUpdate("insert into listeparametresgroupes values ("+ id + "," + param.getId() + ")");
			//ResultSet rs = statement.getGeneratedKeys();
			
			//while(rs.next())				
				//id1 = rs.getInt(1);
						
		}			
		statement.close();								
		//groupeParam.setGeneratedKey(id1);
	}
	
	//Penser à renvoyer une liste de groupe de paramètres, car un projet peut avoir plusieurs groupe de
	// groupe de paramètres
	public GroupeParametre getGroupeParametre(String nomProjet) throws SQLException{
		
		Statement statement1 = conn.createStatement();
		Statement statement2 = conn.createStatement();
		statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from projetgroupeparametre where nom_projet='" + nomProjet + "'");
		int idParamGrpe, idParam= 0;
		
		//Mettre un while pour renvoyer un groupe de groupe de paramètres
		if (rs.next()) {

			idParamGrpe = rs.getInt(2);					
			//Récupérer le nom du groupe dans la table groupaparametre
			//Pensez peut être à faire un JOIN entre les tables listeparametresgroupes et parametre
			rs = statement.executeQuery("select * from groupeparametre where id_grpeParam=" + idParamGrpe + "");
			String nameGrpeParam = null;
			ResultSet rs1, rs2;
			String nomParam = null, valeurParam = null;
			
			if(rs.next()) {
				
				nameGrpeParam = rs.getString(2);
				groupeParam = new GroupeParametre(idParamGrpe, nameGrpeParam);
				
				//Récupérer l'id de tous les paramètres dans la table listeparametresgroupes
				rs1 = statement1.executeQuery("select * from listeparametresgroupes where id_grpeParam="+ idParamGrpe + "");
				while (rs1.next()) {

					idParam = rs1.getInt(2);					
					rs2 = statement2.executeQuery("select * from parametre where id_param="+ idParam + "");
					
					if(rs2.next()){
						nomParam = rs2.getString(2);
						valeurParam = rs2.getString(3);
						
						parametre = new Parametre(idParam, nomParam, valeurParam);
						groupeParam.add(parametre);
					}
				}
			}
			
			//groupeParam.setId(grpeParamID);						
		}		

		if (statement != null)
			statement.close();
		if(statement1 != null)
			statement1.close();
		if(statement2 != null)
			statement1.close();

		return groupeParam;
	}
}
