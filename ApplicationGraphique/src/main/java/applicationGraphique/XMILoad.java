
package applicationGraphique;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLPackage;

import com.imcfr.generateur.emf.xmimodel.helpers.RessourceSetHelper;
import com.imcfr.generateur.xmi.exceptions.ResourceSetLoadingException;

public class XMILoad {
	/**
	 * This method loadFile takes as input a XMI file to load
	 * 
	 * 
	 * 
	 * **/
	public Model  loadFile(File xmiFile) 
	//public static void loadFile(String xmiFile) 
			throws ResourceSetLoadingException, IOException, FileNotFoundException {
		if(!xmiFile.exists()){
			throw new FileNotFoundException("Le fichier "+xmiFile.getAbsolutePath()+" n'existe pas");
		}
		URI xmiFileURI= URI.createFileURI(xmiFile.getAbsolutePath());
		ResourceSet ressourceSet= RessourceSetHelper.getResourceSet();
		Resource r=ressourceSet.createResource(xmiFileURI);
		r.load(null);
		Model emfModel=(Model) EcoreUtil.getObjectByType(r.getContents(), UMLPackage.Literals.MODEL);
		if(emfModel==null){
			throw new ResourceSetLoadingException("Le model n'a pas pu être chargé");
		}
		return emfModel;
	}
	

}