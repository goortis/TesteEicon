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

import com.goortis.pedidos.domain.Item;
import com.goortis.pedidos.services.ItemService;

@RestController
@RequestMapping("/items")
public class ItemResources {

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Item>> listar() {
	return ResponseEntity.status(HttpStatus.OK).body(itemService.listar());

    }

    @RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> salvar(@Valid @RequestBody Item item) {
	item = itemService.salvar(item);

	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(item.getId()).toUri();

	return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
	Item itemPedido = itemService.buscar(id);

	CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

	return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(itemPedido);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
	itemService.deletar(id);
	return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> atualizar(@RequestBody Item item, @PathVariable("id") Long id) {
	item.setId(id);
	itemService.atualizar(item);

	return ResponseEntity.noContent().build();
    }

}
