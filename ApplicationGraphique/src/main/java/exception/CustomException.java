package exception;

import java.sql.SQLException;

/**
 * Classe qui permet de personnaliser les Exceptions.
 * @author fkakcha
 *
 */

public class CustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomException(String msg){
		super(msg);
	}

	// Iterates through a stack of SQLExceptions
	public static void SQLExceptionPrint(SQLException sqle) {
		while (sqle != null) {
			System.out.println("\n---SQLException Caught---\n");
			System.out.println("SQLState: " + (sqle).getSQLState());
			System.out.println("Severity: " + (sqle).getErrorCode());
			System.out.println("Message: " + (sqle).getMessage());
			sqle.printStackTrace();
			sqle = sqle.getNextException();
		}
	} // END SQLExceptionPrint

	
	 public static void errorPrint(Throwable e) {
		if (e instanceof SQLException)
			SQLExceptionPrint((SQLException) e);
		else {
			System.out.println("A non SQL error occured.");
			e.printStackTrace();
		}
	} 
	
	 
	 public static String getSQLState(Throwable e){
		 
		 String sqlState = null;		 
		 if(e instanceof SQLException){
			 
			 SQLException se = (SQLException)e;
			 sqlState = se.getSQLState();
		 }
		 
		 return sqlState;
	 }	
}
