package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

import javax.swing.SwingWorker;

import main.CustomException;
import main.DataBaseManagement;
import manager.EtatManager;
import manager.ModelesManager;
import manager.ParametreManager;
import manager.ProjetManager;
import model.*;

import org.apache.log4j.Logger;

import view.*;


public class MenuPrincipalController {

	private static Logger logger = Logger.getLogger(MenuPrincipalController.class);
	
	private EnregistrerProjetUI projetUI = null;
	private OuvrirProjetUI ouvrirProjetUI = null;
	private GroupeParametreUI grpeParamUI;
	private ModeleUI modeleUI;
	private ParametreUI parametreUI;
	
	private String nomProjet;
	private String versionProjet;
	private String codeProjet;
	
	private String nomModele;
	private String descriptionModele;
	
	private String grpeParamId;
	private String grpeParamNom;
	
	private String parametreId ;
	private String parametreNom;
	private String parametreValeur;
	
	private ParametreManager paraManager;
	private EtatManager etatManager;	
	private ModelesManager modelesManager;
	private ProjetManager projetManager;
	
	private DataBaseManagement dataBaseManagement;
	
	private Parametre parametre;
	private GroupeParametre grpeParam;
	private Modeles modele;
	private Projet projet;
	private Etat etat1, etat2;
	
	private boolean saveEtat;
	
