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

import com.goortis.pedidos.domain.Cliente;
import com.goortis.pedidos.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResources {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_ATOM_XML_VALUE })
    public ResponseEntity<List<Cliente>> listar() {
	return ResponseEntity.status(HttpStatus.OK).body(clienteService.listar());

    }

    @RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_ATOM_XML_VALUE })
    public ResponseEntity<Void> salvar(@Valid @RequestBody Cliente cliente) {
	cliente = clienteService.salvar(cliente);

	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
		.toUri();

	return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_ATOM_XML_VALUE })
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
	Cliente cliente = clienteService.buscar(id);

	CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

	return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(cliente);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
	clienteService.deletar(id);
	return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_ATOM_XML_VALUE })
    public ResponseEntity<Void> atualizar(@RequestBody Cliente cliente, @PathVariable("id") Long id) {
	cliente.setId(id);
	clienteService.atualizar(cliente);

	return ResponseEntity.noContent().build();
    }

}
