package com.goortis.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goortis.pedidos.domain.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

}
