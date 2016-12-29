package br.org.itv.guia.builder;

import java.io.File;

import br.org.itv.guia.constants.HeadersEnum;

public class HabitatBuilder extends SpecieBuilder {

	private final String GEORGRAPHIC_DISTRIBUTION = "Distribuição geográfica"; 
	
	public HabitatBuilder(File file) {
		loadDocxFile(file);
	}
	
	@Override
	public String getValue() {
		return getTextFromTopicHeader(HeadersEnum.GEORGRAPHIC_DISTRIBUTION.getHeader()).trim();
	}

}
