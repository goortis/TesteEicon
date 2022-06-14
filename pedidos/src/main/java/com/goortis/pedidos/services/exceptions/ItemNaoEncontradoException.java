package com.goortis.pedidos.services.exceptions;

public class ItemNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public ItemNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ItemNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
