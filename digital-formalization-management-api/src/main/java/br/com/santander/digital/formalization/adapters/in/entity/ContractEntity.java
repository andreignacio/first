package br.com.santander.digital.formalization.adapters.in.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "contract")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractEntity {

    @Id
    private String id;
	private String clientId;
	private String productId;
	private LocalDate createdDate;
	private BigDecimal price;
	private Boolean active;
	
	public ContractEntity(Builder builder) {
		this.id = builder.id;
		this.clientId = builder.clientId;
		this.productId = builder.productId;
		this.active = builder.active;
		this.setCreatedDate(builder.createdDate);
		this.setPrice(builder.price);
	}
	
	public static class Builder {
		
		private String id;
		private String clientId;
		private String productId;
		private LocalDate createdDate;
		private BigDecimal price;
		private Boolean active;
		
		public Builder id(String value) {
			id = value;
			return this;
		}
		
		public Builder clientId(String value) {
			clientId = value;
			return this;
		}
		
		public Builder productId(String value) {
			productId = value;
			return this;
		}
		
		public Builder active(Boolean value) {
			active = value;
			return this;
		}
		
		public Builder createdDate(LocalDate value) {
			createdDate = value;
			return this;
		}
		
		public Builder price(BigDecimal value) {
			price = value;
			return this;
		}
		
		public ContractEntity build() {
            return new ContractEntity(this);
        }
		
		
	}

	
}
