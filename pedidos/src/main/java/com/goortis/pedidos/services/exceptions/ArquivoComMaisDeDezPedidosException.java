package com.goortis.pedidos.services.exceptions;

public class ArquivoComMaisDeDezPedidosException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public ArquivoComMaisDeDezPedidosException(String mensagem) {
		super(mensagem);
	}
	
	public ArquivoComMaisDeDezPedidosException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
