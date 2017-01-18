package br.org.itv.guia.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.org.itv.guia.exception.DuplicatedScientificNameException;
import br.org.itv.guia.exception.MissingFieldException;
import br.org.itv.guia.model.Specie;
import br.org.itv.guia.repository.SpecieRepository;
import br.org.itv.guia.utils.Utils;

@Component
public class SpecieService {

	@Autowired
	private SpecieRepository specieRepository;

	public List<Specie> getSpecies() {
		
		List<Specie> species = new ArrayList<>();
		specieRepository.findAll().forEach(species::add);
		return species;
	}

	
	public Specie persistSpecie(Specie requestSpecie) throws Exception {
		
		validateFields(requestSpecie);
		validateScientificNameUniquiness(requestSpecie);
		requestSpecie.setObjectVersion(specieRepository.findLastVersion() + 1);
		return specieRepository.save(requestSpecie);
	}
	
	public Specie findSpecieById(Long id) {
		return specieRepository.findOne(id);
	}
	
	private void validateFields(Specie requestSpecie) throws MissingFieldException, IllegalArgumentException, IllegalAccessException{
		for(Field field : requestSpecie.getClass().getDeclaredFields()){
			if(!field.getName().equalsIgnoreCase("id") && !field.getName().equalsIgnoreCase("popularName") && !field.getName().equalsIgnoreCase("urlreflora") && !field.getName().equalsIgnoreCase("objectversion")) {
				validateField(requestSpecie, field);
			}
		}
	}
	
	private void validateField(Specie requestSpecie, Field field) throws MissingFieldException, IllegalArgumentException, IllegalAccessException{
		
		field.setAccessible(true);
		
		if(field.getType().equals(String.class) && (Utils.isNull(field.get(requestSpecie)) || ((String)field.get(requestSpecie)).isEmpty())){
			throw new MissingFieldException(field);
		}
		
		if(field.getType().equals(List.class) && (Utils.isNull(field.get(requestSpecie)) || ((List)field.get(requestSpecie)).isEmpty())){
			throw new MissingFieldException(field);
		}
		
		if(Utils.isNull(field.get(requestSpecie))){
			throw new MissingFieldException(field);
		}
	}
	
	private void validateScientificNameUniquiness(Specie requestSpecie) throws DuplicatedScientificNameException {
		if (!Utils.isNull(specieRepository.findByScientificName(requestSpecie.getScientificName()))){
			throw new DuplicatedScientificNameException();
		}
		
	}
}
