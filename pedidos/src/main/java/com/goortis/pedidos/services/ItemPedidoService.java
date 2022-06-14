package com.goortis.pedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.goortis.pedidos.domain.ItemPedido;
import com.goortis.pedidos.repository.ItemPedidoRepository;
import com.goortis.pedidos.services.exceptions.ItemExistenteException;
import com.goortis.pedidos.services.exceptions.ItemNaoEncontradoException;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> listar() {

	return itemPedidoRepository.findAll();
    }

    public ItemPedido salvar(ItemPedido itemPedido) {

	if (itemPedido.getId() != null) {
	    ItemPedido a = itemPedidoRepository.findOne(itemPedido.getId());

	    if (a != null) {
		throw new ItemExistenteException("O Item já existe.");
	    }
	}

	return itemPedidoRepository.save(itemPedido);

    }

    public ItemPedido buscar(Long id) {
	ItemPedido Item = itemPedidoRepository.findOne(id);

	if (Item == null) {
	    throw new ItemNaoEncontradoException("O item não pôde ser encontrado.");
	}
	return Item;
    }

    public void deletar(Long id) {
	try {
	    itemPedidoRepository.delete(id);
	} catch (EmptyResultDataAccessException e) {
	    throw new ItemNaoEncontradoException("O item não pôde ser encontrado.");
	}
    }

    public void atualizar(ItemPedido item) {
	verificarExistencia(item);
	itemPedidoRepository.save(item);
    }

    private void verificarExistencia(ItemPedido item) {
	buscar(item.getId());
    }
}