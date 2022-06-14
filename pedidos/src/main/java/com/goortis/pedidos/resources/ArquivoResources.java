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

import com.goortis.pedidos.domain.Arquivo;
import com.goortis.pedidos.services.ArquivoService;

@RestController
@RequestMapping("/arquivos")
public class ArquivoResources {

    @Autowired
    private ArquivoService arquivoService;

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Arquivo>> listar() {
	return ResponseEntity.status(HttpStatus.OK).body(arquivoService.listar());

    }

    @RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> salvar(@Valid @RequestBody Arquivo arquivo) {

	arquivo = arquivoService.salvar(arquivo);

	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(arquivo.getId())
		.toUri();

	return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
	Arquivo arquivo = arquivoService.buscar(id);

	CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

	return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(arquivo);
    }

}
