package applicationGraphique;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Stereotype;
/**this class pkgLoad Package Load contains four methods to treat a XMI file.
 * - First method oOfStr object of stereotype is  to verify if an object is from a given stereotype
 * - Second method method getType is to give the type of a given element its output (Package, Class, Association OR Unknown Type).
 * - Third method recParsPkg is a recursive method to parse a package and its sub elements and it show each package or class is from 
 * 		which package or class.
 * - Fourth method parsCls is a method to parse the classes that don't belong to any package
 * - Fifth pkgElemOfStr Package Element of stereotype is a method that show the package element
 *   from a given stereotype. It calls the method that show the classes from a given stereotype.
 * - sixth method objElemOfStr object element of a stereotype, this method returns the object element which are nested in a package or 
 *   which are a basic classes with their stereoTypes.
 *   @author naboud
 *
**/
public class PkgLoad {
	
	public static	ArrayList<String> steroTypes = new ArrayList<String>(){
		{add("Configuration::Project"); //pkg
		add("Profile::Tag3");//pkg
		add("Profile::Tag1"); //st for class
		add("Profile::Tag2");//st for class
		add("Configuration::TagConfg");	//st for class
		add("LocalProfile::IMC");
		}
	};
	/**
	 * This method verify if a stereotype is  applied on a Model'element
	 *  @param   the object that we want to know its stereotype
	 * @param  list of string  which is all the qualified names of the stereotypes
	 * it must constructed like this Profile:: stereotype
	 * 
	 * **/
	static public void oOfStr (EObject eobject, List<String> str) {
		for (int i=0; i <str.size(); i++){
			//System.out.println((char)31 +"[1m testing bold");
			Stereotype ster= ((Element) eobject).getAppliedStereotype(str.get(i));	
			if (ster!=null)
				if (eobject instanceof org.eclipse.uml2.uml.Package)	
					System.out.println("\n**Stéréotype** "+ster.getQualifiedName()+ " est appliqué sur la package "+ ((NamedElement) eobject).getName()); 
				else if (eobject instanceof org.eclipse.uml2.uml.Class)	
					System.out.println("\n**Stéréotype** "+ster.getQualifiedName()+ " est appliqué sur la class "+ ((NamedElement) eobject).getName()); 
		}
	}
	
	/**This method returns the type a packageable Element **/	
	static public String getType(EObject x){
		if (x instanceof org.eclipse.uml2.uml.Package)
			return "Package";
		else if  (x instanceof org.eclipse.uml2.uml.Class)
			return "Class";
		else if (x instanceof org.eclipse.uml2.uml.Association)
		return "Association";
		else 
			return "Unknowned Type";
	}
	
	
	/**
	 * The methode recParsPkg takes a package and return messages 
	 * that show the containment of this package
	 * The input of this method must be UML package , 
	 * @param  Package 
	 * @return   messages that parse the elements in a package
	 * 
	 */
	static public void recParsPkg (Package pkg) { 
		System.out.println("Package "+pkg.getName());
		oOfStr(pkg,steroTypes);
		TreeIterator<EObject> treeit =pkg.eAllContents();
		while (treeit.hasNext()){        
			EObject eobject = (EObject) treeit.next(); 
			if (eobject instanceof org.eclipse.uml2.uml.Package){ 
				Package pckg= (org.eclipse.uml2.uml.Package)eobject;
				System.out.println("Package : "+pckg.getName());
				oOfStr(eobject,PkgLoad.steroTypes);
				//recParsPkg((org.eclipse.uml2.uml.Package)eobject);
			}
			else 
				if(eobject instanceof org.eclipse.uml2.uml.Class){ 
					Class cls=(Class) eobject;
					System.out.print("\nClass : "+cls.getName()); 
					oOfStr(cls,PkgLoad.steroTypes);
				}
		}
	}
	
	/** This method is applied when the packaged element are from class type 
	* if only class**/
	static public void parsCls (EObject cls){ 
		if  (cls instanceof org.eclipse.uml2.uml.Class){ 
			//Class cls=(Class) eobject;
			System.out.print("\nClass : ***"+((NamedElement) cls).getName()); 
			oOfStr(cls,steroTypes);
		}
	}
	
	
/**This method shows the root elements that are from a given stereotype 
 * @param 
**/
	
	static public void pkgElemOfStr (Package m, String str) {
		  EList<PackageableElement>liste= m.getPackagedElements();
		   if(liste!=null){
			   for (PackageableElement packageableElement : liste) {    
				   if (packageableElement instanceof org.eclipse.uml2.uml.Package){
					   Stereotype ster= packageableElement.getAppliedStereotype(str);	
					   if (ster!=null)
						System.out.println("\n"+packageableElement.getName()+ " is "+getType(packageableElement)+" from the model box "+packageableElement.getQualifiedName()+" is from the sterotype "+str );
					     }	  
				   else if (packageableElement instanceof org.eclipse.uml2.uml.Class)
				   				   objElemOfStr(packageableElement,str);
			   }
		   }
   }
		
	/**This method shows the  elements that are from a given stereotype 
	 * @param 
	**/
	
	static public void objElemOfStr (EObject m, String str){ 
		if (m instanceof org.eclipse.uml2.uml.Package){
			Package package1=((org.eclipse.uml2.uml.Package)m);
			Stereotype ster= package1.getAppliedStereotype(str);
			if (ster!=null)
				System.out.println("\n"+package1.getName()+ " is "+getType(package1)+" from the model box "+package1.getQualifiedName()+" is from the sterotype "+str );	
		}	  
		else 
			if (m instanceof org.eclipse.uml2.uml.Class){
				NamedElement element=((NamedElement) m);
				Stereotype ster= element.getAppliedStereotype(str);	
				if (ster!=null)
					System.out.println("\n"+element.getName()+ " is "+getType(element)+" from the model box "+element.getQualifiedName()+" is from the sterotype "+str );
			}
	}

	
	
}//class