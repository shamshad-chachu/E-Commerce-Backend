package com.Coorg.ECommerce.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data // Lombok annotation for getters, setters, toString, equals, and hashCode
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    // Link back to the main Order entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Order order; 

    // Product Details from the Frontend Cart Item
    private Long productId; // The ID of the product purchased
    private String name; 
    private String category;
    
    // Price at the time of order (critical for integrity)
    private Double price; 
    
    private Integer qty;
    
    // The calculated total for this line item (price * qty)
    private Double lineTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(Double lineTotal) {
		this.lineTotal = lineTotal;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", order=" + order + ", productId=" + productId + ", name=" + name
				+ ", category=" + category + ", price=" + price + ", qty=" + qty + ", lineTotal=" + lineTotal + "]";
	} 

    
}
