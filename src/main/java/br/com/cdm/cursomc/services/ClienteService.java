package br.com.cdm.cursomc.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.cdm.cursomc.domain.Cliente;
import br.com.cdm.cursomc.repositories.ClienteRepository;
import br.com.cdm.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	
	public Cliente find(Long id) {
		Optional<Cliente> obj = this.clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrato! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
