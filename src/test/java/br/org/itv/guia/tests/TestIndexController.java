package br.org.itv.guia.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.org.itv.guia.GuiaApplication;
import br.org.itv.guia.controller.vo.SpeciesResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=GuiaApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestIndexController {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void mustRetrieveAllSpeciesWhenInIndex(){
		ResponseEntity<SpeciesResponse> responseSpecies = testRestTemplate.getForEntity("/guia", SpeciesResponse.class);
		assertTrue(responseSpecies.getStatusCode().equals(HttpStatus.OK));
		assertNotNull(responseSpecies.getBody());
		assertTrue(responseSpecies.getBody().getSpecies().size() > 0);
	}
	
}
