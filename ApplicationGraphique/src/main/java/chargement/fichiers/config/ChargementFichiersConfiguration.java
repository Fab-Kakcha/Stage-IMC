package chargement.fichiers.config;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;

import manager.LibrairieManager;
import manager.ModelBoxManager;
import manager.ProfileManager;
import model.*;

/**
 * Cette classe sauvegarde en BDD les chemins par défaut vers les fichiers de configuration qui sont 
 * dans des fichiers de properties
 * 
 * @author fkakcha
 *
 */
public class ChargementFichiersConfiguration {

	
	public void chargementFichiersProfile(ProfileManager profileManager) throws IOException, SQLException{
		
		File file;
		URL url;
		String pathXMLFile;
		
		Properties props = new Properties();
		props.load(ChargementFichiersConfiguration.class.getResourceAsStream("/properties/Profiles/profiles.properties"));
		
		pathXMLFile = props.getProperty("Contact");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		profileManager.save(new Profile("Contact", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("Mesure");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		profileManager.save(new Profile("Mesure", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("Role");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());	
		profileManager.save(new Profile("Role", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("Base");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		profileManager.save(new Profile("Base", file.getAbsolutePath()));
	}

	
	public void chargementFichiersModelBox(ModelBoxManager modelBoxManager) throws IOException, SQLException{
		
		File file;
		URL url;
		String pathXMLFile;
		
		Properties props = new Properties();
		props.load(ChargementFichiersConfiguration.class.getResourceAsStream("/properties/ModelBox/modelbox.properties"));
		
		pathXMLFile = props.getProperty("AgadirTechnique");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		modelBoxManager.save(new ModelBox("AgadirTechnique", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("AgadirReferentielGeographique");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		modelBoxManager.save(new ModelBox("AgadirReferentielGeographique", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("AgadirReferentielContact");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		modelBoxManager.save(new ModelBox("AgadirReferentielContact", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("AgadirReferentielFormation");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		modelBoxManager.save(new ModelBox("AgadirReferentielFormation", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("AgadirContact");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		modelBoxManager.save(new ModelBox("AgadirContact", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("AgadirRole");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		modelBoxManager.save(new ModelBox("AgadirRole", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("AgadirMesure");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		modelBoxManager.save(new ModelBox("AgadirMesure", file.getAbsolutePath()));	
		
		pathXMLFile = props.getProperty("ImcSecurite");
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		modelBoxManager.save(new ModelBox("ImcSecurite", file.getAbsolutePath()));
					
	}
	
	public void chargementFichiersLibrairie(LibrairieManager librairieManager) throws IOException, SQLException{
		
		File file;
		URL url;
		String pathXMLFile;
		
		Properties props = new Properties();
		props.load(ChargementFichiersConfiguration.class.getResourceAsStream("/properties/Librairies/librairies.properties"));
		
		pathXMLFile = props.getProperty("Commun");		
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		librairieManager.save(new Librairie("Commun", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("CouchePersistanceJPA");		
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		librairieManager.save(new Librairie("CouchePersistanceJPA", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("CoucheService");		
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		librairieManager.save(new Librairie("CoucheService", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("BddPostgres");		
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		librairieManager.save(new Librairie("BddPostgres", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("CouchePresentation");		
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		librairieManager.save(new Librairie("CouchePresentation", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("XSD");		
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		librairieManager.save(new Librairie("XSD", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("ModelGeneration");		
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		librairieManager.save(new Librairie("ModelGeneration", file.getAbsolutePath()));
		
		pathXMLFile = props.getProperty("IMCDev");		
		url = ChargementFichiersConfiguration.class.getResource(pathXMLFile);
		file = new File(url.getPath());
		librairieManager.save(new Librairie("IMCDev", file.getAbsolutePath()));		

	}
}
