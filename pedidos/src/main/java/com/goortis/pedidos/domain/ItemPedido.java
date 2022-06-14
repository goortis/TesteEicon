package com.goortis.pedidos.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codigo_item", nullable = false, updatable = true, insertable = true)
    private Item item;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "codigo_pedido", nullable = false, updatable = true, insertable = true)
    private Pedido pedido;

    private Integer quantidade;

    private Double valor;

    public Double getTotalPorProduto() {
	Double total = 0.0;

	if (this.getQuantidade() == 0)
	    setQuantidade(1);

	total = this.quantidade * this.valor;

	if (this.quantidade >= 10) {
	    return total - (total * 10) / 100;

	}

	if (this.quantidade > 5 && this.quantidade < 10) {
	    return total - (total * 5) / 100;
	}

	return total;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Integer getQuantidade() {
	if (quantidade != 0)
	    return quantidade;
	else {
	    this.setQuantidade(new Integer(1));
	    return new Integer(1);
	}
    }

    public void setQuantidade(Integer quantidade) {
	this.quantidade = quantidade;
    }

    public Double getValor() {
	return valor;
    }

    public void setValor(Double valor) {
	this.valor = valor;
    }

    public Item getItem() {
	return item;
    }

    public void setItem(Item item) {
	this.item = item;
    }

    public Pedido getPedido() {
	return pedido;
    }

    public void setPedido(Pedido pedido) {
	this.pedido = pedido;
    }

}
