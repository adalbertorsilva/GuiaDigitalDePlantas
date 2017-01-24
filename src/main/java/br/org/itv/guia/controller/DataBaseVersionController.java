package br.org.itv.guia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.itv.guia.controller.vo.SpecieEntityResponse;
import br.org.itv.guia.service.SpecieService;

@CrossOrigin(origins = {"http://localhost:8080", "http://e0618828.ngrok.io"})
@RestController
@RequestMapping("/version")
public class DataBaseVersionController {
	
	@Autowired
	private SpecieService specieService;
	
	@GetMapping
	public ResponseEntity<Integer> getDataBaseLastVersion(){
		return new ResponseEntity<Integer>(specieService.findDataBaseLastVersion(), HttpStatus.OK);
	}
	
	@GetMapping("{dataBaseVersion}")
	public ResponseEntity<SpecieEntityResponse> getNewSpecies(@PathVariable Integer dataBaseVersion){
		
		SpecieEntityResponse speciesResponse = new SpecieEntityResponse();
		speciesResponse.setSpecies(specieService.findNewSpecies(dataBaseVersion));
		
		return new ResponseEntity<SpecieEntityResponse>(speciesResponse, HttpStatus.OK);
	}

}
