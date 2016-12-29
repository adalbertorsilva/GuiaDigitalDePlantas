package br.org.itv.guia.constants;

public enum SpecieFieldsEnum {

	ID("id"),
	DESC("desc"),
	FLOWERING("flowering"),
	HABITAT("habitat"),
	POPULAR_NAME("popular_name"),
	SCIENTIFIC_NAME("scientific_name"),
	URL_REFLORA("url_reflora"),
	SAMPLE("sample"),
	PICTURES("pictures"),
	COORDINATES("coordinates");
	
	
	private String fieldName;
	
	private SpecieFieldsEnum(String fieldName) {
		this.fieldName = fieldName;
	}	
}
