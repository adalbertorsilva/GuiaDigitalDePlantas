package br.org.itv.guia.model;

public class Picture {
	
	private String encondedImage;

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

}
