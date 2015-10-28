package controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.SwingWorker;

import manager.*;
import model.*;
import applicationGraphique.*;
import bdd.*;
import view.*;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import chargement.fichiers.config.ChargementFichiersConfiguration;

import com.imcfr.generateur.xmi.exceptions.ResourceSetLoadingException;

import codegenerateur.GenerateurCodesSources;
import exception.CustomException;


/**
 * Cette classe réalise des actions. Ces actions sont déclenchées suite à l'interaction de l'utilisateur avec l'application graphique.
 * Toutes les actions ont été prises en considération.
 * @author fkakcha
 *
 */

//Plus besoin de prendre les commandes des utilisateur en ligne de commande.
//Appeler toutes les méthodes de la classe GénérateurCodesSources: pensez à définir et à charger un workspace. Il faut également charger
//les librairies et les profiles lors de la config du générateur. De même, il faut charger les modelsBox lors de la config d'un projet.


public class MenuPrincipalController {

	private static Logger logger = Logger.getLogger(MenuPrincipalController.class);
	private final static int nbreDeLibrairie = 8;
	private final static int nbreDeProfile= 4;
	private final static int nbreDeTextField = nbreDeLibrairie + nbreDeProfile;
	private final static int nbreDeModelBox = 8;
	
	private File file;
	private BDDApplication dataBaseManagement;
	private BDDProjet bddProjet;
	private String nomProjet;

	//Définition de tous les UI
	private ConfigGenerateurUI configGenerateurProjetUI;
	private CreerProjetUI creerProjetUI;
	private ConfigProjetUI configProjetUI;
	private MenuPrincipalUI menuPrincipalUI;
		
	//Déclaration des Managers qui vont se connecter et interagir avec la BDD
	private LibrairieManager librairieManager;
	private ProfileManager profileManager;
	private ProjetManager projetManager;
	private ModelBoxManager modelBoxManager;
	
	private GenerateurCodesSources generateur;
	private ChargementFichiersConfiguration chargementFichiersConfiguration;
	
	private ArrayList<Projet> listProjetsEnBDD = new ArrayList<Projet>();
	private HashMap<String, Connection> correspNomProjetConnectionBDD = new HashMap<String, Connection>();

