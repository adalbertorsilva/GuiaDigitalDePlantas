package br.org.itv.guia.constants;

public enum PropertiesEnum {

	ROOT_DIRECTORY_PATH("root_directory_path"),
	DESTINATION_PATH("destination_path"),
	FILE_NAME("file_name");
	
	private String key;
	
	private PropertiesEnum(String key) {
		this.key = key;
	}
	
	public String getKey(){
		return key;
	}
}
