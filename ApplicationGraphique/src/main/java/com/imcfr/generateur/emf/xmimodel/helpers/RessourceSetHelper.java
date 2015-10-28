package com.imcfr.generateur.emf.xmimodel.helpers;

import java.net.URL;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UML22UMLExtendedMetaData;
import org.eclipse.uml2.uml.resource.UML22UMLResource;
import org.eclipse.uml2.uml.resource.UMLResource;

import com.imcfr.commun.Log;
import com.imcfr.generateur.emf.xmimodel.constants.EclipseUML2Constantes;
import com.imcfr.generateur.emf.xmimodel.constants.UMLConstantes;
import com.imcfr.generateur.emf.xmimodel.constants.XMIConstantes;
import com.imcfr.generateur.xmi.exceptions.ResourceSetLoadingException;

/**
 * @author hly
 * Helper permettant de facilement récupérer le ResourceSet nécessaire pour le chargement des fichiers XMI
 *
 */
public class RessourceSetHelper {
	
	
	/**
	 * Permet d'obtenir un RessourceSet nécessaie pour la lecture du fichier XMI
	 * @return
	 */
	public static ResourceSet getResourceSet() throws ResourceSetLoadingException{
		ResourceSet set = new ResourceSetImpl();
		initExtensionFactoryMap();
		initPackageRegistry(set);
		initURIMap(set);
		setUML2_0Compatibility(set);
		setUML2_1Compatibility(set);
		setUML2_2Compatibility(set);
		setUML2_3Compatibility(set);
		loadDefaultOptions(set);
		return set;
	}
	
