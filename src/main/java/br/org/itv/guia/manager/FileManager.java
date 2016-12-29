package br.org.itv.guia.manager;

import java.io.File;
import java.lang.reflect.Field;
import java.util.function.Consumer;
import java.util.stream.Stream;

import br.org.itv.guia.builder.SpecieBuilder;
import br.org.itv.guia.model.Specie;

public class FileManager extends GeneralManager{
	
	private File file;
	private Specie specie;
	
	public void setFile(File file){
		this.file = file;
	}
	
	public Specie getSpecie(){
		return specie;
	}

	public Specie createSpecie() {		
		specie = new Specie();
		iterateOverSpecieFieldAnd(this::fulfilSpecieObject);
		return specie;
	}
	
	private void iterateOverSpecieFieldAnd(Consumer<Field> action){
		Stream.of(specie.getClass().getDeclaredFields())
			  .forEach(action);
	}
	
	private void fulfilSpecieObject(Field field) {
		field.setAccessible(true);
		try {
			field.set(specie, SpecieBuilder.getBuilder(file, field).getValue());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
