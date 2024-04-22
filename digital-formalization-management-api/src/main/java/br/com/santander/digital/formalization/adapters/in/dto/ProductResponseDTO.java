package br.com.santander.digital.formalization.adapters.in.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductResponseDTO extends RepresentationModel<ProductResponseDTO> {
	
	
	private String productId;
	private String productName;
	private String productCode;
	private String productDescription;
	
	@JsonProperty("expireAt")
    @JsonFormat(pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	private LocalDate productCreatedDate;
	private BigDecimal productPrice;
	


	@Override
    @Hidden
    @NonNull
    public Links getLinks() {
        return super.getLinks();
    }
}
