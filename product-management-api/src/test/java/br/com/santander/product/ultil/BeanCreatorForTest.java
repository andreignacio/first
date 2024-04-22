package br.com.santander.product.ultil;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.santander.product.adapters.in.dto.ProductRequestDTO;
import br.com.santander.product.adapters.in.dto.ProductResponseDTO;
import br.com.santander.product.adapters.in.dto.ProductSimulationResponseDTO;
import br.com.santander.product.adapters.in.entity.ProductEntity;
import br.com.santander.product.application.core.domain.Product;

public class BeanCreatorForTest {
	
	
	public static ProductRequestDTO getRequestBean() {
		
		return ProductRequestDTO.builder()
			.productCode("01")
			.productDescription("Cartão Visa Ouro")
			.productName("Cartão de Credito")
			.productPrice(new BigDecimal(130))
			.build();
		
	}
	
	
	public static ProductEntity getProductEntity() {
		return ProductEntity.builder()
			.code("01")
			.description("Cartão Visa Ouro")
			.id("6623edd9aabc741c9d47ed42")
			.name("Cartão de Credito")
			.code("CARD")
			.createdDate(LocalDate.now())
			.price(new BigDecimal(130))
			.build();
	} 
	
	
	public static Product getProduct() {
		return new Product.Builder()
			.code("01")
			.description("Cartão Visa Ouro")
			.id("6623edd9aabc741c9d47ed42")
			.name("Cartão de Credito")
			.createdDate(LocalDate.now())
			.price(new BigDecimal(130))
			.build();
	} 
	
	public static ProductResponseDTO getResponseBean() {
		
		return ProductResponseDTO.builder()
				.productCode("01")
				.productDescription("Cartão Visa Ouro")
				.productId("6623edd9aabc741c9d47ed42")
				.productName("Cartão de Credito")
				.productCreatedDate(LocalDate.now())
				.productPrice(new BigDecimal(130))
				.build();
		
	}
	
	public static ProductSimulationResponseDTO getProductSimulationResponseDTO() {
		return ProductSimulationResponseDTO.builder()
				.productDescription("Cartão Visa Ouro")
				.productName("Cartão de Credito")
				.productPrice(new BigDecimal(130))
				.build();
	}
	
	
}
