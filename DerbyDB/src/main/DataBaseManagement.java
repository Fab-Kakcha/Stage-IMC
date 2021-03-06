package main;

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
	 * Création des tables de base de données. Huit tables sont crées au total.
	 * @throws SQLException
	 */
	public void createTables() throws SQLException{
					
			statement = conn.createStatement();
			statement.execute("create table etat (code_etat varchar(20) not null constraint etat_pk primary key, "
					+ "intitule varchar(50) not null)");
			statement.execute("create table projetetat (nom_projet varchar(20) not null constraint projetetat_pk primary key, "
					+ "version varchar(20) not null, "
					+ "code varchar(20), "
					+"code_etat varchar(20) constraint etat_fk references etat on delete cascade on update restrict)");
			statement.execute("create table parametre (id_param int not null constraint parametre_pk primary key, "
					+ "nom varchar(50) not null, valeur varchar(20))");	
			
			//statement.execute("create table parametre (id_param int not null constraint parametre_pk primary key, "
				//	+ "nom varchar(20) not null, "
				//	+ "valeur varchar(20), "
				//	+ "nom_projet varchar(20))");
			statement.execute("create table modeles (nom_modele varchar(20) not null constraint modeles_pk primary key, "
					+ "description varchar(50) not null)");
			statement.execute("create table groupeparametre (id_grpeParam int not null constraint groupeparametre_pk primary key,"
					//+"id integer not null generated always as identity (start with 1, increment by 1) constraint groupeparametre_pk primary key, "
					+ "nom varchar(20) not null)");
					//+ "id_param int constraint parametre_fk references parametre on delete cascade on update restrict)");
			//statement.execute("create table projetgroupeparametre (nom_projet varchar(20) not null constraint projetgroupeparametre_pk primary key, "
				//	+ "version varchar(20) not null, "
				//	+ "code varchar(20), "
				//	+ "grpeParam_id int not null, "
				//	+ "grpeParam_name varchar(20) not null, "
					//+"id_grpeParam int constraint projetgrpeparam_fk references groupeparametre on delete cascade on update restrict)");
				//	+"id int constraint projetgrpeparam_fk references groupeparametre on delete cascade on update restrict), "
				//	+ " ");
			statement.execute("create table projetgroupeparametre (nom_projet varchar(20) not null constraint projetetat_fk references projetetat on delete cascade on update restrict, "
					//+ "grpeParam_name varchar(20) not null, "
					+"id_grpeParam int constraint groupeparametre_fk references groupeparametre on delete cascade on update restrict)");
					//+"id int constraint groupeparametre_fk references groupeparametre on delete cascade on update restrict)");
			
			//statement.execute("create table projetmodeles (nom_projet varchar(20) not null constraint projetmodele_pk primary key, "
				//	+ "version varchar(20) not null, "
					//+ "code varchar(20), "
					//+"nom_modele varchar(20) constraint modeles_fk references modeles on delete cascade on update restrict)");
			statement.execute("create table projetmodeles (nom_projet varchar(20) not null constraint projetetat_fk2 references projetetat on delete cascade on update restrict, "
					+"nom_modele varchar(20) constraint modeles_fk references modeles on delete cascade on update restrict)");
			
			statement.execute("create table listeparametresgroupes (id_grpeParam int constraint groupeparametre_fk2 references groupeparametre on delete cascade on update restrict, "
					+ "id_param int constraint parametre_fk2 references parametre on delete cascade on update restrict)");
									
			if(statement != null)
				statement.close();	
	}
	
	/**
	 * Destruction d'une table particulière
	 * @param tableName nom de la table à détruire
	 * @param constraint_pk nom d'une contrainte de clé primaire s'il y en a
	 * @param constraint_fk nom d'une contrainte de clé étrangère s'il y en a
	 */
	public void dropTable(String tableName, String constraint_pk, String constraint_fk){
		
		try {
			statement = conn.createStatement();
			
			if(constraint_pk != null)
				statement.executeUpdate("alter table " + tableName + " drop constraint " + constraint_fk);
			
			if(constraint_fk != null)
				statement.executeUpdate("alter table " + tableName + " drop constraint " + constraint_pk);
			
			statement.executeUpdate("drop table " + tableName);
			logger.info("INFO: table " + tableName + " dropped");	
			statement.close();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Destruction de toutes les tables en base de données.
	 * @throws SQLException
	 */
	public void dropAllTables() throws SQLException {
			
		String[] tableNames = new String[] { "etat", "parametre", "groupeparametre", "modeles", "projetgroupeparametre",
				"projetmodeles", "projetetat", "listeparametresgroupes"};
		String[] constraintName = new String[] { "etat_pk", "parametre_pk",
				"groupeparametre_pk", "modeles_pk", "projetetat_fk",
				"projetetat_fk2", "projetetat_pk" };

		statement = conn.createStatement();

		statement
				.executeUpdate("alter table projetgroupeparametre drop constraint groupeparametre_fk");
		statement
				.executeUpdate("alter table projetmodeles drop constraint modeles_fk");
		statement
				.executeUpdate("alter table projetetat drop constraint etat_fk");
		statement
				.executeUpdate("alter table listeparametresgroupes drop constraint parametre_fk2");
		statement
				.executeUpdate("alter table listeparametresgroupes drop constraint groupeparametre_fk2");
		
		for (int i = 0; i < tableNames.length - 1; i++) {

			statement.executeUpdate("alter table " + tableNames[i] + " drop constraint " + constraintName[i]);
		}

		for (int i = 0; i < tableNames.length; i++) {

			statement.executeUpdate("drop table " + tableNames[i]);
		}
		
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
			dropAllTables();
			createTables();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			CustomException.errorPrint(e);				
		}		
	}
}
