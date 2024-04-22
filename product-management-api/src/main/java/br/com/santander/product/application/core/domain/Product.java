package br.com.santander.product.application.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Product {
	
	private String id;
	private String name;
	private String code;
	private String description;
	private LocalDate createdDate;
	private BigDecimal price;
	

	public Product(Builder builder) {
		super();
		this.id = builder.id;
		this.name = builder.name;
		this.code = builder.code;
		this.description = builder.description;
		this.setCreatedDate(builder.createdDate);
		this.setPrice(builder.price);
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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



	public static class Builder {
		
		private String id;
		private String name;
		private String code;
		private String description;
		private LocalDate createdDate;
		private BigDecimal price;
		
		public Builder id(String value) {
			id = value;
			return this;
		}
		
		public Builder name(String value) {
			name = value;
			return this;
		}
		
		public Builder code(String value) {
			code = value;
			return this;
		}
		
		public Builder description(String value) {
			description = value;
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
		
		public Product build() {
            return new Product(this);
        }
		
		
	}



	@Override
	public int hashCode() {
		return Objects.hash(code, createdDate, description, id, name, price);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(code, other.code) && Objects.equals(createdDate, other.createdDate)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price);
	}

	
}
