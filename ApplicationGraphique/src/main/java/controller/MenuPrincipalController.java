package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;

import main.*;
import manager.*;
import model.*;

import org.apache.log4j.Logger;

import view.ConfigGenerateurProjetUI;
import view.CreerProjetUI;
import view.MenuPrincipalUI;

public class MenuPrincipalController {

	private static Logger logger = Logger.getLogger(MenuPrincipalController.class);
	private final static int nbreDeLibrairie = 8;
	private final static int nbreDeProfile= 4;
	private final static int nbreDeTextField = nbreDeLibrairie + nbreDeProfile;
	
	private File file, folder;
	//private DataBaseManagement dataBaseManagement;
	
	private ConfigGenerateurProjetUI configGenerateurProjetUI;
	private CreerProjetUI creerProjetUI;
	//private MenuPrincipalUI menuPrincipalUI;
	
	private String nomProjet;
	
	//Déclaration des Managers
	private LibrairieManager librairieManager;
	private ProfileManager profileManager;
	private ProjetManager projetManager;

	
	public MenuPrincipalController(MenuPrincipalUI menuPrincipalUI, DataBaseManagement dataBaseManagement) 
			throws SQLException {
		
		//this.dataBaseManagement = dataBaseManagement;
		//this.menuPrincipalUI =  menuPrincipalUI;
		configGenerateurProjetUI = menuPrincipalUI.getConfigGenerateurProjetUI();
		creerProjetUI = menuPrincipalUI.getCreerProjetUI();
		
		//Ajout de Listener sur l'UI du menu principal
		menuPrincipalUI.addJMenuItem1Listener(new ActionMenuItemPerformed());
		menuPrincipalUI.addJMenuItem2Listener(new ActionMenuItemPerformed());
		menuPrincipalUI.addJMenuItem3Listener(new ActionMenuItemPerformed());
		menuPrincipalUI.addJMenuItem4Listener(new ActionMenuItemPerformed());
		
		//Ajout de Listener sur l'UI de configuration
		configGenerateurProjetUI.addJButton1Listener(new ChoisirUnFichierAction());
		configGenerateurProjetUI.addJButton2Listener(new ChoisirUnFichierAction());
		configGenerateurProjetUI.addJButton3Listener(new ChoisirUnFichierAction());
		configGenerateurProjetUI.addJButton4Listener(new ChoisirUnFichierAction());
		configGenerateurProjetUI.addJButton5Listener(new ChoisirUnFichierAction());
		configGenerateurProjetUI.addJButton6Listener(new ChoisirUnFichierAction());
		configGenerateurProjetUI.addJButton7Listener(new ChoisirUnFichierAction());
		configGenerateurProjetUI.addJButton8Listener(new ChoisirUnFichierAction());
		configGenerateurProjetUI.addJButton9Listener(new ChoisirUnFichierAction());
		configGenerateurProjetUI.addJButton10Listener(new ChoisirUnFichierAction());
		configGenerateurProjetUI.addJButton11Listener(new ChoisirUnFichierAction());
		configGenerateurProjetUI.addJButton12Listener(new ChoisirUnFichierAction());
		
		configGenerateurProjetUI.addJButton13Listener(new EnregistrerAction());
		configGenerateurProjetUI.addJButton14Listener(new AnnulerAction());
		
		//Ajout de Listener sur l'UI de création de projet
		creerProjetUI.addJButton3Listener(new EnregistrerAction());
		creerProjetUI.addJButton4Listener(new AnnulerAction());
		
		//Instantiation des managers
		librairieManager = new LibrairieManager(dataBaseManagement.getConnection());
		profileManager = new ProfileManager(dataBaseManagement.getConnection());
		projetManager = new ProjetManager(dataBaseManagement.getConnection());
		
		//Récupération des projets existant en BDD
		recupérationProjetEnBDD();
		
		ArrayList<String> nomProjet = new ArrayList<String>();
		nomProjet.add("projet1");
		nomProjet.add("Projet2");
		nomProjet.add("Projet3");
		
		menuPrincipalUI.creationDynamiqueDeLabelEtBouton(nomProjet);
	}
	
	private void recupérationProjetEnBDD() throws SQLException{
				
		ArrayList<Projet> listProjets = new ArrayList<Projet>();
		listProjets = projetManager.getAll();
		
		Projet projet = null;
		Iterator<Projet> ite = listProjets.iterator();
		while(ite.hasNext()){
			
			projet = ite.next();
			logger.info(projet.toString());
		}
	}
	
	
	class ActionMenuItemPerformed implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			//boolean saveEtat = false;			
			String commande = e.getActionCommand();
			if(commande.equals("Nouveau projet")){				

				creerProjetUI.faireApparaitre();
				
			}else if(commande.equals("Paramètre")){				
								
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
					}else
						logger.info("listModeles est vide");
					
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
					}else
						logger.info("listProfiles est vide");
															
					configGenerateurProjetUI.faireApparaitre();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}else if(commande.equals("Paramètre projet")){
				
				
			}
			else if(commande.equals("Quitter")){
				
				//dataBaseManagement.shutdown();
				System.exit(0);
			}					
		}				
	}
	
	class ChoisirUnFichierAction implements ActionListener{

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
							
			}			
		}				
	}
	
	class EnregistrerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		
			String actionCommande = arg0.getActionCommand();
			
			if(actionCommande.equals("Enregistrer configurations")){
				
				boolean textFieldFilled = configGenerateurProjetUI.verificationTextField();
				
				if(textFieldFilled){
					
					int n = configGenerateurProjetUI.yesOrNoDialogBox("Confirmez vous l'enregistrement ?");				
					if(n == 0){				

						String chemin, nom;

						try {
							for (int i = 1; i <= nbreDeLibrairie; i++) {

								nom = configGenerateurProjetUI.getLabel("jLabel" + i);
								chemin = configGenerateurProjetUI.getTextField("jTextField" + i);
								librairieManager.save(new Librairie(nom, chemin));
							}
							
							for (int i = nbreDeLibrairie +1 ; i <= nbreDeTextField; i++) {

								nom = configGenerateurProjetUI.getLabel("jLabel" + i);
								chemin = configGenerateurProjetUI.getTextField("jTextField" + i);
								profileManager.save(new Profile(nom, chemin));
							}

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							CustomException.errorPrint(e);
						}
						
					}else if(n == 1)
						configGenerateurProjetUI.infoDialogBox("Vous avez annulé l'enregistrement");
					
				}else{
					
					configGenerateurProjetUI.erreurDialogBox("Tous les champs sont obligatoires");
				}
				
			}else if(actionCommande.equals("Creer projet")){
				
				boolean bool = creerProjetUI.verificationNomProjet();
				if(bool){
					
					//Enregistrer le nom du projet et le chemin du répertoire local du projet en BDD
					nomProjet = creerProjetUI.getNomProjet();					
					folder = new File("C:\\Users\\fkakcha\\workspace\\ApplicationGraphiqueAgadir\\projets Agadir\\" + nomProjet);
					boolean b = folder.mkdir();
					if(b){		
						
						try {
							logger.info(folder.getPath() + " est crée");
							projetManager.save(new Projet(nomProjet,folder.getPath()));
							creerProjetUI.faireDisparaitre();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}								
					}											
				}else
					creerProjetUI.erreurDialogBox("champ obligatoire");
			}											
		}		
	}
	
	class AnnulerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			for(int i = 1; i <= nbreDeTextField; i++){
				configGenerateurProjetUI.setTextField("jTextField" + i, null);
			}
		}				
	}
}
