package br.org.itv.guia.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import br.org.itv.guia.constants.HeadersEnum;

public class DescriptionBuilder extends SpecieBuilder {
	
	public DescriptionBuilder(File file) {
		loadDocxFile(file);
	}

	@Override
	public String getValue() {
		
		StringBuilder builder = new StringBuilder();
		
		String paragraphText = getTextFromTopicHeader(HeadersEnum.MORPHOLOGICAL_CHARACTERIZATION.getHeader()).isEmpty() ? 
							   getTextFromTopicHeader(HeadersEnum.MORPHOLOGICAL_COMMENT.getHeader()) : 
							   getTextFromTopicHeader(HeadersEnum.MORPHOLOGICAL_CHARACTERIZATION.getHeader());
		
		List<Integer> pointIndexes = getPatternIndexes(paragraphText, ".");
		
		builder.append(paragraphText.substring(0, pointIndexes.get(pointIndexes.size() -2) +1));
		builder.append(getTextFromTopicHeader(HeadersEnum.CONSERVATION_STATUS.getHeader()));
		builder.append(getTextFromTopicHeader(HeadersEnum.IMPORTANCE_FOR_RAD.getHeader()));
		
		return convertHyphenToTwoPoints(builder.toString().trim());
	}
	
}
