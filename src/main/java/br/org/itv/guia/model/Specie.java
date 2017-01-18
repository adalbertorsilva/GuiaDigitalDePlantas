package br.org.itv.guia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Specie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="description", columnDefinition="TEXT") 
	private String description;
	
	@Column(columnDefinition="TEXT")
	private String flowering;
	
	@Column(columnDefinition="TEXT")
	private String habitat;
	
	@JsonProperty("popular_name")
	private String popularName;
	
	@JsonProperty("scientific_name") 
	private String scientificName;
	
	@JsonProperty("url_reflora")
	private String urlReflora;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true) 
	private List<Picture> pictures;
	
	@OneToOne(orphanRemoval=true, cascade=CascadeType.ALL, optional=true)
	private Picture sample;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true) 
	private List<Coordinate> coordinates;
	
	private Integer objectVersion;
	
	public Specie(){
		this.pictures = new ArrayList<>();
		this.coordinates = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
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
		return popularName;
	}
	public void setPopularName(String popularName) {
		this.popularName = popularName;
	}
	public String getScientificName() {
		return scientificName;
	}
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
	public String getUrlReflora() {
		return urlReflora;
	}
	public void setUrlReflora(String urlReflora) {
		this.urlReflora = urlReflora;
	}
	public List<Picture> getPictures() {
		return pictures;
	}

	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	public Picture getSample() {
		return sample;
	}

	public void setSample(Picture sample) {
		this.sample = sample;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getObjectVersion() {
		return objectVersion;
	}

	public void setObjectVersion(Integer objectVersion) {
		this.objectVersion = objectVersion;
	}
}
