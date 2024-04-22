package br.com.santander.digital.formalization.application.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Contract {
	
	private String id;
	private String clientId;
	private String productId;
	private LocalDate createdDate;
	private BigDecimal price;
	private Boolean active;
	

	public Contract(Builder builder) {
		super();
		this.id = builder.id;
		this.setClientId(builder.clientId);
		this.setProductId(builder.productId);
		this.setCreatedDate(builder.createdDate);
		this.setPrice(builder.price);
		this.setActive(builder.active); 
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}



	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
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
		
		public Builder productId(String value) {
			productId = value;
			return this;
		}
		
		public Builder clientId(String value) {
			clientId = value;
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
		
		public Builder active(Boolean value) {
			active = value;
			return this;
		}
		
		public Contract build() {
            return new Contract(this);
        }
		
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, clientId, createdDate, id, price, productId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		return Objects.equals(active, other.active) && Objects.equals(clientId, other.clientId)
				&& Objects.equals(createdDate, other.createdDate) && Objects.equals(id, other.id)
				&& Objects.equals(price, other.price) && Objects.equals(productId, other.productId);
	}
	
	



	


}
