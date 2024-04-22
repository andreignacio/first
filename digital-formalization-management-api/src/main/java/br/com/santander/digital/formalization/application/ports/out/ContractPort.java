package br.com.santander.digital.formalization.application.ports.out;

import br.com.santander.digital.formalization.application.core.domain.Contract;

public interface ContractPort {
	
	
	Contract saveContract(String clienId, String productId);
	
	

}
