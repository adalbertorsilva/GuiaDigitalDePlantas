package br.org.itv.guia.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Specie {

	private Long id;
	private String desc;
	private String flowering;
	private String habitat;
	@JsonProperty("popular_name")
	private String popular_name;
	@JsonProperty("scientific_name")
	private String scientific_name;
	@JsonProperty("url_reflora")
	private String url_reflora;
	private List<Picture> pictures;
	private String sample;
	private List<Coordinate> coordinates;
	
	public Specie(){
		this.pictures = new ArrayList<>();
		this.coordinates = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFlowering() {
		return flowering;
	}
	public void setFlowering(String flowering) {
		this.flowering = flowering;
	}
	public String getHabitat() {
		return habitat;
	}
	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}
	public String getPopularName() {
		return popular_name;
	}
	public void setPopularName(String popular_name) {
		this.popular_name = popular_name;
	}
	public String getScientificName() {
		return scientific_name;
	}
	public void setScientificName(String scientific_name) {
		this.scientific_name = scientific_name;
	}
	public String getUrlReflora() {
		return url_reflora;
	}
	public void setUrlReflora(String url_reflora) {
		this.url_reflora = url_reflora;
	}
	public List<Picture> getPictures() {
		return pictures;
	}

	public String getSample() {
		return sample;
	}

	public void setSample(String sample) {
		this.sample = sample;
	}

	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
