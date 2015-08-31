//CRUD = Create, Read, Update, Delete

package crud;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Ajouter le nom du projet dans la table projetgroupeParametre.
 * */

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	private static String dbURL = "jdbc:derby:C:\\Users\\fkakcha\\MyDB;create=true;user=admin;password=admin";
	private static String derbyDriver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static DataBaseManagement dataBaseManagement = null;
	private static AgadirGenerateurUI agadirGenerateurUI;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			PropertyConfigurator.configure("C:\\Users\\fkakcha\\workspace\\properties\\log4j.properties");
			
			agadirGenerateurUI = new AgadirGenerateurUI();
			AgadirGenController agadirGenController = new AgadirGenController(agadirGenerateurUI);

			java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	agadirGenerateurUI.setVisible(true);
	            }
	        });			
			
			dataBaseManagement = new DataBaseManagement(derbyDriver,dbURL);
			//dataBaseManagement.dropAllTables();
			//dataBaseManagement.createTables();			
						
			//--------------------------------------------------------------------------------------------------
			/**
			 * Instantiation des classes
			 */
			Parametre param = new Parametre(1, "param1", "Bdx");
			Parametre param2 = new Parametre(2, "param2", "Gironde");
			Parametre param3 = new Parametre(3, "param3", "Aquitaine");			
			GroupeParametre groupeParam = new GroupeParametre(1, "groupe1");
			
			Modeles modele = new Modeles("model1", "modele unique");
			Modeles modele2 = new Modeles("model2", "modele unique");
			
			Etat etat = new Etat("1", "ouvert");
			Etat etat2 = new Etat("2", "ferme");
			Projet projet = new Projet("projet1", "version1", "code1");
			Projet projet2 = new Projet("projet2", "version1", "code1");
			
			//Declaration des Managers
			ParametreManager paraManager = new ParametreManager(dataBaseManagement.getConnection());			
			EtatManager etatManager = new EtatManager(dataBaseManagement.getConnection());
			ModelesManager modelesManager = new ModelesManager(dataBaseManagement.getConnection());		
			ProjetManager projetManager = new ProjetManager(dataBaseManagement.getConnection());
			
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
			projet.addGroupeParametre(groupeParam);
			projet.addModele(modele);
			//projet.setEtat(etat);
						
			//projetManager.save(projet);			
			//projetManager.deleteParametre(projet, param);
			//projet.toPrint();
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			dataBaseManagement.errorPrint(e);
			
		}finally{		
			dataBaseManagement.shutdown(dbURL);
		}
	}	
}
