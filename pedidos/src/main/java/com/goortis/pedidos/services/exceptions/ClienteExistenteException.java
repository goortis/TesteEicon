package com.goortis.pedidos.services.exceptions;

public class ClienteExistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public ClienteExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public ClienteExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
