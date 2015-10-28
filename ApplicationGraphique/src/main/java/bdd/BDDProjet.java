package bdd;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import chargement.fichiers.config.ChargementFichiersConfiguration;

public class BDDProjet {

	private static Logger logger = Logger.getLogger(BDDProjet.class);
	
	private static String dbURL;
	private static String derbyDriver;
	private String bddPath;
	
	private Connection conn;
	private Statement statement;
	
	public void creer(String projetPath) throws IOException{		
		
		try {
			
			Properties props = new Properties();
			props.load(ChargementFichiersConfiguration.class.getResourceAsStream("/properties/bddprojet.properties"));
			
			Properties p = new Properties();
			p.put("create", "true");
			p.put("user", props.getProperty("bdd.login"));
			p.put("password", props.getProperty("bdd.password"));
			
			derbyDriver = props.getProperty("bdd.driver");
			
			bddPath = projetPath + File.separator+".metadata"+File.separator+"bdd";
			dbURL = "jdbc:derby:"+bddPath;
			
			Class.forName(derbyDriver).newInstance();
			conn = DriverManager.getConnection(dbURL, p);
			logger.info(conn);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void creerTables() throws SQLException{
		
		statement = conn.createStatement();
		
		statement.execute("create table modelbox (nomModel varchar(50) not null, "
				+ "chemin varchar(500) not null)");
		//statement.execute("create table profiles (nom varchar(50) not null constraint profils_pk primary key, "
				//+ "chemin varchar(500) not null)");
		
		if(statement != null)
			statement.close();	
	}
	
	
	public void suppressionTables() throws SQLException{
		
		statement = conn.createStatement();
		
		//statement.executeUpdate("alter table profiles drop constraint profils_pk");
		//statement.executeUpdate("drop table profiles");
		statement.executeUpdate("drop table modelbox");
		
		if(statement != null)
			statement.close();	
	}
	
	/**
	 * Arrêter la base de données
	 */
	public void shutdown() {

		boolean gotSQLExc = false;

		try {
			if (statement != null)
				statement.close();

			if (conn != null) {
				conn.close();

				DriverManager.getConnection(dbURL + ";shutdown=true");
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			if (se.getSQLState().equals("08006")) {
				gotSQLExc = true;
			}
			// TODO Auto-generated catch block
		}
		
		if (!gotSQLExc) {
			logger.info("Database did not shut down normally");
		} else {
			logger.info("Database shut down normally");
		}		 
	}
}
