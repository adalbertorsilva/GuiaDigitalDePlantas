package br.org.itv.guia.builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.stream.Stream;

public class SampleBuilder extends SpecieBuilder {

	
	public SampleBuilder(File file) {
		this.file = file;
	}
	
	@Override
	public Object getValue() {
		File picturesDirecotry = file.getParentFile();
		return convertSampleFileToString64(getSampleImageFile(picturesDirecotry));
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
