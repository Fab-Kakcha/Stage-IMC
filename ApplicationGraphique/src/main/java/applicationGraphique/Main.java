package applicationGraphique;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import bdd.BDDApplication;
import codegenerateur.GenerateurCodesSources;
import controller.MenuPrincipalController;
import exception.CustomException;
import view.MenuPrincipalUI;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	//private static String dbURL = "jdbc:derby:C:\\Users\\fkakcha\\MyDB;create=true;user=admin;password=admin";
	//private static String derbyDriver = "org.apache.derby.jdbc.EmbeddedDriver";
	
	private static String dbURL;
	private static String derbyDriver;
	
	private static BDDApplication dataBaseManagement;
	
	private static MenuPrincipalUI menuPrincipalUI;	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Properties props= new Properties();
			props.load(new FileInputStream(new File(args[0])));
			PropertyConfigurator.configure(props.getProperty("log.file"));
			
			Options options = GenerateurCodesSources.commandLineDefinition();			
			CommandLineParser parser = new BasicParser();
			CommandLine cmd = parser.parse(options, args);
			dbURL="jdbc:derby:"+props.getProperty("bdd.url")+";create=true;user="+props.getProperty("bdd.login")+";password="+props.getProperty("bdd.password");
			derbyDriver = props.getProperty("bdd.driver");
			
			dataBaseManagement = new BDDApplication(derbyDriver, dbURL);
			dataBaseManagement.destructionTables();
			dataBaseManagement.creationDeTables();
			
			menuPrincipalUI = new MenuPrincipalUI();
			new MenuPrincipalController(menuPrincipalUI, dataBaseManagement, cmd);

			logger.info("Lancement de l'application graphique");
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					// agadirGenerateurUI.setVisible(true);
					menuPrincipalUI.setVisible(true);
				}
			});						
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CustomException.errorPrint(e);
		}				
		
	}// Fin du Main
				
}
