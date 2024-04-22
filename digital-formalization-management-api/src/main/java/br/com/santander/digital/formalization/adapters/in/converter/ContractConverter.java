package br.com.santander.digital.formalization.adapters.in.converter;

import org.springframework.stereotype.Component;

import br.com.santander.digital.formalization.adapters.in.entity.ContractEntity;
import br.com.santander.digital.formalization.application.core.domain.Contract;

@Component
public class ContractConverter {

	public Contract fromRepositoryEntityToModelCore(ContractEntity contractEntity) {
		return new Contract.Builder()
		.clientId(contractEntity.getClientId())
		.active(contractEntity.getActive())
		.createdDate(contractEntity.getCreatedDate())
		.id(contractEntity.getId())
		.price(contractEntity.getPrice())
		.productId(contractEntity.getProductId()).build();
	}
	
}
