package br.org.itv.guia.builder;

import java.io.File;

import br.org.itv.guia.constants.HeadersEnum;

public class PopularNameBuilder extends SpecieBuilder {

	private final String POPULAR_NAME = "Nome popular";
	
	public PopularNameBuilder(File file) {
		loadDocxFile(file);
	}

	@Override
	public String getValue() {
		return getTextFromTopicHeader(HeadersEnum.POPULAR_NAME.getHeader()).trim();
	}

}
