package br.com.santander.product.adapters.in.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductRequestDTO {
	
	@NotBlank(message = "Nome é obrigatório")
    @Size(max = 50, message = "Nome pode ter no maximmo 50 caracteres")
	private String productName;
	
	@NotBlank(message = "Codigo é obrigatório")
    @Size(max = 6, message = "Codigo pode ter no maximmo 6 caracteres")
	private String productCode;
	
	@NotBlank(message = "Descrição é obrigatório")
    @Size(max = 256, message = "Descrição pode ter no maximmo 256 caracteres")
	private String productDescription;
	
	@NotNull(message = "Preço é obrigatório")
	@DecimalMin(value = "0.01", message = "O preço mínimo deve ser maior que 0")
    @DecimalMax(value = "9999.99", message = "O preço máximo deve ser menor que 10000")
	private BigDecimal productPrice;
	
	
}
