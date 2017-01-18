package br.org.itv.guia.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.org.itv.guia.GuiaApplication;
import br.org.itv.guia.exception.ErrorResponse;
import br.org.itv.guia.model.Coordinate;
import br.org.itv.guia.model.Picture;
import br.org.itv.guia.model.Specie;
import br.org.itv.guia.repository.SpecieRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=GuiaApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSpecieController {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private SpecieRepository specieRepository;
	
	@Test @Transactional
	public void mustHaveAScietificName(){
		Specie specie = new Specie();
		
		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/specie", specie, ErrorResponse.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertNotNull(response.getBody());
	}
	
	@Test @Transactional
	public void mustHaveADescription(){
		Specie specie = new Specie();
		specie.setScientificName("some scientific name");
		
		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/specie", specie, ErrorResponse.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertNotNull(response.getBody());
	}
	
	@Test @Transactional
	public void mustHaveAFlowreing(){
		Specie specie = new Specie();
		specie.setScientificName("some scientific name");
		specie.setDescription("some description");
		
		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/specie", specie, ErrorResponse.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertNotNull(response.getBody());
	}
	
	@Test @Transactional
	public void mustHaveAHabitat(){
		Specie specie = new Specie();
		specie.setScientificName("some scientific name");
		specie.setDescription("some description");
		specie.setFlowering("some flowering description");
		
		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/specie", specie, ErrorResponse.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertNotNull(response.getBody());
	}
	
	@Test @Transactional
	public void mustHaveASampleImage(){
		Specie specie = new Specie();
		specie.setScientificName("some scientific name");
		specie.setDescription("some description");
		specie.setFlowering("some flowering description");
		specie.setHabitat("Some Habitat description");
		
		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/specie", specie, ErrorResponse.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertNotNull(response.getBody());
	}
	
	@Test @Transactional
	public void mustHaveANonEmptyCollectionOfPictures(){
		Specie specie = new Specie();
		specie.setScientificName("some scientific name");
		specie.setDescription("some description");
		specie.setFlowering("some flowering description");
		specie.setHabitat("Some Habitat description");
		specie.setSample(new Picture());
		
		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/specie", specie, ErrorResponse.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertNotNull(response.getBody());
	}
	
	@Test @Transactional
	public void mustHaveANonEmptyCollectionOfCoordinates(){
		Specie specie = new Specie();
		specie.setScientificName("some scientific name 2");
		specie.setDescription("some description");
		specie.setFlowering("some flowering description");
		specie.setHabitat("Some Habitat description");
		specie.setSample(new Picture());
		
		Picture picture = new Picture();
		picture.setEncondedImage("some encoded image");
		
		specie.getPictures().add(picture);
		
		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/specie", specie, ErrorResponse.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertNotNull(response.getBody());
	}
	
	@Test @Transactional
	public void mustPersistAnSpecie(){
		Specie specie = new Specie();
		specie.setScientificName("some scientific name 2");
		specie.setDescription("some description");
		specie.setFlowering("some flowering description");
		specie.setHabitat("Some Habitat description");
		specie.setSample(new Picture());
		
		Picture picture = new Picture();
		picture.setEncondedImage("some encoded image");
		specie.getPictures().add(picture);
		specie.getCoordinates().add(new Coordinate(123456f, 654321f));
		
		ResponseEntity<Specie> response = testRestTemplate.postForEntity("/specie", specie, Specie.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertNotNull(response.getBody());
		assertNotNull(response.getBody().getId());
	}
	
	@Test @Transactional
	public void aSpecieMustAnUniqueScientificName(){
		Specie specie = new Specie();
		specie.setScientificName("some scientific name 3");
		specie.setDescription("some description");
		specie.setFlowering("some flowering description");
		specie.setHabitat("Some Habitat description");
		specie.setSample(new Picture());
		
		Picture picture = new Picture();
		picture.setEncondedImage("some encoded image");
		specie.getPictures().add(picture);
		specie.getCoordinates().add(new Coordinate(123456f, 654321f));
		
		testRestTemplate.postForEntity("/specie", specie, Specie.class);
		
		specie = new Specie();
		specie.setScientificName("some scientific name 3");
		specie.setDescription("some description");
		specie.setFlowering("some flowering description");
		specie.setHabitat("Some Habitat description");
		specie.setSample(new Picture());
		
		picture = new Picture();
		picture.setEncondedImage("some encoded image");
		specie.getPictures().add(picture);
		specie.getCoordinates().add(new Coordinate(123456f, 654321f));
		
		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/specie", specie, ErrorResponse.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertNotNull(response.getBody());
	}
	
	@Test @Transactional
	public void mustRetrieveASpecieById(){
		
		List<Specie> species = new ArrayList<>();
		
		specieRepository.findAll().forEach(s -> species.add(s));
		
		Specie firstSpecie = species.get(0);
		ResponseEntity<Specie> response = testRestTemplate.getForEntity("/specie/" + firstSpecie.getId(), Specie.class);
		
		assertTrue(firstSpecie.getScientificName().equalsIgnoreCase(response.getBody().getScientificName()));
	}

}
