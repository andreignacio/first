package br.com.santander.product.adapters.in.dto;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductSimulationResponseDTO extends RepresentationModel<ProductSimulationResponseDTO> {
	
	
	private String productName;
	private String productDescription;
	private BigDecimal productPrice;
	
}