	/**
	 * Ce constructeur permet d'initialiser l'IHM principale de l'application graphique et la base de données.
	 * @param menuPrincipalUI IHM principale de l'application graphique. Des évènements succeptibles de déclencher des actions sont
	 * enregistrés sur les composants des IHMs de l'application graphique.
	 * @param dataBaseManagement Base de données interagissant avec l'application.
	 * @throws SQLException
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public MenuPrincipalController(MenuPrincipalUI menuPrincipalUI, BDDApplication dataBaseManagement, CommandLine cmd) 
			throws SQLException, ParseException, IOException {
		
		this.dataBaseManagement = dataBaseManagement;
		this.menuPrincipalUI =  menuPrincipalUI;
		configGenerateurProjetUI = menuPrincipalUI.getConfigGenerateurProjetUI();
		creerProjetUI = menuPrincipalUI.getCreerProjetUI();
		configProjetUI = menuPrincipalUI.getConfigProjetUI();
		
		//Ajout de Listener sur l'UI du menu principal
		menuPrincipalUI.addJMenuItem1Listener(new ActionMenuItemPerformed());
		menuPrincipalUI.addJMenuItem2Listener(new ActionMenuItemPerformed());
		menuPrincipalUI.addJMenuItem3Listener(new ActionMenuItemPerformed());
		menuPrincipalUI.addJMenuItem4Listener(new ActionMenuItemPerformed());
		
		//Ajout de Listener sur l'UI de configuration du générateur
		configGenerateurProjetUI.addJButton1Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		configGenerateurProjetUI.addJButton2Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		configGenerateurProjetUI.addJButton3Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		configGenerateurProjetUI.addJButton4Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		configGenerateurProjetUI.addJButton5Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		configGenerateurProjetUI.addJButton6Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		configGenerateurProjetUI.addJButton7Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		configGenerateurProjetUI.addJButton8Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		configGenerateurProjetUI.addJButton9Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		configGenerateurProjetUI.addJButton10Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		configGenerateurProjetUI.addJButton11Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		configGenerateurProjetUI.addJButton12Listener(new ActionChoisirFichierDeConfigGenerateurPerformed());
		
		configGenerateurProjetUI.addJButton13Listener(new ActionEnregistrerPerformed());
		configGenerateurProjetUI.addJButton14Listener(new ActionAnnulerPerformed());
		
		//Ajout de Listener sur l'UI de création de projet
		creerProjetUI.addJButton3Listener(new ActionEnregistrerPerformed());
		creerProjetUI.addJButton4Listener(new ActionAnnulerPerformed());
		
		//Ajout de listener sur l'UI de configuration du projet
		configProjetUI.addJButton1Listener(new ActionChoisirFichierDeConfigProjetPerformed());
		configProjetUI.addJButton2Listener(new ActionChoisirFichierDeConfigProjetPerformed());
		configProjetUI.addJButton3Listener(new ActionChoisirFichierDeConfigProjetPerformed());
		configProjetUI.addJButton4Listener(new ActionChoisirFichierDeConfigProjetPerformed());
		configProjetUI.addJButton5Listener(new ActionChoisirFichierDeConfigProjetPerformed());
		configProjetUI.addJButton6Listener(new ActionChoisirFichierDeConfigProjetPerformed());
		configProjetUI.addJButton7Listener(new ActionChoisirFichierDeConfigProjetPerformed());
		configProjetUI.addJButton8Listener(new ActionChoisirFichierDeConfigProjetPerformed());
		configProjetUI.addJButton9Listener(new ActionOutputProjetPerformed());
		
		configProjetUI.addJButton13Listener(new ActionEnregistrerPerformed());
		configProjetUI.addJButton14Listener(new ActionAnnulerPerformed());
		
		//Instantiation des managers pour accéder à la BDD
		librairieManager = new LibrairieManager(dataBaseManagement.getConnection());
		projetManager = new ProjetManager(dataBaseManagement.getConnection());
		
		//profileManager = new ProfileManager(dataBaseManagement.getConnection());
		//modelBoxManager = new ModelBoxManager(dataBaseManagement.getConnection());
		
		//Récupération des projets existant en BDD
		recupérationProjetEnBDD();
		
		//Chargement des fichiers de config à partir des fichiers de properties
		chargementFichiersConfiguration = new ChargementFichiersConfiguration();
		//chargementFichiersConfiguration.chargementFichiersProfile(profileManager);
		//chargementFichiersConfiguration.chargementFichiersModelBox(modelBoxManager);
		chargementFichiersConfiguration.chargementFichiersLibrairie(librairieManager);
			
		//Initialisation du générateur
		initialisationGenerateur(cmd);
	}
	
	private void initialisationGenerateur(CommandLine cmd) throws ParseException{
		
		generateur = new GenerateurCodesSources();
		
		generateur.loadConfig(cmd);
		generateur.init();
		generateur.load();
	}
	
	
	public void getProjectFromDB() throws SQLException, IOException{
		
		SwingWorker<Void,Void> swingWorker = new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				
				Random random = new Random();
				int progressBar = 0;
				setProgress(0);
				
				listProjetsEnBDD = projetManager.getAll();
				int size = listProjetsEnBDD.size();
				
				Projet projet = null;
				Iterator<Projet> ite = listProjetsEnBDD.iterator();
				
				while(ite.hasNext()){
					
					projet = ite.next();
					bddProjet.creer(projet.getChemin());
					
					correspNomProjetConnectionBDD.put(projet.getNom(), bddProjet.getConn());
					
					progressBar += progressBar + size%100;
					setProgress(progressBar);
				}
				
				return null;
			}
			
			public void done(){
				
				
				//menuPrincipalUI.creationDynamiqueDeLabelEtBouton(listNomProjetEnBDD);
				//menuPrincipalUI.addListBoutonOuvrirListener(new ActionOuvrirProjetPerformed());
				//menuPrincipalUI.addListBoutonSupprimerListener(new ActionSupprimerProjetPerformed());
			}
			
			
		};
		
		swingWorker.execute();
		swingWorker.addPropertyChangeListener(new ActionPropertyChangeListenerChargementProjet());
		
	}
	
	
	class ActionPropertyChangeListenerChargementProjet implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			
			 if ("progress".equals(evt.getPropertyName())) {
				 
				 int progress = (Integer)evt.getNewValue();
				 //creerProjetUI.getJProgressBar().setValue(progress);				 
             }
			
		}		
	}
	
	/**
	 * Récupère et affiche les projets en BDD sur l'UI
	 * @throws SQLException
	 */
	private void recupérationProjetEnBDD() throws SQLException{
				
		ArrayList<String> listNomProjetEnBDD = new ArrayList<String>();
		listProjetsEnBDD = projetManager.getAll();
		
		Projet projet = null;
		Iterator<Projet> ite = listProjetsEnBDD.iterator();
		while(ite.hasNext()){
			
			projet = ite.next();
			listNomProjetEnBDD.add(projet.getNom());
		}
		
		menuPrincipalUI.creationDynamiqueDeLabelEtBouton(listNomProjetEnBDD);
		menuPrincipalUI.addListBoutonOuvrirListener(new ActionOuvrirProjetPerformed());
		menuPrincipalUI.addListBoutonSupprimerListener(new ActionSupprimerProjetPerformed());
	}
	
