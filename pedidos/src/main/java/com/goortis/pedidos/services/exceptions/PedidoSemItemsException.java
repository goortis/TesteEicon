package com.goortis.pedidos.services.exceptions;

public class PedidoSemItemsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public PedidoSemItemsException(String mensagem) {
		super(mensagem);
	}
	
	public PedidoSemItemsException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
