package com.imcfr.test.fichiers.exemple;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

public class ClassPathTester {

	public ClassPathTester() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testLoadFichierClassPath(){
		try {
			URL url= ClassPathTester.class.getResource("/exemple/fichier/dans/class/path/exemple.properties");
			File fichier= new File(url.getPath());
			System.out.println(fichier.getPath());
			
			Properties props= new Properties();
			props.load(ClassPathTester.class.getResourceAsStream("/exemple/fichier/dans/class/path/exemple.properties") );
			System.out.println(props.getProperty("message"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

}
