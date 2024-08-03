package br.com.cdm.cursomc.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.cdm.cursomc.domain.Pedido;
import br.com.cdm.cursomc.repositories.PedidoRepository;
import br.com.cdm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	private final PedidoRepository pedidoRepository;
	
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
	public Pedido buscar(Long id) {
		Optional<Pedido> obj = this.pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
