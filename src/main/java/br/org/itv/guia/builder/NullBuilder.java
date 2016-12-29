package br.org.itv.guia.builder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NullBuilder extends SpecieBuilder {

	private final String NOT_SET = "Não informado.";
	private Field field;
	
	
	public NullBuilder(Field field) {
		this.field = field;
	}

	@Override
	public Object getValue() {
		
		if(field.getType().equals(List.class)){
			return new ArrayList<>();
		}
		
		return ! field.getType().equals(String.class) ? null : NOT_SET;
	}

}
