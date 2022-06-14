package com.goortis.pedidos.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.goortis.pedidos.domain.ItemPedido;
import com.goortis.pedidos.services.ItemPedidoService;

@RestController
@RequestMapping("/itemsPedidos")
public class ItemPedidoResources {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<ItemPedido>> listar() {
	return ResponseEntity.status(HttpStatus.OK).body(itemPedidoService.listar());

    }

    @RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> salvar(@Valid @RequestBody ItemPedido item) {
	item = itemPedidoService.salvar(item);

	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(item.getId()).toUri();

	return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
	ItemPedido itemPedido = itemPedidoService.buscar(id);

	CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

	return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(itemPedido);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
	itemPedidoService.deletar(id);
	return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> atualizar(@RequestBody ItemPedido item, @PathVariable("id") Long id) {
	item.setId(id);
	itemPedidoService.atualizar(item);

	return ResponseEntity.noContent().build();
    }

}
