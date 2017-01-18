package br.org.itv.guia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coordinate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Float latitude;
	private Float longitude;
	private String place;
	
	public Coordinate() {}
	
	public Coordinate(String coordinatesValue, String place){
		setLatitudeAndLongitude(coordinatesValue);
		this.place = place;
	}

	public Coordinate(Float latitude, Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	private void setLatitudeAndLongitude(String coordinatesValue){
		
		String[] points = coordinatesValue.split(",");
		
		setLatitude(Float.parseFloat(points[0]));
		setLongitude(Float.parseFloat(points[1]));
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
}
