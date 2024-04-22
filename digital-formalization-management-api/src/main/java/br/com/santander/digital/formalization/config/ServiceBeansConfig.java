package br.com.santander.digital.formalization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.santander.digital.formalization.application.core.service.ContractService;
import br.com.santander.digital.formalization.application.ports.out.ContractPort;

@Configuration
public class ServiceBeansConfig {
	
	@Bean
	public ContractService generateContractService(ContractPort contractPort) {
		return new ContractService(contractPort);
	}
	
}
