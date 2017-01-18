package br.org.itv.guia.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.org.itv.guia.model.Specie;

public class SpecieVO {
	
	private Long id;
	@JsonProperty("scientific_name")
	private String scientificName;
	
	public SpecieVO() {}
	
	public SpecieVO(Specie specie){
		this.id = specie.getId();
		this.scientificName = specie.getScientificName();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getScientificName() {
		return scientificName;
	}
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

}
