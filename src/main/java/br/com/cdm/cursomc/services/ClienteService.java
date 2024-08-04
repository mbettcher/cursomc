package br.com.cdm.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cdm.cursomc.domain.Cidade;
import br.com.cdm.cursomc.domain.Cliente;
import br.com.cdm.cursomc.domain.Endereco;
import br.com.cdm.cursomc.domain.enums.TipoCliente;
import br.com.cdm.cursomc.dto.ClienteDTO;
import br.com.cdm.cursomc.dto.ClienteNewDTO;
import br.com.cdm.cursomc.repositories.CidadeRepository;
import br.com.cdm.cursomc.repositories.ClienteRepository;
import br.com.cdm.cursomc.repositories.EnderecoRepository;
import br.com.cdm.cursomc.services.exceptions.DataIntegrityException;
import br.com.cdm.cursomc.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final CidadeRepository cidadeRepository;
	private final EnderecoRepository enderecoRepository;

	public ClienteService(
			ClienteRepository clienteRepository, 
			CidadeRepository cidadeRepository,
			EnderecoRepository enderecoRepository) {
		this.clienteRepository = clienteRepository;
		this.cidadeRepository = cidadeRepository;
		this.enderecoRepository = enderecoRepository;
	}

	public Cliente find(Long id) {
		Optional<Cliente> obj = this.clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrato! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
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
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfCnpj(), 
				TipoCliente.toEnum(objDTO.getTipo()));
		
		Optional<Cidade> cidade = cidadeRepository.findById(objDTO.getCidadeId());
		
		Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), 
				objDTO.getBairro(), objDTO.getCep(), cliente, (cidade.isPresent()) ? cidade.get() : null);
		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {
			cliente.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			cliente.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cliente;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setCpfCnpj(obj.getCpfCnpj());
		newObj.setTipo(obj.getTipo());
	}

}
