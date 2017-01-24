package br.org.itv.guia.controller.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.org.itv.guia.model.Specie;

public class SpecieEntityResponse {
	
	@JsonProperty("species")
	private List<Specie> species;

	public List<Specie> getSpecies() {
		return species;
	}

	public void setSpecies(List<Specie> species) {
		this.species = species;
	}

}
