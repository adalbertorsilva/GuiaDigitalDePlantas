package br.org.itv.guia.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class GeneralManager {
	
	private final String CONFIG_PROPERTIES = "config.properties";
	protected Properties properties;
	
	protected void loadProperties(){
		try {
			properties = new Properties();
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream input = classloader.getResourceAsStream(CONFIG_PROPERTIES);
			properties.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
