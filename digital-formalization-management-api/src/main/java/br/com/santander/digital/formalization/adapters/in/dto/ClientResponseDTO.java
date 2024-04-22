package br.com.santander.digital.formalization.adapters.in.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientResponseDTO {
	
	private String clientId;
	private String clientDocument;
	private String clientName;
	private String clientEmail;
	private String clientTelephone;
	@JsonProperty("expireAt")
    @JsonFormat(pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class) 
	private LocalDate clientBirthDate;
	
	
	
}
