package br.com.santander.digital.formalization.util;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.santander.digital.formalization.adapters.in.dto.ClientResponseDTO;
import br.com.santander.digital.formalization.adapters.in.dto.FormaliationContractRequestDTO;
import br.com.santander.digital.formalization.adapters.in.dto.ProductResponseDTO;
import br.com.santander.digital.formalization.adapters.in.entity.ContractEntity;
import br.com.santander.digital.formalization.application.core.domain.Contract;




public class BeanCreatorForTest {
	
	public static  FormaliationContractRequestDTO getRequestDTO() {
		return FormaliationContractRequestDTO.builder()
    			.clientId("6623edd9aabc741c9d47ed42")
    			.productId("6623edd9aabc741c9d47ed42")
    			.build();
	}

	public static ClientResponseDTO getClientResponseDTO() {
		
		return ClientResponseDTO.builder()
				.clientName("André Luis Ignacio")
				.clientId("6623edd9aabc741c9d47ed42")
				.clientBirthDate(LocalDate.now())
				.clientDocument("01234567890")
				.clientEmail("andre@gmail.com")
				.clientTelephone("11988665500")
				.build();
	}
	
	public static ProductResponseDTO getProductResponseDTO() {
		
		return ProductResponseDTO.builder()
				.productCode("01")
				.productDescription("Cartão Visa Ouro")
				.productId("6623edd9aabc741c9d47ed42")
				.productName("Cartão de Credito")
				.productCreatedDate(LocalDate.now())
				.productPrice(new BigDecimal(130))
				.build();
		
	}
	
	public static ContractEntity getContractEntity() {
		return ContractEntity.builder()
				.active(true)
				.clientId("6623edd9aabc741c9d47ed42")
				.createdDate(LocalDate.now())
				.id("6623edd9aabc741c9d47ed42")
				.price(new BigDecimal(130))
				.productId("6623edd9aabc741c9d47ed42")
				.build();
	}
	
	
	public static Contract getContract() {
		return new Contract.Builder()
				.active(true)
				.clientId("6623edd9aabc741c9d47ed42")
				.createdDate(LocalDate.now())
				.id("6623edd9aabc741c9d47ed42")
				.price(new BigDecimal(130))
				.productId("6623edd9aabc741c9d47ed42")
				.build();
	}
	
	
	
	
}
