package br.com.cdm.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cdm.cursomc.domain.Cliente;
import br.com.cdm.cursomc.domain.enums.TipoCliente;
import br.com.cdm.cursomc.dto.ClienteNewDTO;
import br.com.cdm.cursomc.repositories.ClienteRepository;
import br.com.cdm.cursomc.resources.exceptions.FieldMessage;
import br.com.cdm.cursomc.services.validation.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {
		//method without parameters
	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDTO.getCpfCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF inválido!"));
		}
		
		if(objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDTO.getCpfCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF inválido!"));
		}
		
		Cliente auxiliar = clienteRepository.findByEmail(objDTO.getEmail());
		
		if(auxiliar != null) {
			list.add(new FieldMessage("email","Email já cadastrado!"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}
}
