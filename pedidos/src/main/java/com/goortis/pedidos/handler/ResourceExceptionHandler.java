package com.goortis.pedidos.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.goortis.pedidos.domain.DetalhesErro;
import com.goortis.pedidos.services.exceptions.ClienteExistenteException;
import com.goortis.pedidos.services.exceptions.ClienteNaoEncontradoException;
import com.goortis.pedidos.services.exceptions.ItemExistenteException;
import com.goortis.pedidos.services.exceptions.ItemNaoEncontradoException;
import com.goortis.pedidos.services.exceptions.PedidoExistenteException;
import com.goortis.pedidos.services.exceptions.PedidoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handleClienteNaoEncontradoException(ClienteNaoEncontradoException e,
	    HttpServletRequest request) {

	DetalhesErro erro = new DetalhesErro();
	erro.setStatus(404l);
	erro.setTitulo("O cliente não pôde ser encontrado");
	erro.setMensagemDesenvolvedor("http://erros.mydomain.com/404");
	erro.setTimestamp(System.currentTimeMillis());

	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ClienteExistenteException.class)
    public ResponseEntity<DetalhesErro> handleClienteExistenteException(ClienteExistenteException e,
	    HttpServletRequest request) {

	DetalhesErro erro = new DetalhesErro();
	erro.setStatus(409l);
	erro.setTitulo("O cliente já existe");
	erro.setMensagemDesenvolvedor("http://erros.mydomain.com/409");
	erro.setTimestamp(System.currentTimeMillis());

	return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlePedidoNaoEncontradoException(PedidoNaoEncontradoException e,
	    HttpServletRequest request) {

	DetalhesErro erro = new DetalhesErro();
	erro.setStatus(404l);
	erro.setTitulo("O pedido não pôde ser encontrado");
	erro.setMensagemDesenvolvedor("http://erros.mydomain.com/404");
	erro.setTimestamp(System.currentTimeMillis());

	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(PedidoExistenteException.class)
    public ResponseEntity<DetalhesErro> handlePedidoExistenteException(PedidoExistenteException e,
	    HttpServletRequest request) {

	DetalhesErro erro = new DetalhesErro();
	erro.setStatus(409l);
	erro.setTitulo("O pedido já existe");
	erro.setMensagemDesenvolvedor("http://erros.mydomain.com/409");
	erro.setTimestamp(System.currentTimeMillis());

	return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(ItemNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handleItemPedidoNaoEncontradoException(ItemNaoEncontradoException e,
	    HttpServletRequest request) {

	DetalhesErro erro = new DetalhesErro();
	erro.setStatus(404l);
	erro.setTitulo("O item não pôde ser encontrado");
	erro.setMensagemDesenvolvedor("http://erros.mydomain.com/404");
	erro.setTimestamp(System.currentTimeMillis());

	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ItemExistenteException.class)
    public ResponseEntity<DetalhesErro> handleItemPedidoExistenteException(ItemExistenteException e,
	    HttpServletRequest request) {

	DetalhesErro erro = new DetalhesErro();
	erro.setStatus(409l);
	erro.setTitulo("O item já existente");
	erro.setMensagemDesenvolvedor("http://erros.mydomain.com/409");
	erro.setTimestamp(System.currentTimeMillis());

	return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e,
	    HttpServletRequest request) {

	DetalhesErro erro = new DetalhesErro();
	erro.setStatus(400l);
	erro.setTitulo("Requisição inválida");
	erro.setMensagemDesenvolvedor("http://erros.mydomain.com/400");
	erro.setTimestamp(System.currentTimeMillis());

	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}