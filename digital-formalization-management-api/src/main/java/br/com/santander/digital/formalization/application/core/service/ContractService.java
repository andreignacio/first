package br.com.santander.digital.formalization.application.core.service;

import br.com.santander.digital.formalization.application.core.domain.Contract;
import br.com.santander.digital.formalization.application.ports.in.ContractServicePort;
import br.com.santander.digital.formalization.application.ports.out.ContractPort;

public class ContractService implements ContractServicePort {

	private final ContractPort contractPort;
	
	public ContractService(ContractPort contracttPort) {
		this.contractPort = contracttPort;
	}
	
	public Contract saveContract(String clienId, String productId) {		
		
		Contract contract = contractPort.saveContract(clienId, productId);		
		return contract;
	}

	

}
