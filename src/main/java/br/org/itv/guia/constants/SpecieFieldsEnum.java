package br.org.itv.guia.constants;

import java.util.stream.Stream;

public enum SpecieFieldsEnum {

	ID("id"),
	DESC("description"),
	FLOWERING("flowering"),
	HABITAT("habitat"),
	POPULAR_NAME("popularName"),
	SCIENTIFIC_NAME("scientificName"),
	URL_REFLORA("urlReflora"),
	SAMPLE("sample"),
	PICTURES("pictures"),
	COORDINATES("coordinates"),
	OBJECT_VERSION("objectVersion");
	
	
	private String fieldName;
	
	private SpecieFieldsEnum(String fieldName) {
		this.fieldName = fieldName;
	}	
	
	public String getFieldName(){
		return this.fieldName;
	}
	
	public static SpecieFieldsEnum getEnumValueByString(String fieldName){
		
		return Stream.of(SpecieFieldsEnum.values())
					 .filter(e -> e.getFieldName().equalsIgnoreCase(fieldName))
					 .findFirst().orElse(null);
		
	}
}
