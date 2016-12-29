package br.org.itv.guia.builder;

import java.io.File;
import java.util.List;

import br.org.itv.guia.constants.HeadersEnum;

public class FloweringBuilder extends SpecieBuilder {

	
	public FloweringBuilder(File file) {
		loadDocxFile(file);
	}
	
	@Override
	public String getValue() {
		
		StringBuilder builder = new StringBuilder();
		
		String paragraphText = getTextFromTopicHeader(HeadersEnum.MORPHOLOGICAL_CHARACTERIZATION.getHeader()).isEmpty() ? 
				   			   getTextFromTopicHeader(HeadersEnum.MORPHOLOGICAL_COMMENT.getHeader()) : 
				   			   getTextFromTopicHeader(HeadersEnum.MORPHOLOGICAL_CHARACTERIZATION.getHeader());

		List<Integer> pointIndexes = getPatternIndexes(paragraphText, ".");
		
		builder.append(paragraphText.substring(pointIndexes.get(pointIndexes.size() -2) +1));
		builder.append(getTextFromTopicHeader(HeadersEnum.POLLINATION_AND_DISPERSION_SYNDROME.getHeader()));
		
		return convertHyphenToTwoPoints(builder.toString().trim());
	}
}
