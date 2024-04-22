package br.com.santander.digital.formalization.adapters.out;

import java.time.LocalDate;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.santander.digital.formalization.adapters.in.converter.ContractConverter;
import br.com.santander.digital.formalization.adapters.in.dto.ClientResponseDTO;
import br.com.santander.digital.formalization.adapters.in.dto.ProductResponseDTO;
import br.com.santander.digital.formalization.adapters.in.entity.ContractEntity;
import br.com.santander.digital.formalization.adapters.out.repository.ContractRepository;
import br.com.santander.digital.formalization.adapters.out.rest.ClientService;
import br.com.santander.digital.formalization.adapters.out.rest.ProductService;
import br.com.santander.digital.formalization.application.core.domain.Contract;
import br.com.santander.digital.formalization.application.core.exception.BusinessException;
import br.com.santander.digital.formalization.application.ports.out.ContractPort;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;


@Component
@AllArgsConstructor
public class ContractAdapter implements ContractPort {
	
	private final ContractRepository repository;
	private final ProductService productService;
	private final ClientService clientService;
	private final ContractConverter converter;
	

	private static final String NÃO_ENCONTRADO_PRODUTO_PARA_O_CODIGO_INFORMADO = "Não encontrado produto para o codigo informado";
	private static final String NÃO_ENCONTRADO_CLIENTE_PARA_O_CODIGO_INFORMADO = "Não encontrado cliente para o codigo informado";
	
	@Override
	public Contract saveContract(String clienId, String productId) throws BusinessException{
		
		ResponseEntity<ProductResponseDTO> productResponseEntity = productService.getProduct(productId);
		
		if (!productResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
			throw new BusinessException(NÃO_ENCONTRADO_PRODUTO_PARA_O_CODIGO_INFORMADO); 
            
		}
		
		ResponseEntity<ClientResponseDTO> clientResponseEntity = clientService.getClient(clienId);
		
		if (!clientResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
			throw new BusinessException(NÃO_ENCONTRADO_CLIENTE_PARA_O_CODIGO_INFORMADO); 
            
		}
		
		ContractEntity productEntity = buidContract(productId, productResponseEntity, clientResponseEntity);
		
		
		ContractEntity save = repository.save(productEntity);
		return converter.fromRepositoryEntityToModelCore(save);
	}


	private ContractEntity buidContract(String productId, ResponseEntity<ProductResponseDTO> productResponseEntity,
			ResponseEntity<ClientResponseDTO> clientResponseEntity) {
		
		ProductResponseDTO productResponseDTO = productResponseEntity.getBody();
		ClientResponseDTO clientResponseEntityDTO = clientResponseEntity.getBody();
		
		ContractEntity productEntity = 
				ContractEntity.builder()
				.active(true)
				.clientId(clientResponseEntityDTO.getClientId())
				.createdDate(LocalDate.now())
				.id(productId)
				.price(productResponseDTO.getProductPrice())
				.productId(productResponseDTO.getProductId())
				.build();
		return productEntity;
	}

	

	

}
