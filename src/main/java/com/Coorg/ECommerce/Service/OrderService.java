package com.Coorg.ECommerce.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Coorg.ECommerce.Model.Order;
import com.Coorg.ECommerce.Model.OrderItem;
import com.Coorg.ECommerce.Repository.OrderRepo;

@Service
public class OrderService {
	
	private final OrderRepo repo; // Use final for constructor injection

    // 💡 Fix: Use proper constructor injection for the repository
	public OrderService(OrderRepo repo) {
		this.repo = repo;
	}

	
	public List<Order> getAll(){
		
		return repo.findAll();
	}
	
	// 🚀 CORE LOGIC IMPLEMENTATION
	
public ResponseEntity<?> PalecedOrder(Order data){
		
        try {
            // 1. Set the bi-directional link for all OrderItems (Crucial for Cascade)
            if (data.getCart() != null) {
                for (OrderItem item : data.getCart()) {
                    item.setOrder(data); 
                }
            } else {
                return new ResponseEntity<>("Order contains no items.", HttpStatus.BAD_REQUEST);
            }

            // 2. Save the parent Order entity
            Order savedOrder = repo.save(data); 
            
            // 3. Return a simple success response, including only the Order ID
            // 🚀 REVISED RETURN LOGIC HERE
            
            // Create a simple Map to send back the necessary data (Order ID)
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Order placed successfully.");
            // Send back the ID so the frontend knows the transaction succeeded
            response.put("orderId", savedOrder.getId()); 
            
            // Return the Map, which Spring converts to a clean JSON object
            return new ResponseEntity<>(response, HttpStatus.CREATED);
            
        } catch (Exception e) {
            System.err.println("Error processing placed order: " + e.getMessage());
            // Return an error response map
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to place order: " + e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}