package br.com.vivere.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class PropertiesReader {
	
	public static String getValor(String chave) throws IOException {
		
		InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream("hbm-changes.properties");
	      
		Properties prop = new Properties();
		prop.load(inputStream);
		
		return prop.getProperty(chave);
		
	}
	
}
