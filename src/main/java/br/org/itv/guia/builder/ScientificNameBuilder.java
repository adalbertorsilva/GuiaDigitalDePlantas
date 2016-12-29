package br.org.itv.guia.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class ScientificNameBuilder extends SpecieBuilder {
	
	public ScientificNameBuilder(File file) {
		this.file = file;
		loadDocxFile(file);
	}
	
	@Override
	public String getValue() {
		return file.getParent().substring(file.getParent().lastIndexOf("/") + 1).trim();
	}


}
