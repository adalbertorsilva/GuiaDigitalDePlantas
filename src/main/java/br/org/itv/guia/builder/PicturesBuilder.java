package br.org.itv.guia.builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.org.itv.guia.model.Picture;

public class PicturesBuilder extends SpecieBuilder {

	public PicturesBuilder(File file) {
		this.file = file;
	}
	
	@Override
	public List<Picture> getValue() {
		File picturesDirecotry = file.getParentFile();
		
		List<Picture> pictures = getImageFiles(picturesDirecotry).stream()
				   												 .filter(this::isSmallerThan10MB)
				   												 .map(this::createPicture)
				   												 .collect(Collectors.toList()); 
		return pictures;
	}

	public Collection<File> getImageFiles(File directory) {
		return filterFiles(directory, this::isJpeg);
	}
	
	private Collection<File> filterFiles(File directory, Predicate<File> predicate){
		return Stream.of(directory.listFiles())
			 	 .filter(predicate)
			 	 .collect(Collectors.toList());
	}
	
	private boolean isJpeg(File file){
		return file.getName().substring(file.getName().lastIndexOf(".")).equalsIgnoreCase(".jpg") || file.getName().substring(file.getName().lastIndexOf(".")).equalsIgnoreCase(".jpeg");
	}
	
	private Picture createPicture(File file){
		
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(file.toPath());
			Picture picture = new Picture();
			picture.setEncondedImage(Base64.getEncoder().encodeToString(bytes));
			return picture;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private boolean isSmallerThan10MB(File file){
		try {
			return Files.size(file.toPath()) < (7 * 1024 * 1024);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
