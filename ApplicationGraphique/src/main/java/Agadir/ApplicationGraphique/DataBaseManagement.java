package Agadir.ApplicationGraphique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DataBaseManagement implements Runnable{

	private Logger logger = Logger.getLogger(DataBaseManagement.class);
	private  Connection conn;
	private Statement statement;
	private String dbURL;
	
	public DataBaseManagement(String driver, String dbURL){
		
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
				+ "chemin varchar(100) not null)");
		statement.execute("create table profiles (nom varchar(50) not null constraint profils_pk primary key, "
				+ "chemin varchar(100) not null)");
		statement.execute("create table projets (nom varchar(50) not null constraint projets_pk primary key, "
				+ "chemin varchar(100) not null)");
		
		if(statement != null)
			statement.close();	
	}
	
	public void destructionTables() throws SQLException{
		
		statement = conn.createStatement();
		statement.executeUpdate("alter table librairies drop constraint librairie_pk");
		statement.executeUpdate("alter table profiles drop constraint profils_pk");
		statement.executeUpdate("alter table projets drop constraint projets_pk");
		statement.executeUpdate("drop table librairies");
		statement.executeUpdate("drop table profiles");
		statement.executeUpdate("drop table projets");
		
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
			System.out.println("\n Database did not shut down normally");
		} else {
			System.out.println("\n Database shut down normally");
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