	/**
	 * Cette réalise l'action suite à l'appui du bouton "Ouvrir" par un utilisateur. Donc, cette classe permet l'ouverture d'un projet 
	 * afin de paramétrer les chemins vers les fichiers de configuration.
	 * @author fkakcha
	 *
	 */
	class ActionOuvrirProjetPerformed implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String commande = e.getActionCommand();
			
			String cheminModelBox;
			int i = 1;
			Projet projet = null;
			Iterator<Projet> ite = listProjetsEnBDD.iterator();
			
			while(ite.hasNext()){
				
				projet = ite.next();
				nomProjet = projet.getNom();
				if(commande.equals(nomProjet)){
										
					try {
						logger.info("Ouverture du: " + projet.toString());
						
						//bddProjet.creer(projet.getChemin());
						//modelBoxManager.setConn(bddProjet.getConn());
						
						ArrayList<ModelBox> listModelBox = modelBoxManager.getAll();						
						Iterator<ModelBox> ite1 = listModelBox.iterator();
						
						while(ite1.hasNext()){
							
							cheminModelBox = ite1.next().getChemin();
							configProjetUI.setTextField("jTextField"+i, cheminModelBox);
							i++;
						}						
						configProjetUI.faireApparaitre();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						CustomException.errorPrint(e1);
					}										
				}
			}
		}				
	}
	
	/**
	 * Cette réalise réalise l'action suite à l'appui du bouton "supprimer" par un utilisateur. Cette Classe permet de supprimer un projet de la BDD
	 * et l'UI.
	 * @author fkakcha
	 *
	 */
	class ActionSupprimerProjetPerformed implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String commande = e.getActionCommand();									
			try {
				
				int n = menuPrincipalUI.yesOrNoDialogBox("Voulez vous vraiment supprimer ce projet?");
				if(n == 0){
					
					Utils.suppressionDeDossier(commande);
					projetManager.delete(commande);
					recupérationProjetEnBDD();
				}
				
			} catch (SQLException | CustomException e1) {
				// TODO Auto-generated catch block
				CustomException.errorPrint(e1);
			}
		}				
	}
	
	/**
	 * Cette classe réalise l'action résultant de l'appui par un utilisateur d'un élément dans le menu.
	 * @author fkakcha
	 *
	 */
	class ActionMenuItemPerformed implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			//boolean saveEtat = false;			
			String commande = e.getActionCommand();
			
			//L'utilisateur a cliqué sur l'item permettant la création d'un projet
			if(commande.equals("Nouveau projet")){				

				creerProjetUI.faireApparaitre();
				creerProjetUI.setNomProjet(null);
				
			//L'utilisateur a cliqué sur l'item permettant de configurer les paramètres du générateur
			}else if(commande.equals("Paramètre generateur")){				
								
				try {
					
					ArrayList<Librairie> listModeles = new ArrayList<Librairie>();
					listModeles = librairieManager.getAll();	
					int i = 1;
					if(!listModeles.isEmpty()){
						
						Librairie librairie = null;
						Iterator<Librairie> ite = listModeles.iterator();
						while(ite.hasNext()){
							
							librairie = ite.next();
							configGenerateurProjetUI.setTextField("jTextField" + i, librairie.getCheminFichier());
							i++;
						}																							
					}
					
					ArrayList<Profile> listProfiles = new ArrayList<Profile>();
					listProfiles = profileManager.get();
					if(!listProfiles.isEmpty()){
						
						Profile profile = null;
						Iterator<Profile> ite1 = listProfiles.iterator();
						while(ite1.hasNext()){
							
							profile = ite1.next();
							configGenerateurProjetUI.setTextField("jTextField" + i, profile.getCheminFichier());
							i++;
						}							
					}
															
					configGenerateurProjetUI.faireApparaitre();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					CustomException.errorPrint(e1);
				}
				
			//L'utilisateur a cliqué sur l'item permettant de configurer les paramètres d'un projet
			}else if(commande.equals("Paramètre projet")){
				
				configProjetUI.faireApparaitre();
				
			//L'utilisateur a cliqué sur l'item pour quitter l'application graphique
			}else if(commande.equals("Quitter")){
				
				dataBaseManagement.shutdown();
				System.exit(0);
			}					
		}				
	}
	
	/**
	 * Cette Classe de choisir un fichier en local, puis d'afficher le chemin vers ce fichier
	 * @author fkakcha
	 *
	 */
	class ActionChoisirFichierDeConfigGenerateurPerformed implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
			String actionCommande = e.getActionCommand();					
			JFileChooser fc =  configGenerateurProjetUI.getFileChooser();
			int returnVal = fc.showOpenDialog(fc);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
													
				file = fc.getSelectedFile();
				String filePath = file.getPath();
				configGenerateurProjetUI.setTextFieldFromButton(actionCommande, filePath);
					
			}else if(returnVal == JFileChooser.CANCEL_OPTION){
								
			}else if(returnVal == JFileChooser.ERROR_OPTION){
					
				logger.error("Impossible de sélectionner le fichier");
			}			
		}				
	}
	
	/**
	 * Cette classe réalise l'action qui permet de sélectionner un fichier après que l'utilisateur ait cliqué sur le bouton "Choisir fichier" de
	 * l'UI de configuration du projet.
	 * @author fkakcha
	 *
	 */
	class ActionChoisirFichierDeConfigProjetPerformed implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
			String actionCommande = e.getActionCommand();							
			JFileChooser fc =  configProjetUI.getFileChooser();
			int returnVal = fc.showOpenDialog(fc);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
													
				file = fc.getSelectedFile();
				String filePath = file.getPath();
				configProjetUI.setTextFieldFromButton(actionCommande, filePath);
					
			}else if(returnVal == JFileChooser.CANCEL_OPTION){
								
			}else if(returnVal == JFileChooser.ERROR_OPTION){
							
				logger.error("Impossible de sélectionner le fichier");
			}			
		}				
	}
	
	/**
	 * Cette classe permet de sélectionner le répertoire de sorti lors de la configuration d'un projet. L'action est réalisée après l'appui sur le bouton
	 * "Output" de l'UI.
	 * @author fkakcha
	 *
	 */
	class ActionOutputProjetPerformed implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
								
			JFileChooser fc =  configProjetUI.getFolderChooser();
			int returnVal = fc.showOpenDialog(fc);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
				
				file = fc.getSelectedFile();
				String filePath = file.getPath();
				configProjetUI.setOutputField(filePath);
				
			}else if(returnVal == JFileChooser.CANCEL_OPTION){
								
			}else if(returnVal == JFileChooser.ERROR_OPTION){
							
			}			
		}				
	}
	
	/**
	 * Cette classe gère tous les boutons "Enregistrer" de l'application graphique. Chaque bouton "Enregistrer" est identifié de manière unique
	 * grâce à une commande. Cette classe permet également, à titre exceptionnel, de créer un projet.
	 * @author fkakcha
	 *
	 */
	class ActionEnregistrerPerformed implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		
			String actionCommande = arg0.getActionCommand();
			
			//L'utilisateur veut enregistrer les configurations du générateur
			if(actionCommande.equals("Enregistrer config generateur")){
				
				boolean textFieldFilled = configGenerateurProjetUI.verificationTextField();				
				if(textFieldFilled){
					
					int n = configGenerateurProjetUI.yesOrNoDialogBox("Confirmez vous l'enregistrement ?");				
					if(n == 0){				

						String chemin, nom;
						try {
							if(librairieManager.getAll().isEmpty()){
								
								for (int i = 1; i <= nbreDeLibrairie; i++) {

									nom = configGenerateurProjetUI.getLabel("jLabel" + i);
									chemin = configGenerateurProjetUI.getTextField("jTextField" + i);
									librairieManager.save(new Librairie(nom, chemin));
									
									//LoadFile.loadFile(chemin);// chargement du fichier 
									//generateur.libraryLoad(wokspaceName, nom de la libriairie)
								}
								
								for (int i = nbreDeLibrairie +1 ; i <= nbreDeTextField; i++) {

									nom = configGenerateurProjetUI.getLabel("jLabel" + i);
									chemin = configGenerateurProjetUI.getTextField("jTextField" + i);
									profileManager.save(new Profile(nom, chemin));
									
									//LoadFile.loadFile(chemin);// chargement du fichier 
									//generateur.profileLoad(wokspaceName, nom de la libriairie)
								}
								
							}else{ // mise à jour en BDD le chemin vers les fichiers de config du générateur
								
								for (int i = 1; i <= nbreDeLibrairie; i++) {

									nom = configGenerateurProjetUI.getLabel("jLabel" + i);
									chemin = configGenerateurProjetUI.getTextField("jTextField" + i);
									librairieManager.update(new Librairie(nom, chemin));
								}
								
								for (int i = nbreDeLibrairie +1 ; i <= nbreDeTextField; i++) {

									nom = configGenerateurProjetUI.getLabel("jLabel" + i);
									chemin = configGenerateurProjetUI.getTextField("jTextField" + i);
									profileManager.update(new Profile(nom, chemin));
								}
							}	
								
							configGenerateurProjetUI.faireDisparaitre();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							CustomException.errorPrint(e);
						}
						
					}else if(n == 1)
						configGenerateurProjetUI.infoDialogBox("Vous avez annulé l'enregistrement");
					
				}else{
					configGenerateurProjetUI.erreurDialogBox("Tous les champs sont obligatoires");
				}
				
			//L'utilisateur veut enregistrer les configurations du projet	
			}else if(actionCommande.equals("Enregistrer config projet")){
				
				boolean textFieldFilled = configProjetUI.verificationTextField();
				if(textFieldFilled){
					
					int n = configProjetUI.yesOrNoDialogBox("Confirmez vous l'enregistrement ?");
					if(n == 0){
						String chemin, nom;					
						
						try {
							//Idée: voir avec ce nom de projet, s'il existe en BDD des chemins vers des modelBox
							//Possiblité de mettre à jour des chemins de fichiers XML existants en BDD
							if(modelBoxManager.getAll(nomProjet).isEmpty()){
								
								//for (int i = 1; i <= nbreDeModelBox; i++) {

									nom = configProjetUI.getLabel("jLabel" + 1);
									chemin = configProjetUI.getTextField("jTextField" + 1);
									
									//LoadFile.loadFile(chemin);// chargement du fichier 
									modelBoxManager.save(new ModelBox(nom, chemin, nomProjet));
									
									////generateur.modelLoad(wokspaceName, nom de la libriairie);								
								//}
								logger.info("Sauvegarde terminée");
								
							}else{ // mise à jour en BDD des chemins vers les fichier de config d'un projet.
								
								for (int i = 1; i <= nbreDeModelBox; i++) {

									nom = configProjetUI.getLabel("jLabel" + i);
									chemin = configProjetUI.getTextField("jTextField" + i);
									modelBoxManager.update(new ModelBox(nom, chemin, nomProjet));
								}
								logger.info("mise à jour terminée");
							}							
							configProjetUI.faireDisparaitre();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							CustomException.errorPrint(e);
						}

					}else
						configProjetUI.infoDialogBox("Enregistrement annulé");
					
				}else
					configProjetUI.erreurDialogBox("Tous les champs sont obligatoires");

			//L'utilisateur veut créer un projet
			}else if(actionCommande.equals("Creer projet")){
				
				boolean bool = creerProjetUI.verificationNomProjet();
				if (bool) {
					// Enregistrer le nom du projet et le chemin du répertoire
					// local du projet en BDD
					try {
						
						nomProjet = creerProjetUI.getNomProjet();
						String cheminDuDossier = Utils.creationDeDossier(nomProjet);
						if (cheminDuDossier != null) {
							
							final Projet p = new Projet(nomProjet,cheminDuDossier);
							// menuPrincipalUI.activerJMenuItemParametreProjet();
							
							//Mettre en place un SwingWorker pour créer la BDD relative à un projet
							SwingWorker<Boolean, String> swingWorker = new SwingWorker<Boolean, String>(){

								@Override
								protected Boolean doInBackground()throws Exception {
									// TODO Auto-generated method stub
									
									creerProjetUI.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
									setProgress(0);									
									bddProjet = new BDDProjet();									
									setProgress(30);
									setProgress(40);
									setProgress(50);
									setProgress(60);
									
									bddProjet.creer(p.getChemin());
									setProgress(85);
									setProgress(90);
									setProgress(100);
									
									return null;
								}
								
								public void done(){
									
									try {
										creerProjetUI.getJProgressBar().setValue(0);
										creerProjetUI.setCursor(null);
										bddProjet.creerTables();									
										modelBoxManager = new ModelBoxManager(bddProjet.getConn());
										chargementFichiersConfiguration.chargementFichiersModelBox(modelBoxManager);
										
										logger.info("chargement fini");										
										
										//Enregistrer le projet dans la BDD de l'application avec la valeur de la connexion à sa BDD
										projetManager.save(p);
										recupérationProjetEnBDD();
										
										creerProjetUI.faireDisparaitre();
										
									} catch (SQLException | IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							};
							
							swingWorker.addPropertyChangeListener(new ActionPropertyChangeListener());							
							swingWorker.execute();																				

						} else
							creerProjetUI.erreurDialogBox("Un projet avec ce nom existe déjà");
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						CustomException.errorPrint(e);
					}

				} else
					creerProjetUI.erreurDialogBox("champ obligatoire");			
			}						
		}		
	}
	
	/**
	 * 
	 * @author fkakcha
	 *
	 */
	class ActionGeneratePerformed implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			String actionCommande = arg0.getActionCommand();
			if(actionCommande.equals("generate")){
				
				////generateur.generate();
			}
			
		}
				
	}
	
	/**
	 * Cette classe gère tous les boutons "Annuler" de l'application graphique
	 * @author fkakcha
	 *
	 */
	class ActionAnnulerPerformed implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			String actionCommande = arg0.getActionCommand();
			
			//Annulation de l'enregistrement des configurations du générateur
			if(actionCommande.equals("Annuler config generateur")){

				configGenerateurProjetUI.faireDisparaitre();
				for(int i = 1; i <= nbreDeTextField; i++){
					configGenerateurProjetUI.setTextField("jTextField" + i, null);
				}
				
			//Annulation de l'enregistrement des configurations d'un projet	
			}else if(actionCommande.equals("Annuler config projet")){				
				configProjetUI.faireDisparaitre();
				
			//Annulation la création d'un projet	
			}else if(actionCommande.equals("Annuler creation projet")){				
				creerProjetUI.faireDisparaitre();
			}			
		}				
	}
	
	
	class ActionPropertyChangeListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			
			 if ("progress".equals(evt.getPropertyName())) {
				 
				 int progress = (Integer)evt.getNewValue();
				 creerProjetUI.getJProgressBar().setValue(progress);				 
             }
			
		}		
	}
}
