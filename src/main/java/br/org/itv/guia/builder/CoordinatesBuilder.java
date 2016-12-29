package br.org.itv.guia.builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.org.itv.guia.constants.HeadersEnum;
import br.org.itv.guia.constants.StatesEnum;
import br.org.itv.guia.model.Coordinate;

public class CoordinatesBuilder extends SpecieBuilder {
	
	private String baseText;
	private final String CONFIG_PROPERTIES = "config.properties";
	private Properties properties;
	
	public CoordinatesBuilder(File file) {
		loadDocxFile(file);
		loadProperties();
	}

	@Override
	public List<Coordinate> getValue() {
		
		baseText = getTextFromTopicHeader(HeadersEnum.GEORGRAPHIC_DISTRIBUTION.getHeader()).trim();
		
		return Stream.of(StatesEnum.values())
					 .filter(this::textContainsStateName)
					 .map(this::createCoordinate)
					 .collect(Collectors.toList());
	}
	
	private boolean textContainsStateName(StatesEnum state){
		return baseText.contains(state.getStateName());
	}
	
	private Coordinate createCoordinate(StatesEnum state){
		return new Coordinate(properties.getProperty(state.getKey()), state.getStateName());
	}
	
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
