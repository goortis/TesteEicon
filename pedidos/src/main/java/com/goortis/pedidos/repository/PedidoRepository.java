package com.goortis.pedidos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.goortis.pedidos.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "select p from Pedido p WHERE p.codPedido = :numPedido and p.cliente.id = :idCliente")
    Pedido buscarPedidoPorCliente(@Param("numPedido") Long numPedido, @Param("idCliente") Long idCliente);

    @Query(value = "select p from Pedido p WHERE  p.cliente.id = :idCliente")
    List<Pedido> buscarTodosPedidosDoCliente(@Param("idCliente") Long idCliente);

    @Query(value = "select p from Pedido p WHERE  p.dataCadastro = :dataCadastro")
    List<Pedido> buscarTodosPedidosPorData(@Param("dataCadastro") Date dataCadastro);

}
