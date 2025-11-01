package com.Coorg.ECommerce.Controller;

import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Coorg.ECommerce.DTO.ProductRequestDTO;
import com.Coorg.ECommerce.Model.ProductModel;
import com.Coorg.ECommerce.Service.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/Product")
@CrossOrigin()
public class ProductController {
	ProductService service;
	
	public ProductController(ProductService service) {
		// TODO Auto-generated constructor stub
		this.service = service;
	}
	
	@GetMapping("/")
	public List<ProductModel> getAll() {
		System.out.println("All Items");
		List<ProductModel> data = service.getAll();
		System.out.println(data);
		return data;
	}
	@GetMapping("/{id}")
	public Optional<ProductModel> getOneProd(@PathVariable long id) {
		System.out.println("fetching one prod");
		return service.getOneProd(id);
	}
	
//	@PostMapping("/AddProduct")
//	public ResponseEntity<?> AddProduct(@RequestBody ProductModel data){
//		ProductModel result = service.AddProd(data);
//		return ResponseEntity
//	            .status(HttpStatus.CREATED) // Sets the HTTP status code to 201 CREATED
//	            .body(result);
//	}
	@PostMapping("/AddProduct")
	public ResponseEntity<ProductModel> AddProduct(@ModelAttribute ProductRequestDTO productDto) { 
	    
	    // The service method (service.AddProd) handles:
	    // 1. Uploading productDto.getImgFiles()
	    // 2. Generating image URLs
	    // 3. Creating and saving the final ProductModel
	    ProductModel result = service.AddProd(productDto); 
	    
	    return ResponseEntity
	            .status(HttpStatus.CREATED) // HTTP 201
	            .body(result);
	}
	
	@PutMapping("/Update/{id}")
	public ResponseEntity<?> updateProd(@PathVariable long id,@RequestBody ProductModel data){
		System.out.println("in update "+ id);
			ProductModel updatedvalue = service.UpadteProd(id,data);
		return ResponseEntity.ok(updatedvalue);
		
	}
	
	@DeleteMapping("/Delete/{id}")
	public ResponseEntity<?> DeleteProd(@PathVariable long id){
		
		
		return service.DeleteProd(id);
	}
	
}
