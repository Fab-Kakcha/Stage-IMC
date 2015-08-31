package crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFileChooser;

import org.apache.log4j.Logger;

public class AgadirGenController {

	private static Logger logger = Logger.getLogger(AgadirGenController.class);

	private AgadirGenerateurUI agadirGenerateurUI = null;	
	private File file;

	
	public AgadirGenController(AgadirGenerateurUI agadirGenerateurUI){
		
		this.agadirGenerateurUI = agadirGenerateurUI;
		
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
		agadirGenerateurUI.addJButton13Listener(new ChoisirUnFichierAction());
		
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
				
				//Enregistrer le chemin "filePath" dans la base de données.
				
			}else if(returnVal == JFileChooser.CANCEL_OPTION){
				
				
			}else if(returnVal == JFileChooser.ERROR_OPTION){
				
				
			}
			
		}				
	}
}

