package br.com.cdm.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.cdm.cursomc.domain.Cliente;
import br.com.cdm.cursomc.domain.enums.TipoCliente;
import br.com.cdm.cursomc.dto.ClienteDTO;
import br.com.cdm.cursomc.repositories.ClienteRepository;
import br.com.cdm.cursomc.resources.exceptions.FieldMessage;
import br.com.cdm.cursomc.services.validation.utils.BR;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteUpdate ann) {
		//method without parameters
	}

	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.valueOf(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente auxiliar = clienteRepository.findByEmail(objDTO.getEmail());
		
		if(auxiliar != null && !auxiliar.getId().equals(uriId)) {
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
