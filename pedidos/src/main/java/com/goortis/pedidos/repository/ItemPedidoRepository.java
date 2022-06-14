package com.goortis.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goortis.pedidos.domain.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
