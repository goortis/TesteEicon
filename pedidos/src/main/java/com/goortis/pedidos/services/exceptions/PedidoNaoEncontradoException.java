package com.goortis.pedidos.services.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public PedidoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public PedidoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
