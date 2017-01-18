package br.org.itv.guia.controller.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpeciesResponse {

	@JsonProperty("species")
	private List<SpecieVO> species;

	public List<SpecieVO> getSpecies() {
		return species;
	}

	public void setSpecies(List<SpecieVO> species) {
		this.species = species;
	}
}
