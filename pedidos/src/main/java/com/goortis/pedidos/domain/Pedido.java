package com.goortis.pedidos.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Pedido {

    @Id
    private Long codPedido;

    @OneToOne(cascade = CascadeType.MERGE)
    private Cliente cliente;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "codigo_arquivo", nullable = false, updatable = true, insertable = true)
    private Arquivo arquivo;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.MERGE)
    private List<ItemPedido> itemsPedidos;

    @NotEmpty(message = "O nome deve ser preenchido.")
    @Size(max = 30, message = "O nome não pode contar mais de 30 caracteres.")
    private String nome;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    private Double total;

    public Arquivo getArquivo() {
	return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
	this.arquivo = arquivo;
    }

    public Long getCodPedido() {
	return codPedido;
    }

    public void setCodPedido(Long codPedido) {
	this.codPedido = codPedido;
    }

    public Date getDataCadastro() {
	if (this.dataCadastro == null)
	    return new Date();
	return this.dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
	this.dataCadastro = dataCadastro;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public List<ItemPedido> getItemsPedidos() {
	return itemsPedidos;
    }

    public void setItemsPedidos(List<ItemPedido> itemsPedidos) {
	this.itemsPedidos = itemsPedidos;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Double getTotal() {
	Double totalPorProduto = 0.0;
	if (this.getItemsPedidos().isEmpty())
	    return 0.0;
	for (ItemPedido itemPedido : this.getItemsPedidos()) {
	    totalPorProduto = totalPorProduto + (itemPedido.getTotalPorProduto());
	}
	this.setTotal(totalPorProduto);
	return totalPorProduto;
    }

    public void setTotal(Double total) {
	this.total = total;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((arquivo == null) ? 0 : arquivo.hashCode());
	result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
	result = prime * result + ((codPedido == null) ? 0 : codPedido.hashCode());
	result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((total == null) ? 0 : total.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Pedido other = (Pedido) obj;
	if (arquivo == null) {
	    if (other.arquivo != null)
		return false;
	} else if (!arquivo.equals(other.arquivo))
	    return false;
	if (cliente == null) {
	    if (other.cliente != null)
		return false;
	} else if (!cliente.equals(other.cliente))
	    return false;
	if (codPedido == null) {
	    if (other.codPedido != null)
		return false;
	} else if (!codPedido.equals(other.codPedido))
	    return false;
	if (dataCadastro == null) {
	    if (other.dataCadastro != null)
		return false;
	} else if (!dataCadastro.equals(other.dataCadastro))
	    return false;
	if (nome == null) {
	    if (other.nome != null)
		return false;
	} else if (!nome.equals(other.nome))
	    return false;
	if (total == null) {
	    if (other.total != null)
		return false;
	} else if (!total.equals(other.total))
	    return false;
	return true;
    }

}