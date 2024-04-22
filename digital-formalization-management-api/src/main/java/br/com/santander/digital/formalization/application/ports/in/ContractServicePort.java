package br.com.santander.digital.formalization.application.ports.in;

import br.com.santander.digital.formalization.application.core.domain.Contract;

public interface ContractServicePort {
	
	Contract saveContract(String clienId, String productId);
	
	

}
