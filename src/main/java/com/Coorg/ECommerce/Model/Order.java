package com.Coorg.ECommerce.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
@Data // Lombok annotation for getters, setters, toString, etc.
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User Information
    // NOTE: userId is a string in your payload, so we use String, but Long is often preferred in Java
    private String userId; 
    private String username; 

    // Order Details
    // Total amount for the entire order
    private Double totalAmount; 
    
    // Status can be set to PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    private String status = "PENDING"; 

    // Timestamp for when the order was placed
    // Instant is ideal for storing ISO 8601 timestamps like '2025-11-01T09:19:11.195Z'
    private Instant orderDate; 

    // Relationship to OrderItem (Cart Items)
    // One Order has Many OrderItems
    // Cascade.ALL ensures that if an Order is deleted, its items are deleted too.
    // orphanRemoval = true helps clean up OrderItems if they are removed from the list.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> cart; // Match the name in your incoming JSON payload

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Instant getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Instant orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderItem> getCart() {
		return cart;
	}

	public void setCart(List<OrderItem> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", username=" + username + ", totalAmount=" + totalAmount
				+ ", status=" + status + ", orderDate=" + orderDate + ", cart=" + cart + "]";
	}

   
}
