package com.Coorg.ECommerce.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Coorg.ECommerce.Model.UserModel;
import com.Coorg.ECommerce.Service.UserService;

@RestController
@RequestMapping("/User")
@CrossOrigin()
public class UserController {
	 
    // Use final for service to ensure immutability
	private final UserService service ;
	
	// Constructor Injection
	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping("/Register")
	public ResponseEntity<?> Register(@RequestBody UserModel data){
		System.out.println(data);
		System.out.println("in Registory");
		
		return service.Register(data);
	}
	
	@PostMapping("/Login")
	public ResponseEntity<?> Login(@RequestBody UserModel data){
		System.out.println(data);
		System.out.println("in Login");
		
		return service.Login(data);
	}
    
	@GetMapping("/getAll")
	public List<UserModel> GetAll(){
		
		return service.getAll();
	}
}