package br.org.itv.guia.manager;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.org.itv.guia.constants.PropertiesEnum;
import br.org.itv.guia.model.Specie;
import br.org.itv.guia.repository.SpecieRepository;

@Service
public class DirectoryManager extends GeneralManager{

	private File rooDirectory;
	private List<File> speciesDirectories;
	private FileManager fileManager;
	private List<Specie> jsonSpecies; 
	@Autowired
	private SpecieRepository specieRepository;

	public DirectoryManager() {
		loadProperties();
		this.rooDirectory = new File(properties.getProperty(PropertiesEnum.ROOT_DIRECTORY_PATH.getKey()));
		this.speciesDirectories = new ArrayList<>();
		this.fileManager = new FileManager();
		jsonSpecies = new ArrayList<>();
	}
	
	public List<File> getSpeciesDirectory() {
		speciesDirectories.clear();
		return getSpeciesDirectory(rooDirectory);
	}

	private List<File> getSpeciesDirectory(File directory) {
		
		Stream.of(directory.listFiles())
			  .filter(d -> d.isDirectory())
			  .forEach((subdirectory) -> {
					
					if(hasSubdirectory(subdirectory)){
						getSpeciesDirectory(subdirectory);
					}else{
						speciesDirectories.add(subdirectory);
					}
					
			});

		return speciesDirectories.stream()
								 .sorted((s1,s2) -> s1.getName().compareToIgnoreCase(s2.getName()))
								 .collect(Collectors.toList());
	}
	
	public void createSpeciesSeedFile() {
		try {
			Path path = Paths.get(getFileFullPath());
			Files.deleteIfExists(path);
			getSpeciesDirectory();
			createJsonObjects();
			
			ObjectMapper mapper = new ObjectMapper();
			StringWriter writer = new StringWriter();
			mapper.writeValue(path.toFile(), jsonSpecies);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void createJsonObjects(){
		speciesDirectories.stream().forEach((file) -> {
										fileManager.setFile(getDescriptionFile(Arrays.asList(file.listFiles())));
										Specie specie = fileManager.createSpecie();
										specie.setObjectVersion(1);
										jsonSpecies.add(specie);
										specieRepository.save(specie);
										
		});
		
	}
	
	private boolean hasSubdirectory(File directory){
		return ! Stream.of(directory.listFiles())
					   .filter(d -> d.isDirectory())
					   .collect(Collectors.toList()).isEmpty();
	}
	
	public File getDescriptionFile(Collection<File> wordFile) {
		return wordFile.stream()
						   .filter(f -> f.getName().toLowerCase().contains("descrição"))
						   .findAny().get();
	}
	
	public Collection<File> getWordFiles(File directory){
		return filterFiles(directory, this::isWordDocument); 
	}
	
	public Collection<File> getImageFiles(File directory) {
		return filterFiles(directory, this::isJpeg);
	}
	
	private Collection<File> filterFiles(File directory, Predicate<File> predicate){
		return Stream.of(directory.listFiles())
			 	 .filter(predicate)
			 	 .collect(Collectors.toList());
	}
	
	private boolean isJpeg(File file) {
		return file.getName().substring(file.getName().lastIndexOf(".")).equalsIgnoreCase(".jpg") || file.getName().substring(file.getName().lastIndexOf(".")).equalsIgnoreCase(".jpeg");
	}

	private boolean isWordDocument(File file){
		return file.getName().substring(file.getName().lastIndexOf(".")).equalsIgnoreCase(".docx") || file.getName().substring(file.getName().lastIndexOf(".")).equalsIgnoreCase(".doc");
	}
	
	private String getFileFullPath(){
		StringBuilder fileFullPath = new StringBuilder();
		fileFullPath.append(properties.getProperty(PropertiesEnum.DESTINATION_PATH.getKey()))
					.append(properties.getProperty(PropertiesEnum.FILE_NAME.getKey()));
		return fileFullPath.toString();
	}
	
	public SpecieRepository getSpecieReposiory(){
		return this.specieRepository;
	}
}
