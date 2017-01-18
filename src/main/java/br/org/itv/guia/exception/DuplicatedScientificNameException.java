package br.org.itv.guia.exception;

import org.springframework.http.HttpStatus;

public class DuplicatedScientificNameException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6623271564478011316L;

	/**
	 * 
	 */
	
	@Override
	public String getMessage() {
		return "Já existe uma espécie cadastrada com esse nome !";
	}
	
	public HttpStatus getHttpStatus(){
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

}
