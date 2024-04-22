package br.com.santander.digital.formalization.adapters.in.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.santander.digital.formalization.adapters.in.dto.FormaliationContractRequestDTO;
import br.com.santander.digital.formalization.application.core.domain.Contract;
import br.com.santander.digital.formalization.application.ports.in.ContractServicePort;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/digital/formalization")
@AllArgsConstructor
public class FormalizationContractController {
	
	private final ContractServicePort contractServicePort;
	
		
	@PostMapping("/contracts")
    @Operation(summary = "Generate a contract for client")
    public ResponseEntity<Contract> formalizationContract(@Validated @RequestBody FormaliationContractRequestDTO requestDTO) {
		
		Contract savedContract = contractServicePort.saveContract(requestDTO.getClientId(), requestDTO.getProductId());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedContract);
		
    }

  

}
