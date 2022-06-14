package com.goortis.pedidos.services.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public ClienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ClienteNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
