package br.org.itv.guia.constants;

public enum HeadersEnum {
	MORPHOLOGICAL_CHARACTERIZATION("Caracterização morfológica"),
	MORPHOLOGICAL_COMMENT("Comentário morfológico "),
	CONSERVATION_STATUS("Status de Conservação"),
	IMPORTANCE_FOR_RAD("Importância para RAD"),
	POPULAR_NAME("Nome popular"),
	GEORGRAPHIC_DISTRIBUTION("Distribuição geográfica"),
	POLLINATION_AND_DISPERSION_SYNDROME("Síndrome de polinização e de dispersão");

	private String header;
	
	private HeadersEnum(String header) {
		this.header = header;
	}
	
	public String getHeader(){
		return header;
	}
	
}
