package br.com.vivere.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class PropertiesReader {
	
	public static String getValor(String chave) {
		
		try {
			InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream("hbm-changes.properties");
			  
			Properties prop = new Properties();
			prop.load(inputStream);
			
			return prop.getProperty(chave);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static String getValor(String ...chaves) {
		
		StringBuilder sb = new StringBuilder();
		
		try {
			InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream("hbm-changes.properties");
			  
			Properties prop = new Properties();
			prop.load(inputStream);
			
			for (String chave : chaves) {
				sb.append(prop.getProperty(chave));
				sb.append(",");
			}
			
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static Properties getFuncionalProp(String parametro) {
		Properties prop = new Properties();
		
		try {
			InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream(parametro);
			prop.load(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
}