	public MenuPrincipalController(MenuPrincipalUI menuPrincipalUI, DataBaseManagement dataBaseManagement) throws SQLException{
		
		this.projetUI = menuPrincipalUI.getEnregistrerProjetUI();
		this.ouvrirProjetUI = menuPrincipalUI.getOuvrirProjetUI();
		this.dataBaseManagement = dataBaseManagement;
		
		modeleUI = projetUI.getModeleUI();
		grpeParamUI = projetUI.getGrpeParamUI();
		parametreUI = projetUI.getParamUI();
			
		menuPrincipalUI.addJMenuItem4Listener(new ActionMenuItemPerformed());
		menuPrincipalUI.addJMenuItem3Listener(new ActionMenuItemPerformed());
		menuPrincipalUI.addJMenuItem1Listener(new ActionMenuItemPerformed());
		
		//On ajoute des listeners sur tous les boutons des IHMs		
		projetUI.addJButton1Listener(new ActionEnregistrementProjetPerformed());
		projetUI.addJButton2Listener(new ActionEnregistrementProjetPerformed());
		projetUI.addJButton3Listener(new ActionEnregistrementProjetPerformed());
		projetUI.addJButton4Listener(new ActionEnregistrementProjetPerformed());
		
		grpeParamUI.addJButton1Listener(new ActionEnregistrementProjetPerformed());
		grpeParamUI.addJButton2Listener(new ActionEnregistrementProjetPerformed());
		grpeParamUI.addJButton3Listener(new ActionEnregistrementProjetPerformed());
		
		parametreUI.addJButton1Listener(new ActionEnregistrementProjetPerformed());
		parametreUI.addJButton2Listener(new ActionEnregistrementProjetPerformed());
		
		modeleUI.addJButton1Listener(new ActionEnregistrementProjetPerformed());
		modeleUI.addJButton2Listener(new ActionEnregistrementProjetPerformed());
		
		//Instantiation des Managers pour accéder à la BDD
		paraManager = new ParametreManager(dataBaseManagement.getConnection());			
		etatManager = new EtatManager(dataBaseManagement.getConnection());
		modelesManager = new ModelesManager(dataBaseManagement.getConnection());		
		projetManager = new ProjetManager(dataBaseManagement.getConnection());
		
		//
		ouvrirProjetUI.addJButton1Listener(new ActionOuvertureProjetPerformed());
		ouvrirProjetUI.addJButton2Listener(new ActionOuvertureProjetPerformed());
		
		//Définition de l'état d'un projet. Un projet ne peut avoir que 2 états possibles.				
		etat1 = new Etat("1", "ouvert"); 
		etat2 = new Etat("2", "ferme");	
		saveEtat = false;
	}
	
	
	class ActionOuvertureProjetPerformed implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			String actionCommand = arg0.getActionCommand();
			if(actionCommand.equals("okOuvertureProjet")){
				
				if(ouvrirProjetUI.verificationTextField()){
					
					String nomProjet = ouvrirProjetUI.getNomProjet();
					//Faire des requêtes dans la BDD
					try {
						projet = projetManager.get(nomProjet);
						if(projet == null)
							ouvrirProjetUI.erreurDialogBox("Ce projet n'existe pas");
						else{
							
							HashMap<String, Modeles> listModeles = new HashMap<String, Modeles>();
							listModeles = projetManager.getModele(projet);
							projet.setListModele(listModeles);
							
							HashMap<Integer, GroupeParametre> listGrpeParam = new HashMap<Integer, GroupeParametre>();
							listGrpeParam = projetManager.getGroupeParametre(projet);
							projet.setListGroupeParam(listGrpeParam);		
							
							projet.toPrint();
							ouvrirProjetUI.cache();
							ouvrirProjetUI.effacerNomProjet();
						}						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
					
				}else
					ouvrirProjetUI.erreurDialogBox("champ obligatoire");
				
			}else if(actionCommand.equals("annulerOuvertureProjet")){
				
				ouvrirProjetUI.cache();
				ouvrirProjetUI.effacerNomProjet();
			}				
		}
	}
	
	/**
	 * Cette classe traite réalise les actions lorsqu'un évènement est déclenché. Tous les évènement sont traités dans cette unique
	 * classe.
	 * @author fkakcha
	 *
	 */
	class ActionEnregistrementProjetPerformed implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			String actionCommand = arg0.getActionCommand();
			if (actionCommand.equals("nouveauGroupeDeParametre")) {

				if (projetUI.verificationTextField()) {

					grpeParamUI.effacerJTextField();
					grpeParamUI.editerJTextField(true);

					nomProjet = projetUI.getProjetNom();
					versionProjet = projetUI.getProjetVersion();
					codeProjet = projetUI.getProjetCode();
					if (projet == null)
						projet = new Projet(nomProjet, versionProjet, codeProjet);

					projetUI.editerJTextField(false);
					grpeParamUI.setVisible();

				} else
					projetUI.erreurDialogBox("Veuillez remplir les champs obligatoires avant de continuer");

			} else if (actionCommand.equals("nouveauModele")) {

				if (projetUI.verificationTextField()) {

					modeleUI.effacerJTextField();

					nomProjet = projetUI.getProjetNom();
					versionProjet = projetUI.getProjetVersion();
					codeProjet = projetUI.getProjetCode();
					if (projet == null)
						projet = new Projet(nomProjet, versionProjet,codeProjet);

					projetUI.editerJTextField(false);
					modeleUI.setVisible();
				} else
					projetUI.erreurDialogBox("Veuillez remplir les champs obligatoires avant de continuer");

			} else if (actionCommand.equals("nouveauParametre")) {

				if (grpeParamUI.verificationTextField()) {

					parametreUI.effacerJTextField();

					grpeParamId = grpeParamUI.getGroupeParametreId();
					grpeParamNom = grpeParamUI.getGroupeParametreNom();

					// Un grpe de parametre contient plusieurs paramètres
					// Pb: il faut enregistrer plusieurs parametres dans un mm
					// groupe de parametre
					// donc il ne faut pas instancier groupeParam à chaque fois.
					// On vérifie s'il s'agit du même groupe de param
					// en utilisant l'id.
					
					try{
						if (grpeParam == null)
							grpeParam = new GroupeParametre(Integer.parseInt(grpeParamId), grpeParamNom);
						
						grpeParamUI.editerJTextField(false);
						parametreUI.setVisible();
					}catch(NumberFormatException nf){						
						grpeParamUI.erreurDialogBox("Le champ id est un entier naturel");						
					}										

				} else
					grpeParamUI.erreurDialogBox("Veuillez remplir les champs obligatoires avant de continuer");

			} else if (actionCommand.equals("enregistrerProjet")) {
				if (projetUI.verificationTextField()) {
					
					boolean exception = false;
					try {
						
						if(projet == null)
							projet = new Projet(projetUI.getProjetNom(), projetUI.getProjetVersion(), projetUI.getProjetCode());
							
						projet.setNom(projetUI.getProjetNom());
						projet.setEtat(etat1);
						projetManager.save(projet);
						
						//Penser peut être à afficher les configurations du projet sur l'IHM du MenuPrincipal
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						
						CustomException.errorPrint(e);						
						//dataBaseManagement.errorPrint(e);
						
						if(CustomException.getSQLState(e).equals("23505")){
							
							logger.info("Duplication de la clé primaire pour le Projet");
							projetUI.erreurDialogBox("Un projet avec ce nom existe déjà");
							projetUI.editerJTextField(true);
							exception = true;
						}					
					}
					
					projet = null;				
					if(!exception)
						projetUI.cache();
					
				}else
					projetUI.erreurDialogBox("Tous les champs sont obligatoires");

			} else if (actionCommand.equals("annulerProjet")) {

				projetUI.cache();;
			} else if (actionCommand.equals("enregistrerModele")) {

				if (modeleUI.verificationTextField()) {

					nomModele = modeleUI.getModeleNom();
					descriptionModele = modeleUI.getModeleDescription();
					modele = new Modeles(nomModele, descriptionModele);
					
					try {
						modele.setNom(nomModele);
						modelesManager.save(modele);
						projet.addModele(modele);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						CustomException.errorPrint(e);
						//dataBaseManagement.errorPrint(e);
						if(CustomException.getSQLState(e).equals("23505")){
							
							logger.info("Duplication de la clé primaire pour le Modele");
							modeleUI.erreurDialogBox("Un modele avec ce nom existe déjà");
							modeleUI.editerDescriptionModele(false);
						}
					}
					
					modeleUI.editerDescriptionModele(true);
					modeleUI.cache();
				} else
					modeleUI.erreurDialogBox("Tous les champs sont obligatoires");

			} else if (actionCommand.equals("annulerModele")) {

				modeleUI.cache();
			} else if (actionCommand.equals("enregistrerGrpeDePram")) {

				if (grpeParamUI.verificationTextField()) {
					
					boolean exception = false;
					
						if(grpeParam == null){
							grpeParamUI.erreurDialogBox("Veuillez définir des paramètres");							
						}else{
							try {
								grpeParam.setId(Integer.parseInt(grpeParamUI.getGroupeParametreId()));
								paraManager.save(grpeParam);
								projet.addGroupeParametre(grpeParam);
							
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								if(CustomException.getSQLState(e).equals("23505")){
									
									logger.info("Duplication de la clé primaire pour le Groupe de Paramètre");
									grpeParamUI.erreurDialogBox("Un Groupe de Paramètre avec cet id existe déjà. Veuillez modifier la valeur de l'id.");
									grpeParamUI.editerJTextField(true);
									exception = true;
								}										
							}catch(NumberFormatException nf){
								parametreUI.erreurDialogBox("Le champ id est un entier naturel");
								exception = true;
							}
							
							grpeParam = null;
							if(!exception)
								grpeParamUI.cache();							
						}																										
				} else
					grpeParamUI.erreurDialogBox("Tous les champs sont obligatoires");

			} else if (actionCommand.equals("annulerGrpeDePram")) {

				grpeParamUI.cache();
			} else if (actionCommand.equals("enregistrerParametre")) {

				if (parametreUI.verificationTextField()) {

					boolean bool = false;					
					try{
						parametreId = parametreUI.getParametreId();
						parametreNom = parametreUI.getParametreNom();
						parametreValeur = parametreUI.getParametreValeur();
						parametre = new Parametre(Integer.parseInt(parametreId), parametreNom, parametreValeur);
						paraManager.save(parametre);
						grpeParam.add(parametre);
						
					}catch(NumberFormatException nf){
						parametreUI.erreurDialogBox("Le champ id est un entier naturel");
						bool = true;
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						if(CustomException.getSQLState(e).equals("23505")){
							
							logger.info("Duplication de la clé primaire pour le Paramètre");
							parametreUI.erreurDialogBox("Le Paramètre avec cet id existe déjà. Veuillez modifier la valeur de l'id");
							bool = true;
						}	
					}
					if(!bool)
						parametreUI.cache();
				} else
					parametreUI.erreurDialogBox("Tous les champs sont obligatoires");

			} else if (actionCommand.equals("annulerParametre")) {
				parametreUI.cache();
			}
		}		
	}
	
	class ActionMenuItemPerformed implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			//boolean saveEtat = false;			
			String commande = e.getActionCommand();			
			if(commande.equals("nouveauProjet")){				
				if(!saveEtat){
					
					EnregistrerEtatEnDBB enregistrerEtatEnDBB = new EnregistrerEtatEnDBB();
					enregistrerEtatEnDBB.execute();

					saveEtat = true;					
				}
				
				projetUI.effacerJTextField();
				projetUI.setVisible();
				projetUI.editerJTextField(true);
				
			}else if(commande.equals("ouvrirProjet")){				
				ouvrirProjetUI.setVisible();
				
			}
			else if(commande.equals("quitterProjet")){
				
				dataBaseManagement.shutdown();
				System.exit(0);
			}					
		}				
	}
	
	class EnregistrerEtatEnDBB extends SwingWorker<Integer, Void>{

		@Override
		protected Integer doInBackground() throws Exception {
			// TODO Auto-generated method stub			
			etatManager.save(etat1);
			etatManager.save(etat2);
			
			return null;
		}				
	}
}
