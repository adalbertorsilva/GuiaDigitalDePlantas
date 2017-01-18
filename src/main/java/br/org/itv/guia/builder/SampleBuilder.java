package br.org.itv.guia.builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.stream.Stream;

import br.org.itv.guia.model.Picture;
import br.org.itv.guia.utils.Utils;

public class SampleBuilder extends SpecieBuilder {

	
	public SampleBuilder(File file) {
		this.file = file;
	}
	
	@Override
	public Object getValue() {
		return createPicture();
	}
	
	private Picture createPicture(){
		
		File sampleImageFile = getSampleImageFile(file.getParentFile());
		
		if(Utils.isNull(sampleImageFile)){
			return null;
		}
		
		Picture picture = new Picture();
		picture.setPictureName(sampleImageFile.getName());
		picture.setEncondedImage(convertSampleFileToString64(sampleImageFile));
		
		return picture;
	}
	
	private File getSampleImageFile(File file){
		
		return Stream.of(file.listFiles())
			  .filter(this::isSampleFile)
			  .findFirst()
			  .orElse(null);		
	}
	
	private boolean isSampleFile(File file){
		return file.getName().equalsIgnoreCase("sample.jpg") || file.getName().equalsIgnoreCase("sample.jpeg");
	}
	
	private String convertSampleFileToString64(File file){
		byte[] bytes = null;
		try {
			
			if(file == null){
				return "";
			}
			
			bytes = Files.readAllBytes(file.toPath());
			return Base64.getEncoder().encodeToString(bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
