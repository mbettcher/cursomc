package br.com.cdm.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cdm.cursomc.domain.Cliente;
import br.com.cdm.cursomc.domain.enums.TipoCliente;
import br.com.cdm.cursomc.dto.ClienteDTO;
import br.com.cdm.cursomc.repositories.ClienteRepository;
import br.com.cdm.cursomc.services.exceptions.DataIntegrityException;
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

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return clienteRepository.save(obj);
	}

	public Cliente update(Long id, Cliente obj) {
		Cliente newObj = find(id);
		this.updateData(newObj, obj);
		return this.clienteRepository.save(obj);
	}

	public void delete(Long id) {
		find(id);
		try {
			this.clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente que possua produtos!");
		}

	}

	public List<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return this.clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), 
				objDTO.getCpfCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setCpfCnpj(obj.getCpfCnpj());
		newObj.setTipo(obj.getTipo());
	}

}
