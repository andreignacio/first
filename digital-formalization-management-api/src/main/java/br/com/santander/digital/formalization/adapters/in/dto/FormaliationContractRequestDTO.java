package br.com.santander.digital.formalization.adapters.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class FormaliationContractRequestDTO {

	
	@NotBlank(message = "O id do cliente é obrigatório")
	@Size(max = 60, message = "O id do cliente deve ter no máximo 50 caracteres")
	private String clientId;
	@NotBlank(message = "O id do produto é obrigatório")
	@Size(max = 60, message = "O id do produto deve ter no máximo 50 caracteres")
	private String productId;
	
}
