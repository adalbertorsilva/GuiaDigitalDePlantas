package br.org.itv.guia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Picture {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(columnDefinition="TEXT")
	@JsonProperty("encoded_image")
	private String encondedImage;
	
	@JsonProperty("picture_name")
	private String pictureName;

	public Picture(){}
	
	public String getEncondedImage() {
		return encondedImage;
	}

	public void setEncondedImage(String encondedImage) {
		this.encondedImage = encondedImage;
	}
	
	@Override
	public String toString() {
		return encondedImage;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

}
