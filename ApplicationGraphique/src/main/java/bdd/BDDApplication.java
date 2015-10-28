package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import exception.CustomException;

public class BDDApplication implements Runnable{

	private Logger logger = Logger.getLogger(BDDApplication.class);
	private  Connection conn;
	private Statement statement;
	private String dbURL;
	
	/**
	 * Constructeur pour initialiser le driver de la BDD ainsi que l'URL de connexion
	 * @param driver valeur du driver de la BDD
	 * @param dbURL URL de connexion à la BDD
	 */
	public BDDApplication(String driver, String dbURL){
		
		try {
			
			this.dbURL = dbURL;
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(dbURL);
						
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			
			if(e instanceof SQLException){
				
				SQLException sqle = (SQLException)e;
				if(sqle.getSQLState().equals("XJ040")){
					
					sqle = sqle.getNextException();
					if(sqle.getSQLState().equals("XSDB6")){
						
						logger.error(sqle.getMessage() + " Please shutdown that other instance of Derby.");
					}				
				}
			}else{
				
				CustomException.errorPrint(e);
			}						
		}
	}
		
	public Connection getConnection(){		
		return this.conn;
	}
	
	public String getDbURL(){
		return this.dbURL;
	}
	
	/**
	 * Table pour enregistrer le nom et le chemin des fichiers de configurations
	 * @throws SQLException
	 */
	public void creationDeTables() throws SQLException{
		
		statement = conn.createStatement();
		statement.execute("create table librairies (nom varchar(50) not null constraint librairie_pk primary key, "
				+ "chemin varchar(500) not null)");

		statement.execute("create table projets (nom varchar(50) not null constraint projets_pk primary key, "
				+ "chemin varchar(500) not null)");
		//statement.execute("create table modelbox (nomModel varchar(50) not null, "
				//+ "chemin varchar(500) not null, "
				//+ "nom varchar(50) not null constraint modelbox_fk references projets on delete cascade on update restrict)");
				
		if(statement != null)
			statement.close();	
	}
	
	/**
	 * Destruction de toutes les tables en BDD
	 * @throws SQLException
	 */
	public void destructionTables() throws SQLException{
		
		statement = conn.createStatement();
		statement.executeUpdate("alter table librairies drop constraint librairie_pk");
		//statement.executeUpdate("alter table profiles drop constraint profils_pk");
		//statement.executeUpdate("alter table modelbox drop constraint modelbox_pk");
		//statement.executeUpdate("alter table modelbox drop constraint modelbox_fk");
		statement.executeUpdate("alter table projets drop constraint projets_pk");
		
		statement.executeUpdate("drop table librairies");
		//statement.executeUpdate("drop table profiles");
		statement.executeUpdate("drop table projets");
		//statement.executeUpdate("drop table modelbox");
		
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			destructionTables();
			creationDeTables();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(CustomException.getSQLState(e).equals("42Y55")){
				
				try {
					creationDeTables();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					CustomException.errorPrint(e1);
				}
			}				
			else
				CustomException.errorPrint(e);				
		}		
	}
}
