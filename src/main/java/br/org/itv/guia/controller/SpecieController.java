package br.org.itv.guia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.itv.guia.exception.DuplicatedScientificNameException;
import br.org.itv.guia.exception.ErrorResponse;
import br.org.itv.guia.exception.MissingFieldException;
import br.org.itv.guia.model.Specie;
import br.org.itv.guia.service.SpecieService;

@RestController
@RequestMapping("/specie")
public class SpecieController {
	
	@Autowired
	private SpecieService specieService;
	
	@PostMapping
	public ResponseEntity<Specie> createSpecie(@RequestBody Specie requestSpecie) throws Exception{
		return new ResponseEntity<Specie>(specieService.persistSpecie(requestSpecie), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Specie> findSpecieById(@PathVariable Long id){
		return new ResponseEntity<Specie>(specieService.findSpecieById(id), HttpStatus.OK); 
	}
	
	@ExceptionHandler(MissingFieldException.class)
	public ResponseEntity<ErrorResponse> missingFieldExceptionHandler(MissingFieldException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage()), ex.getHttpStatus());
	}
	
	@ExceptionHandler(DuplicatedScientificNameException.class)
	public ResponseEntity<ErrorResponse> duplicatedScientificNameExceptionHandler(DuplicatedScientificNameException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage()), ex.getHttpStatus());
	}
	
}
