package applicationGraphique;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Package;

import com.imcfr.generateur.xmi.exceptions.ResourceSetLoadingException;

public class LoadFile {
  //String path="examples/ldp.xmi";
  public static void loadFile(String path)throws ResourceSetLoadingException, FileNotFoundException, IOException {
	File file = new File(path); 
	XMILoad x= new XMILoad();
	Model model= x.loadFile(file);
	Package pack= (Package)model;
		EList<PackageableElement>liste= pack.getPackagedElements();
	if(liste!=null){
		for (PackageableElement packageableElement : liste) {    
			if (packageableElement instanceof org.eclipse.uml2.uml.Package){
				Package pkg = (Package) packageableElement;
				PkgLoad.recParsPkg(pkg);
			}
			else 
				PkgLoad.parsCls(packageableElement);
		 }
	 }
	}

}
