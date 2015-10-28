package applicationGraphique;

import java.io.File;

import org.apache.log4j.Logger;

import exception.CustomException;

/**
 * Cette classe est responsable de la création d'un dossier lors la création d'un Projet, ainsi que de la suppression de ce même
 * dossier lorsque le Projet est supprimé.
 * @author fkakcha
 *
 */

public class Utils {

	private static Logger logger = Logger.getLogger(Main.class);
	private static final String RACINE_DOSSIER = "projets"+File.separator;
	
	/**
	 * Permet de créer un dossier lors de la création d'un Projet
	 * @param nomDuDossier nom du dossier à créer
	 * @return le chemin absolu du dossier crée
	 * @throws CustomException 
	 */
	public static String creationDeDossier(String nomDuDossier){
		
		if(nomDuDossier == null)
			throw new IllegalArgumentException("Argument is null");
		
		String cheminDuDossier = null;
		File dossier = new File(RACINE_DOSSIER + nomDuDossier);
		boolean bool = dossier.mkdir();
		
		if(bool){
		
			cheminDuDossier = dossier.getAbsolutePath();
			logger.info("Dossier crée: " + cheminDuDossier);
		}
		else{
			
			//throw new CustomException("Impossible de créer le dossier: " + dossier.getAbsolutePath());
			//logger.error("Impossible de créer le dossier: " + dossier.getAbsolutePath());
		}			
		return cheminDuDossier;
	}
	
	/**
	 * Supprime un dossier lors de la suppression d'un Projet
	 * @param nomDuDossier nom du dossier à supprimer
	 * @throws CustomException 
	 */
	public static void suppressionDeDossier(String nomDuDossier) throws CustomException{
		
		if(nomDuDossier == null)
			throw new IllegalArgumentException("Argument is null");
		
		File dossier = new File(RACINE_DOSSIER + nomDuDossier);
		if(dossier.exists()){
			
			if(dossier.isDirectory()){
				
				if(dossier.list().length == 0){
					
					dossier.delete();
					logger.info("Dossier supprimé: " + dossier.getAbsolutePath());
					
				}else{
					
					File file;
					String[] files = dossier.list();
					for(String s : files){
						
						file = new File(dossier, s);
						file.delete();
					}
					
					if(dossier.list().length == 0){
						
						dossier.delete();
						logger.info("Dossier supprimé: " + dossier.getAbsolutePath());
					}
				}					
			}else
				throw new CustomException(dossier.getAbsolutePath() + " est un fichier");
		}			
		else
			throw new CustomException("Le dossier " + dossier.getAbsolutePath() + " n'existe pas");			
	}
}
