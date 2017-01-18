package br.org.itv.guia.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import br.org.itv.guia.manager.FileManager;
import br.org.itv.guia.model.Specie;

public class TestFileManager {
	
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
	public void deve_criar_um_objeto_specie_a_partir_de_um_documento_de_descricao(){
		FileManager manager = new FileManager();
		manager.setFile(new File("/Users/b0014/Documents/Guia/Amanthaceae/Gomphrena arborescens L.f/Descrição_Gomphrena_arborescens.docx"));
		assertNotNull(manager.createSpecie());
	}
	
	@Test
	public void deve_criar_um_objeto_specie_com_nome_cientifico_preenchido_a_partir_de_um_documento_de_descricao(){
		FileManager manager = new FileManager();
		manager.setFile(new File("/Users/b0014/Documents/Guia/Amanthaceae/Gomphrena arborescens L.f/Descrição_Gomphrena_arborescens.docx"));
		assertNotNull(manager.createSpecie().getScientificName());
		assertFalse(manager.createSpecie().getScientificName().isEmpty());
		assertTrue(manager.createSpecie().getScientificName().equals("Gomphrena arborescens L.f"));
	}
	
	@Test
	public void deve_criar_um_objeto_specie_com_descricao_preenchida_a_partir_de_um_documento_de_descricao(){
		FileManager manager = new FileManager();
		manager.setFile(new File("/Users/b0014/Documents/Guia/Amanthaceae/Gomphrena arborescens L.f/Descrição_Gomphrena_arborescens.docx"));
		assertNotNull(manager.createSpecie().getDescription());
		assertFalse(manager.createSpecie().getDescription().isEmpty());
	}
	
	@Test
	public void deve_criar_um_objeto_specie_com_floracao_preenchida_a_partir_de_um_documento_de_descricao(){
		FileManager manager = new FileManager();
		manager.setFile(new File("/Users/b0014/Documents/Guia/Amanthaceae/Gomphrena arborescens L.f/Descrição_Gomphrena_arborescens.docx"));
		assertNotNull(manager.createSpecie().getFlowering());
		assertFalse(manager.createSpecie().getFlowering().isEmpty());
	}
	
	@Test
	public void deve_criar_um_objeto_specie_com_habitat_preenchido_a_partir_de_um_documento_de_descricao(){
		FileManager manager = new FileManager();
		manager.setFile(new File("/Users/b0014/Documents/Guia/Amanthaceae/Gomphrena arborescens L.f/Descrição_Gomphrena_arborescens.docx"));
		assertNotNull(manager.createSpecie().getHabitat());
		assertFalse(manager.createSpecie().getHabitat().isEmpty());
	}
	
	@Test
	public void deve_criar_um_objeto_specie_com_nome_popular_preenchido_a_partir_de_um_documento_de_descricao(){
		FileManager manager = new FileManager();
		manager.setFile(new File("/Users/b0014/Documents/Guia/Amanthaceae/Gomphrena arborescens L.f/Descrição_Gomphrena_arborescens.docx"));
		assertNotNull(manager.createSpecie().getPopularName());
		assertFalse(manager.createSpecie().getPopularName().isEmpty());
	}
	
//	@Test
//	public void deve_gerar_um_arquivo_ao_criar_os_objetos_json_das_especies(){
//		FileManager manager = new FileManager();
//		manager.setFile(new File("/Users/b0014/Documents/Guia/Amanthaceae/Gomphrena arborescens L.f/Descrição_Gomphrena_arborescens.docx"));
//		manager.createSpecie();
//		manager.createJsonObject();
//		
//		assertFalse(manager.createJsonObject().toString().isEmpty());		
//	}

	@Test
	public void deve_gerar_os_arquivos_de_imagem_da_especie(){
		FileManager manager = new FileManager();
		manager.setFile(new File("/Users/b0014/Documents/Guia/Amanthaceae/Gomphrena arborescens L.f/Descrição_Gomphrena_arborescens.docx"));
		
		Specie specie = manager.createSpecie(); 
		assertNotNull(specie.getPictures());
		assertTrue(specie.getPictures().size() == 4);
		assertNotNull(specie.getPictures().get(0).getEncondedImage());
		assertFalse(specie.getPictures().get(0).getEncondedImage().isEmpty());
	}
	
}