	/**
	 * Intialise le moteur de gestion de ressource pour qu'il prenne en compte les extension .xmi et .uml
	 */
	private static void initExtensionFactoryMap() {
		Log.get(RessourceSetHelper.class).logDebug("Initialisation de l'ExtensionFactory pour prendre en compte les extensions .xmi et .uml");
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());
		m.put("uml2", UML22UMLResource.Factory.INSTANCE);
		Log.get(RessourceSetHelper.class).logDebug("Initialisation de l'ExtensionFactory OK");
	}
	
	/**
	 * Configure la compatibilité avec UML 2.0
	 * @param set
	 */
	private static void setUML2_0Compatibility(ResourceSet set){
		Map<URI, URI> uriMap = set.getURIConverter().getURIMap();
		Log.get(RessourceSetHelper.class).logDebug("****** Ajout de la compatibilité UML 2.0 ******");
		
		
		//Map from UML/2.0 etc to UML later versions.
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace UML 2.0");
		Registry packageRegistry = set.getPackageRegistry();
		packageRegistry.put(UMLConstantes.UML_2_0_URI, UMLPackage.eINSTANCE);
		uriMap.put(URI.createURI(UMLConstantes.UML_2_0_URI), URI.createURI(UMLPackage.eNS_URI));
		
		//Map from UML/2.0 etc to UML later versions.
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace des types primitifs UML 2.0");
		uriMap.put(URI.createURI(UMLConstantes.UML_2_0_PRIMITIVE_TYPES_URI), URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
		
		Log.get(RessourceSetHelper.class).logDebug("************************************************");
	}
	/**
	 * Configure la compatibilité avec UML 2.1.0 et UML 2.1.1
	 * @param set
	 */
	private static void setUML2_1Compatibility(ResourceSet set){
		Map<URI, URI> uriMap = set.getURIConverter().getURIMap();
		Log.get(RessourceSetHelper.class).logDebug("****** Ajout de la compatibilité UML 2.1 ******");
		//Map from UML/2.0 etc to UML later versions.
		Registry packageRegistry = set.getPackageRegistry();
		packageRegistry.put(UMLConstantes.UML_2_1_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(UMLConstantes.UML_2_1_1_URI, UMLPackage.eINSTANCE);

		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace UML 2.1.0");
		uriMap.put(URI.createURI(UMLConstantes.UML_2_1_URI), URI.createURI(UMLPackage.eNS_URI));
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace UML 2.1.1");
		uriMap.put(URI.createURI(UMLConstantes.UML_2_1_1_URI), URI.createURI(UMLPackage.eNS_URI));
		
		//Map from UML/2.0 etc to UML later versions.
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace des types primitifs UML 2.1.0");
		uriMap.put(URI.createURI(UMLConstantes.UML_2_1_PRIMITIVE_TYPES_URI), URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace des types primitifs UML 2.1.1");
		uriMap.put(URI.createURI(UMLConstantes.UML_2_1_1_PRIMITIVE_TYPES_URI), URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
		
		Log.get(RessourceSetHelper.class).logDebug("************************************************");
	}
	/**
	 * Configure la compatibilité avec UML 2.2
	 * @param set
	 */
	private static void setUML2_2Compatibility(ResourceSet set){
		Map<URI, URI> uriMap = set.getURIConverter().getURIMap();
		Log.get(RessourceSetHelper.class).logDebug("****** Ajout de la compatibilité UML 2.1 ******");
		//Map from UML/2.0 etc to UML later versions.
		Registry packageRegistry = set.getPackageRegistry();
		packageRegistry.put(UMLConstantes.UML_2_2_URI, UMLPackage.eINSTANCE);
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace UML 2.2");
		uriMap.put(URI.createURI(UMLConstantes.UML_2_2_URI), URI.createURI(UMLPackage.eNS_URI));
		
		//Map from UML/2.0 etc to UML later versions.
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace des types primitifs UML 2.2");
		uriMap.put(URI.createURI(UMLConstantes.UML_2_2_PRIMITIVE_TYPES_URI), URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
		
		Log.get(RessourceSetHelper.class).logDebug("************************************************");
	}
	/**
	 * Configure la compatibilité avec UML 2.3
	 * @param set
	 */
	private static void setUML2_3Compatibility(ResourceSet set){
		Map<URI, URI> uriMap = set.getURIConverter().getURIMap();
		Log.get(RessourceSetHelper.class).logDebug("****** Ajout de la compatibilité UML 2.3 ******");
		//Map from UML/2.0 etc to UML later versions.
		Registry packageRegistry = set.getPackageRegistry();
		packageRegistry.put(UMLConstantes.UML_2_3_URI, UMLPackage.eINSTANCE);
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace UML 2.3");
		uriMap.put(URI.createURI(UMLConstantes.UML_2_3_URI), URI.createURI(UMLPackage.eNS_URI));
		
		//Map from UML/2.0 etc to UML later versions.
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace des types primitifs UML 2.3");
		uriMap.put(URI.createURI(UMLConstantes.UML_2_3_PRIMITIVE_TYPES_URI), URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
		
		Log.get(RessourceSetHelper.class).logDebug("************************************************");
	}
	/**
	 * FIXME La compatibilité avec 2.4 n'est pas encore prise en compte
	 * Configure la compatibilité avec UML 2.4
	 * @param set
	 */
	private static void setUML2_4Compatibility(ResourceSet set){
		Map<URI, URI> uriMap = set.getURIConverter().getURIMap();
		Log.get(RessourceSetHelper.class).logDebug("****** Ajout de la compatibilité UML 2.4 ******");
		//Map from UML/2.0 etc to UML later versions.
		Registry packageRegistry = set.getPackageRegistry();
		packageRegistry.put(UMLConstantes.UML_2_4_URI, UMLPackage.eINSTANCE);
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace UML 2.4");
		uriMap.put(URI.createURI(UMLConstantes.UML_2_4_URI), URI.createURI(UMLPackage.eNS_URI));
		
		//Map from UML/2.0 etc to UML later versions.
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement du namespace des types primitifs UML 2.4");
		uriMap.put(URI.createURI(UMLConstantes.UML_2_4_PRIMITIVE_TYPES_URI), URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
		
		Log.get(RessourceSetHelper.class).logDebug("************************************************");
	}

	/**
	 * Enregistre les namespace pour les compatibilités de version EclipseUML2 et XMI
	 * // @see http://dev.eclipse.org/newslists/news.eclipse.tools.uml2/msg92.html
	 * @param set
	 */
	private static void initPackageRegistry(ResourceSet set) {
		Registry packageRegistry = set.getPackageRegistry();
		packageRegistry.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		// UMLPackage.eNS_URI=http://www.eclipse.org/uml2/2.1.0/UML
		// This gives a ConnectException when loading the model unless 2.0.0
		// namespace is also registered
		packageRegistry.put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement des namespaces Eclipse UML2 pour la compatibilité de version XMI");
		packageRegistry.put(EclipseUML2Constantes.UML2_1_0_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(EclipseUML2Constantes.UML2_2_0_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(EclipseUML2Constantes.UML2_2_1_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(EclipseUML2Constantes.UML2_2_2_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(EclipseUML2Constantes.UML2_2_3_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(EclipseUML2Constantes.UML2_3_0_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(EclipseUML2Constantes.UML2_3_1_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(Ecore2XMLPackage.eNS_URI, Ecore2XMLPackage.eINSTANCE);
		packageRegistry.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		
		Log.get(RessourceSetHelper.class).logDebug("Enregistrement des namespaces XMI pour la compatibilité de version XMI");
		packageRegistry.put(XMIConstantes.XMI_2_0_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(XMIConstantes.XMI_2_0_1_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(XMIConstantes.XMI_2_1_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(XMIConstantes.XMI_2_1_1_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(XMIConstantes.XMI_2_1_2_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(XMIConstantes.XMI_2_2_URI, UMLPackage.eINSTANCE);
		packageRegistry.put(XMIConstantes.XMI_2_3_URI, UMLPackage.eINSTANCE);
	}


	/**
	 * Met en place la mapping initial pour les librairies, métamodèles et profiles EclipseUML2
	 * @param set
	 */
	private static void initURIMap(ResourceSet set) throws  ResourceSetLoadingException{
		URL url = ResourceSet.class.getResource("/libraries/UMLPrimitiveTypes.library.uml");
		Map<URI, URI> uriMap = set.getURIConverter().getURIMap();
		if (url != null) {
			// Need to create a pathmap location map for UML2 Resources, to load
			// standard profiles.
			String path = url.getPath().substring(0, url.getPath().indexOf("libraries"));
			URI uri = URI.createURI("jar:" + path);
			URIConverter.URI_MAP.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uri.appendSegment("libraries").appendSegment(""));
			URIConverter.URI_MAP.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uri.appendSegment("metamodels").appendSegment(""));
			URIConverter.URI_MAP.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uri.appendSegment("profiles").appendSegment(""));
		} else {
			throw new ResourceSetLoadingException("Could not load UML2 org.eclipse.uml2.resources jar from classpath");
		}
		// Local implementation which delegates to the global map, so
		// registrations are local
		uriMap.putAll(UML22UMLExtendedMetaData.getURIMap());
	}

	private static void loadDefaultOptions(ResourceSet set) {
		// - populate the load options
		Map<Object, Object> loadOptions = set.getLoadOptions();
		// Enable notifications during load. Profiles not found do not generate
		// a notification
		loadOptions.put(XMLResource.OPTION_DISABLE_NOTIFY, Boolean.FALSE);
		loadOptions.put(XMLResource.OPTION_DOM_USE_NAMESPACES_IN_SCOPE, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.FALSE);
		loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_PROCESS_DANGLING_HREF_RECORD, Boolean.TRUE);
	}

}
