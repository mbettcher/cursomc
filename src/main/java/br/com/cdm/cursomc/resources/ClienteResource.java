package br.com.cdm.cursomc.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdm.cursomc.domain.Cliente;
import br.com.cdm.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	private final ClienteService clienteService;
	
	public ClienteResource(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable("id") Long id) {
		Cliente obj = this.clienteService.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
