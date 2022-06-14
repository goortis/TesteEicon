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

import com.goortis.pedidos.domain.Pedido;
import com.goortis.pedidos.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResources {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Pedido>> listar() {
	return ResponseEntity.status(HttpStatus.OK).body(pedidoService.listar());

    }

    @RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> salvar(@Valid @RequestBody Pedido pedido) {

	pedido = pedidoService.salvar(pedido);

	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getCodPedido())
		.toUri();

	return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
	Pedido pedido = pedidoService.buscar(id);

	CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

	return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(pedido);
    }

    @RequestMapping(value = "/{codPedido}/cliente/{idCliente}", method = RequestMethod.GET, produces = {
	    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> buscarPedidoPorCliente(@PathVariable("codPedido") Long codPedido,
	    @PathVariable("idCliente") Long idCliente) {
	Pedido pedidos = pedidoService.buscarPedidoPorCliente(codPedido, idCliente);

	return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @RequestMapping(value = "/cliente/{idCliente}", method = RequestMethod.GET, produces = {
	    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> buscarTodosPedidosDoCliente(@PathVariable("idCliente") Long idCliente) {
	List<Pedido> pedidos = pedidoService.buscarTodosPedidosDoCliente(idCliente);

	return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @RequestMapping(value = "/data/{dd}/{mm}/{yyyy}", method = RequestMethod.GET, produces = {
	    MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> buscarTodosPedidosPorData(@PathVariable("dd") Integer dd, @PathVariable("mm") Integer mm,
	    @PathVariable("yyyy") Integer yyyy) {
	List<Pedido> pedidos = pedidoService.buscarTodosPedidosPorData(dd, mm, yyyy);

	return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
	pedidoService.deletar(id);
	return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> atualizar(@RequestBody Pedido pedido, @PathVariable("id") Long id) {
	pedido.setCodPedido(id);
	pedidoService.atualizar(pedido);

	return ResponseEntity.noContent().build();
    }

}
