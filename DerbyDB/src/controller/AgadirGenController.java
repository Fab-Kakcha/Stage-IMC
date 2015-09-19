package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingWorker;

import main.CustomException;
import main.DataBaseManagement;
import manager.EtatManager;
import manager.ModelesManager;
import manager.ParametreManager;
import manager.ProjetManager;
import model.Modeles;

import org.apache.log4j.Logger;

import view.AgadirGenerateurUI;

public class AgadirGenController{

	private static Logger logger = Logger.getLogger(AgadirGenController.class);
	
	private final static int nbreDeTextField = 12;
	private AgadirGenerateurUI agadirGenerateurUI = null;	
	private File file;
	private DataBaseManagement dataBaseManagement;
	
	ParametreManager paraManager;
	EtatManager etatManager;	
	ModelesManager modelesManager;
	ProjetManager projetManager;
	
	public AgadirGenController(AgadirGenerateurUI agadirGenerateurUI, DataBaseManagement dataBaseManagement){
		
		this.agadirGenerateurUI = agadirGenerateurUI;
		this.dataBaseManagement = dataBaseManagement;
		
		//SuppressionTableBDD suppressionTables = new SuppressionTableBDD();
		//suppressionTables.execute();
		//CreationTableBDD creationTables = new CreationTableBDD();
		//creationTables.execute();
		
		//Boutons "choisir un fichier"
		agadirGenerateurUI.addJButton1Listener(new ChoisirUnFichierAction());
		agadirGenerateurUI.addJButton2Listener(new ChoisirUnFichierAction());
		agadirGenerateurUI.addJButton3Listener(new ChoisirUnFichierAction());
		agadirGenerateurUI.addJButton4Listener(new ChoisirUnFichierAction());
		agadirGenerateurUI.addJButton5Listener(new ChoisirUnFichierAction());
		agadirGenerateurUI.addJButton6Listener(new ChoisirUnFichierAction());
		agadirGenerateurUI.addJButton7Listener(new ChoisirUnFichierAction());
		agadirGenerateurUI.addJButton8Listener(new ChoisirUnFichierAction());
		agadirGenerateurUI.addJButton9Listener(new ChoisirUnFichierAction());
		agadirGenerateurUI.addJButton10Listener(new ChoisirUnFichierAction());
		agadirGenerateurUI.addJButton11Listener(new ChoisirUnFichierAction());
		agadirGenerateurUI.addJButton12Listener(new ChoisirUnFichierAction());
		
		//Bouton "enregistrer"
		agadirGenerateurUI.addJButton13Listener(new EnregistrerAction());
		
		//Boutton "Annuler"
		agadirGenerateurUI.addJButton14Listener(new AnnulerAction());
		
		//Declaration des Managers
		paraManager = new ParametreManager(dataBaseManagement.getConnection());			
		etatManager = new EtatManager(dataBaseManagement.getConnection());
		modelesManager = new ModelesManager(dataBaseManagement.getConnection());		
		projetManager = new ProjetManager(dataBaseManagement.getConnection());	
	}
			
	
	class ChoisirUnFichierAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
			String actionCommande = e.getActionCommand();					
			JFileChooser fc =  agadirGenerateurUI.getFileChooser();
			int returnVal = fc.showOpenDialog(fc);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
													
				file = fc.getSelectedFile();
				String filePath = file.getPath();
				agadirGenerateurUI.setTextField(actionCommande, filePath);
					
			}else if(returnVal == JFileChooser.CANCEL_OPTION){
								
			}else if(returnVal == JFileChooser.ERROR_OPTION){
							
			}			
		}				
	}
	
	class EnregistrerAction implements ActionListener{

		//private DataBaseManagement dataBaseManagement;		
		//public EnregistrerAction(DataBaseManagement dataBaseManagement){		
			//this.dataBaseManagement = dataBaseManagement;
		//}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		
			//agadirGenerateurUI
			boolean textFieldFilled = agadirGenerateurUI.verificationTextField();
			
			if(textFieldFilled){
				
				int n = agadirGenerateurUI.yesOrNoDialogBox("Confirmez vous l'enregistrement ?");				
				if(n == 0){				
					TacheEnregBDD tacheEnreg = new TacheEnregBDD();
					tacheEnreg.execute();
					
				}else if(n == 1)
					agadirGenerateurUI.infoDialogBox("Vous avez annulé l'enregistrement");
				
			}else{
				
				agadirGenerateurUI.erreurDialogBox("Tous les champs sont obligatoires");
			}						
		}		
	}
	
	class AnnulerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			for(int i = 1; i <= nbreDeTextField; i++){
				agadirGenerateurUI.setTextField("jButton" + i, null);
			}
		}				
	}
	
	class TacheEnregBDD extends SwingWorker<Integer, String>{

		//private DataBaseManagement dataBaseManagement;		
		//public TacheEnregBDD(DataBaseManagement dataBaseManagement){			
			//this.dataBaseManagement = dataBaseManagement;
		//}
		
		@Override
		protected Integer doInBackground() throws Exception {
			// TODO Auto-generated method stub
			
			try {
				
				for(int i = 1; i <= nbreDeTextField; i++){
					
					modelesManager.save(new Modeles(String.valueOf(i), agadirGenerateurUI.getTextField("jButton" + i)));
				}				
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				CustomException.errorPrint(e);
				if(CustomException.getSQLState(e).equals("23505")){
					
					//this.cancel(true);
					return 1;
				}else if(CustomException.getSQLState(e).equals("42X05")){
					
					return 2;
				}
			}						
			return 0;
		}
		
		protected void done(){
			
			if(this.isCancelled()){			
								
			}			
			else{
				
				try {
					int value = get();					
					if(value == 1){
						
						logger.info("Les données ont déjà été enregistrées");
						agadirGenerateurUI.infoDialogBox("Les données ont déjà été enregistrées");
					}else if(value == 2){
						
						logger.error("Les Tables n'existent pas en BDD");
						agadirGenerateurUI.erreurDialogBox("L'enregistrement des données a échoué");
					}else{
						
						logger.info("Enregistrement en BDD est terminé");
						agadirGenerateurUI.infoDialogBox("L'enregistrement en BDD est terminé");
						dataBaseManagement.shutdown();
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}								
			}
		}
	}
	
	class CreationTableBDD extends SwingWorker<Integer, String> {
		
		@Override
		protected Integer doInBackground() throws Exception {
			// TODO Auto-generated method stub
			
			try{
				dataBaseManagement.createTables();			
			}catch(Throwable e){	
				
				this.cancel(true);
				CustomException.errorPrint(e);
			}			
			return null;
		}
		
		protected void done(){
			
			if(this.isCancelled())
				logger.error("Failed creating all Tables");
			else
				logger.info("Done creating all Tables");
		}		
	}
	
	class SuppressionTableBDD extends SwingWorker<Integer, Void> {
		
		@Override
		protected Integer doInBackground() throws Exception {
			// TODO Auto-generated method stub
			try{
				
				dataBaseManagement.dropAllTables();		
			} catch(Throwable e){
				
				this.cancel(true);
				CustomException.errorPrint(e);
			}
			
			return null;
		}
		
		protected void done(){
			
			if(this.isCancelled())
				logger.error("Failed dropping all Tables");
			else
				logger.info("Done dropping all Tables");
		}		
	}
}
