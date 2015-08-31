
package myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Restaurants {

	private static Logger logger = Logger.getLogger(Restaurants.class);
	private static String dbURL = "jdbc:derby:C:\\Users\\fkakcha\\MyDB;create=true;user=admin;password=admin";
	private static String tableName = "restaurants";
	private static String sqlCommand;
	private static Connection conn;
	private static Statement statement;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PropertyConfigurator.configure("C:\\Users\\fkakcha\\workspace\\properties\\log4j.properties");
				
		createConnection();
		insertRestaurants(5,"Kebab", "Bordeaux");
		
		selectRestaurants();
		shutdown();
	}

	private static void createConnection(){
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			conn = DriverManager.getConnection(dbURL);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void insertRestaurants(int id, String restName, String cityName){
		
		try {
			statement = conn.createStatement();
			statement.execute("insert into "+tableName+" values ("+id + ",'" + restName + "','"+ cityName +"')");
			statement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void selectRestaurants(){
		
		try {
			statement = conn.createStatement();
			sqlCommand = "select * from " + tableName;
			ResultSet results = statement.executeQuery(sqlCommand);
			ResultSetMetaData metaData = results.getMetaData();
			int numCols = metaData.getColumnCount();
			
			for(int i=1; i<=numCols; i++){
				
				System.out.print(metaData.getColumnLabel(i)+"\t\t");
			}
			
			System.out.println("\n---------------------------------------------");
			
			int id;
			String restName, cityName;
			
			while(results.next()){
				
				id = results.getInt(1);
				restName = results.getString(2);
				cityName = results.getString(3);
				
				System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
			}
			
			results.close();
			statement.close();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void shutdown(){
		
		try {
			if (statement != null)

				statement.close();

			if (conn != null) {
				conn.close();
				
				DriverManager.getConnection(dbURL + ";shutdown=true");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
}
