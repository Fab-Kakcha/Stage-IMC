package Agadir.ApplicationGraphique;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import controller.MenuPrincipalController;
import view.MenuPrincipalUI;

public class Main {


	private static Logger logger = Logger.getLogger(Main.class);
	private static String dbURL = "jdbc:derby:C:\\Users\\fkakcha\\MyDB;create=true;user=admin;password=admin";
	private static String derbyDriver = "org.apache.derby.jdbc.EmbeddedDriver";
	
	private static DataBaseManagement dataBaseManagement;
	
	private static MenuPrincipalUI menuPrincipalUI;	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			PropertyConfigurator.configure("C:\\Users\\fkakcha\\workspace\\properties\\log4j.properties");

			dataBaseManagement = new DataBaseManagement(derbyDriver, dbURL);
			// new Thread(dataBaseManagement).start();
			
			menuPrincipalUI = new MenuPrincipalUI();
			new MenuPrincipalController(menuPrincipalUI, dataBaseManagement);

			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					// agadirGenerateurUI.setVisible(true);
					menuPrincipalUI.setVisible(true);
				}
			});

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			CustomException.errorPrint(e);
		}
	}// Fin du Main
		
		
}
