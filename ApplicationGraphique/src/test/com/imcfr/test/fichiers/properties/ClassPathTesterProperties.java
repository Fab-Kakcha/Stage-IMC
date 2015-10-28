package com.imcfr.test.fichiers.properties;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import junit.framework.TestCase;

public class ClassPathTesterProperties extends TestCase {

	
	public void testPropertiesClassPath(){
		
		try {
			URL url = ClassPathTesterProperties.class.getResource("/properties/librairies/librairies.properties");
			File file = new File(url.getPath());

			System.out.println(file.getAbsolutePath());

			Properties props = new Properties();
			props.load(ClassPathTesterProperties.class.getResourceAsStream("/properties/librairies/librairies.properties"));
			
			String pathXMLFile = props.getProperty("BddPostgres");
			System.out.println(pathXMLFile);
			
			url = ClassPathTesterProperties.class.getResource(pathXMLFile);
			file = new File(url.getPath());
			
			System.out.println(file.getAbsolutePath());	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
