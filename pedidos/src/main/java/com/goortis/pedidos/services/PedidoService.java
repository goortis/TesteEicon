package com.goortis.pedidos.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goortis.pedidos.domain.ItemPedido;
import com.goortis.pedidos.domain.Pedido;
import com.goortis.pedidos.repository.PedidoRepository;
import com.goortis.pedidos.services.exceptions.PedidoExistenteException;
import com.goortis.pedidos.services.exceptions.PedidoNaoEncontradoException;
import com.goortis.pedidos.services.exceptions.PedidoSemItemsException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listar() {

	return pedidoRepository.findAll();
    }

    public Pedido salvar(Pedido pedido) {
	Pedido a = null;
	if (pedido.getCodPedido() != null)
	    a = pedidoRepository.findOne(pedido.getCodPedido());
	if (a != null)
	    throw new PedidoExistenteException("O pedido já existe.");
	if (!isPedidoComItems(pedido))
	    throw new PedidoSemItemsException("O pedido debe ter pelo menos um item");
	if (pedido.getDataCadastro() == null)
	    pedido.setDataCadastro(java.sql.Date.valueOf(LocalDate.now()));

	calcularValorTotal(pedido);

	return pedidoRepository.save(pedido);

    }

    private boolean isPedidoComItems(Pedido pedido) {

	return !pedido.getItemsPedidos().isEmpty();
    }

    private boolean isPedidoComMaisDeDezItems(Pedido pedido) {
	if (pedido.getItemsPedidos().isEmpty())
	    return false;
	return pedido.getItemsPedidos().stream().count() > 10;
    }

    private void calcularValorTotal(Pedido pedido) {
	Double totalPorProduto = 0.0;
	for (ItemPedido itemPedido : pedido.getItemsPedidos()) {
	    totalPorProduto = totalPorProduto + (itemPedido.getTotalPorProduto());
	}
	pedido.setTotal(totalPorProduto);

    }

    public Pedido buscar(Long id) {
	Pedido pedido = pedidoRepository.findOne(id);

	if (pedido == null) {
	    throw new PedidoNaoEncontradoException("O pedido não pôde ser encontrado.");
	}
	return pedido;
    }

    public void deletar(Long id) {
	try {
	    pedidoRepository.delete(id);
	} catch (EmptyResultDataAccessException e) {
	    throw new PedidoNaoEncontradoException("O pedido não pôde ser encontrado.");
	}
    }

    public void atualizar(Pedido pedido) {
	verificarExistencia(pedido);
	pedidoRepository.save(pedido);
    }

    private void verificarExistencia(Pedido pedido) {
	buscar(pedido.getCodPedido());
    }

    public Pedido buscarPedidoPorCliente(Long codPedido, Long idCliente) {
	return pedidoRepository.buscarPedidoPorCliente(codPedido, idCliente);
    }

    public List<Pedido> buscarTodosPedidosDoCliente(Long idCliente) {
	return pedidoRepository.buscarTodosPedidosDoCliente(idCliente);
    }

    public List<Pedido> buscarTodosPedidosPorData(Integer dd, Integer mm, Integer yyyy) {
	Date date = java.sql.Date.valueOf(LocalDate.of(yyyy, mm, dd));
	return pedidoRepository.buscarTodosPedidosPorData(date);
    }

}
