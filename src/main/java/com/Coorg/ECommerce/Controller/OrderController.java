package com.Coorg.ECommerce.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Coorg.ECommerce.Model.Order;
import com.Coorg.ECommerce.Service.OrderService;

@RestController
@RequestMapping("/Order")
@CrossOrigin()
public class OrderController {
	
	OrderService service;
	
	public OrderController(OrderService service) {
		// TODO Auto-generated constructor stub
		this.service = service;
	}

	@GetMapping("/")
	public List<Order> getAll(){
		System.out.println("in all get orderd");
		
		return service.getAll();
	}
	
	@PostMapping("/PlaceOrder")
	public ResponseEntity<?> PlacedOrder(@RequestBody Order data){
		System.out.println(data);
		System.out.println("in post orderd");
		
		
		
		return service.PalecedOrder(data);
	}
}
