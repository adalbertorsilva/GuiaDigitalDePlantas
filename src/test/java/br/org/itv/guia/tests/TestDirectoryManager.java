package br.org.itv.guia.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import br.org.itv.guia.constants.PropertiesEnum;
import br.org.itv.guia.manager.DirectoryManager;

public class TestDirectoryManager {
	
	private static Properties properties;
	
	@BeforeClass
	public static void init(){
		loadProperties();
	}
	
	private static void loadProperties(){
		try {
			properties = new Properties();	
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream input = classloader.getResourceAsStream("config.properties");
			properties.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void deve_obter_todas_as_pastas_de_especies(){
		DirectoryManager manager = new DirectoryManager();
		assertTrue(manager.getSpeciesDirectory().size() == 75);
		assertTrue(manager.getSpeciesDirectory().size() == 75);
	}
	
	@Test
	public void deve_obter_todos_arquivos_word_do_diretorio(){
		DirectoryManager manager = new DirectoryManager();
		assertTrue(manager.getWordFiles(new File("/Users/b0014/Documents/Guia/Amanthaceae/Gomphrena arborescens L.f/")).size() == 2);
	}
	
	@Test
	public void deve_obter_o_arquivo_de_descricao_da_especie(){
		DirectoryManager manager = new DirectoryManager();
		assertNotNull(manager.getDescriptionFile(manager.getWordFiles(new File("/Users/b0014/Documents/Guia/Amanthaceae/Gomphrena arborescens L.f/"))));
	}
	
	@Test
	public void deve_gerar_arquivo_txt(){
		DirectoryManager manager = new DirectoryManager();
		manager.createSpeciesSeedFile();
		
		File file = new File(properties.getProperty(PropertiesEnum.DESTINATION_PATH.getKey()) + properties.getProperty(PropertiesEnum.FILE_NAME.getKey())); 
		assertTrue(file.exists());
	}
	
	@Test
	public void deve_gerar_arquivo_com_conteudo_dos_objetos_convertidos() throws IOException{
		DirectoryManager manager = new DirectoryManager();
		manager.getSpeciesDirectory();
		manager.createSpeciesSeedFile();
		
		Path path = Paths.get(properties.getProperty(PropertiesEnum.DESTINATION_PATH.getKey()) + properties.getProperty(PropertiesEnum.FILE_NAME.getKey()));
//		String fileContent = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		
//		assertTrue(fileContent.equalsIgnoreCase(manager.createJsonObjects()));
		
	}

	@Test
	public void deve_obter_todos_os_arquivos_jpeg_do_diretorio(){
		DirectoryManager manager = new DirectoryManager();
		assertTrue(manager.getImageFiles(new File("/Users/b0014/Documents/Guia/Amanthaceae/Gomphrena arborescens L.f/")).size() == 4);
	}
}
