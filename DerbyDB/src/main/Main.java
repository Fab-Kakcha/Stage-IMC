//CRUD = Create, Read, Update, Delete

package main;

import model.Etat;
import model.GroupeParametre;
import model.Modeles;
import model.Parametre;
import model.Projet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import view.*;
import controller.MenuPrincipalController;

/**
 * Ajouter le nom du projet dans la table projetgroupeParametre.
 * */

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	private static String dbURL = "jdbc:derby:C:\\Users\\fkakcha\\MyDB;create=true;user=admin;password=admin";
	private static String derbyDriver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static DataBaseManagement dataBaseManagement = null;
	//private static AgadirGenerateurUI agadirGenerateurUI;
	private static MenuPrincipalUI menuPrincipalProjetUI;
	
	private static long startTime = System.currentTimeMillis();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			PropertyConfigurator.configure("C:\\Users\\fkakcha\\workspace\\properties\\log4j.properties");
			
			/**
			 * Instantiation des classes
			 */
			Parametre param = new Parametre(1, "param1", "Bdx");		
			GroupeParametre groupeParam = new GroupeParametre(1, "groupe1");			
			Modeles modele = new Modeles("model1", "modele unique");		
			Etat etat = new Etat("1", "ouvert");
			Projet projet = new Projet("projet1", "version1", "code1");
			
			//------------------------------------------------------------------------------
			dataBaseManagement = new DataBaseManagement(derbyDriver,dbURL);
			new Thread(dataBaseManagement).start();
			
			//dataBaseManagement.dropAllTables();
			//dataBaseManagement.createTables();
			
			//agadirGenerateurUI = new AgadirGenerateurUI();
			menuPrincipalProjetUI = new MenuPrincipalUI();
			new MenuPrincipalController(menuPrincipalProjetUI, dataBaseManagement);
			
			//new AgadirGenController(agadirGenerateurUI, dataBaseManagement);
			
			java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	//agadirGenerateurUI.setVisible(true);
	            	menuPrincipalProjetUI.setVisible(true);	            	
	            }
	        });									
			
			long endTime = System.currentTimeMillis();
			System.out.println("It took " + (endTime - startTime) + " milliseconds");
			
			//----------------------------------------------------------------------------------------------------------
			/**
			 * Enregistrer les paramètres, les états, et les modèles en base de données dans leurs tables 
			 * respectives. Sinon, il y aura violation des clés étrangères
			 */
			//groupeParam.add(param);
			//groupeParam.add(param2);
			
			//paraManager.saveParametre(param);
			//paraManager.saveParametre(param2);
			//paraManager.saveParametre(groupeParam);	
			
			//etatManager.saveEtat(etat);
			//modelesManager.saveModele(modele);
			
			//------------------------------------------------------------------------------------------------------------			
			/**
			 * Ecriture en base de données de tous les éléments qui constituent un projet
			 */
			//projet.addGroupeParametre(groupeParam);
			//projet.addModele(modele);
			//projet.setEtat(etat);
						
			//projetManager.save(projet);			
			//projetManager.deleteParametre(projet, param);
			//projet.toPrint();
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			CustomException.errorPrint(e);			
		}
	}	
}
