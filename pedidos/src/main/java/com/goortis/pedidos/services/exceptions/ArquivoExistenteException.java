package com.goortis.pedidos.services.exceptions;

public class ArquivoExistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public ArquivoExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public ArquivoExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
