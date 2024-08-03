package br.com.cdm.cursomc.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdm.cursomc.domain.Pedido;
import br.com.cdm.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	private final PedidoService pedidoService;
	
	public PedidoResource(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable(name = "id") Long id) {
		Pedido obj = this.pedidoService.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
