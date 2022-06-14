package com.goortis.pedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goortis.pedidos.domain.Arquivo;
import com.goortis.pedidos.repository.ArquivoRepository;
import com.goortis.pedidos.services.exceptions.ArquivoComMaisDeDezPedidosException;
import com.goortis.pedidos.services.exceptions.ArquivoExistenteException;
import com.goortis.pedidos.services.exceptions.ClienteNaoEncontradoException;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository arquivoRepository;

    public List<Arquivo> listar() {

	return arquivoRepository.findAll();
    }

    public Arquivo salvar(Arquivo arquivo) {

	Arquivo a = null;
	if (arquivo.getId() != null)
	    a = arquivoRepository.findOne(arquivo.getId());
	if (a != null)
	    throw new ArquivoExistenteException("O pedido já existe.");
	if (isArquivoComMaisDeDezPedidos(arquivo))
	    throw new ArquivoComMaisDeDezPedidosException("Não pode ter mais de dez pedidos");

	return arquivoRepository.save(arquivo);

    }

    public Arquivo buscar(Long id) {
	Arquivo arquivo = arquivoRepository.findOne(id);

	if (arquivo == null) {
	    throw new ClienteNaoEncontradoException("O arquivo não pôde ser encontrado.");
	}
	return arquivo;
    }

    public void deletar(Long id) {
	try {
	    arquivoRepository.delete(id);
	} catch (EmptyResultDataAccessException e) {
	    throw new ClienteNaoEncontradoException("O cliente não pôde ser encontrado.");
	}
    }

    public void atualizar(Arquivo arquivo) {
	verificarExistencia(arquivo);
	arquivoRepository.save(arquivo);
    }

    private void verificarExistencia(Arquivo arquivo) {
	buscar(arquivo.getId());
    }

    private boolean isArquivoComMaisDeDezPedidos(Arquivo arquivo) {

	return arquivo.getPedidos().stream().count() > 10;
    }
}