package br.org.itv.guia.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import br.org.itv.guia.constants.SpecieFieldsEnum;

public abstract class SpecieBuilder {
	
	protected XWPFDocument docxDocument;
	protected File file;
	
	public static SpecieBuilder getBuilder(File file, Field field){
		switch (SpecieFieldsEnum.valueOf(field.getName().toUpperCase())) {
		case SCIENTIFIC_NAME:
			return new ScientificNameBuilder(file);
		case DESC:
			return new DescriptionBuilder(file);
		case FLOWERING:
			return new FloweringBuilder(file);
		case HABITAT:
			return new HabitatBuilder(file);
		case POPULAR_NAME:
			return new PopularNameBuilder(file);
		case PICTURES:
			return new PicturesBuilder(file);
		case SAMPLE:
			return new SampleBuilder(file);
		case COORDINATES:
			return new CoordinatesBuilder(file);
		default:
			return new NullBuilder(field);
		}
	}    
	
	public abstract Object getValue();
	
	protected String getTextFromTopicHeader(String header){
		
		Optional<String> text = docxDocument.getParagraphs().stream()
				   							.map(paragraph -> paragraph.getText())
				   							.filter(paragraph -> paragraph.contains(header))
				   							.findFirst();
		
		
		return text.isPresent() ? convertHyphenToTwoPoints(text.get()) : "";
	}
	
	protected String convertHyphenToTwoPoints(String text){
		return text.replace("\u2013", ":");
	}
	
	protected List<Integer> getPatternIndexes(String text, String pattern){
		List<Integer> pointIndexes = new ArrayList<>();
		
		for(int index = text.indexOf(pattern); index > 0; index = text.indexOf(pattern, index + 1)){
			pointIndexes.add(new Integer(index));
		}
		
		return pointIndexes;
	}
	
	protected void loadDocxFile(File file){
		try {
			this.docxDocument = new XWPFDocument(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
