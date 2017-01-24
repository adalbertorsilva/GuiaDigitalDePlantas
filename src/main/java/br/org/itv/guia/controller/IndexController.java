package br.org.itv.guia.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.itv.guia.controller.vo.SpecieVO;
import br.org.itv.guia.controller.vo.SpeciesResponse;
import br.org.itv.guia.service.SpecieService;

@CrossOrigin(origins = {"http://localhost:8080", "http://e0618828.ngrok.io","http://localhost:8080/guia", "http://e0618828.ngrok.io/guia" })
@RestController
@RequestMapping("guia")
public class IndexController {

	@Autowired
	private SpecieService specieService;
	
	@GetMapping
	public ResponseEntity<SpeciesResponse> getPersistedSpecies(){
		SpeciesResponse speciesResponse = new SpeciesResponse();
		
		List<SpecieVO> vos = specieService.getSpecies().stream().map(s -> new SpecieVO(s)).collect(Collectors.toList());
		speciesResponse.setSpecies(vos);
		
		ResponseEntity<SpeciesResponse> response = new ResponseEntity<>(speciesResponse, HttpStatus.OK); 
		return response;
	}
	
}
