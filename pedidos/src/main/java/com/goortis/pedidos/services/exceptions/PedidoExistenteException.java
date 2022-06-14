package com.goortis.pedidos.services.exceptions;

public class PedidoExistenteException extends RuntimeException {

    private static final long serialVersionUID = 1869300553614629710L;

    public PedidoExistenteException(String mensagem) {
	super(mensagem);
    }

    public PedidoExistenteException(String mensagem, Throwable causa) {
	super(mensagem, causa);
    }

}
