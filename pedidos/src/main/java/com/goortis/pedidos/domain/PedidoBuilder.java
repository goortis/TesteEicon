package com.goortis.pedidos.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PedidoBuilder {

    private Pedido instancia;

    public PedidoBuilder() {
	this.instancia = new Pedido();
    }

    public PedidoBuilder comNome(String nome) {
	this.instancia.setNome(nome);
	return this;
    }

    public PedidoBuilder comArquivo(String nome) {
	definirArquivo(nome);
	return this;
    }

    private void definirArquivo(String nome) {
	Arquivo arquivo = new Arquivo();
	arquivo.setNome(nome);
	this.instancia.setArquivo(arquivo);

    }

    public PedidoBuilder comCliente(String nome) {
	definirCliente(nome);
	return this;
    }

    private void definirCliente(String nome) {
	Cliente cliente = new Cliente();
	cliente.setNome(nome);
	this.instancia.setCliente(cliente);
    }

    public PedidoBuilder comDataCadastro(String data) throws ParseException {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
	this.instancia.setDataCadastro(formatter.parse(data));
	return this;
    }

    public PedidoBuilder comItem(String nome, Integer quantidade, String valorUnitario) {
	Item item = new Item();
	ItemPedido itemPedido = new ItemPedido();
	item.setNome(nome);

	itemPedido.setValor(new Double(valorUnitario));
	itemPedido.setQuantidade(quantidade);

	itemPedido.setItem(item);

	if (this.instancia.getItemsPedidos() == null) {
	    this.instancia.setItemsPedidos(new ArrayList<ItemPedido>());
	}

	this.instancia.getItemsPedidos().add(itemPedido);

	return this;
    }

    public PedidoBuilder comTotal(String total) {
	this.instancia.setTotal(new Double(total));
	return this;
    }

    public Pedido construir() {
	return this.instancia;
    }

}