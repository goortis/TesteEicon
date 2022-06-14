package com.goortis.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goortis.pedidos.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
