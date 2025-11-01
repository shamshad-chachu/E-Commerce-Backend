package com.Coorg.ECommerce.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList; // Added for safety

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Coorg.ECommerce.DTO.ProductRequestDTO;
import com.Coorg.ECommerce.Model.ProductModel;
import com.Coorg.ECommerce.Repository.ProductRepo;

@Service
public class ProductService {
	ProductRepo repo;
	
	public ProductService(ProductRepo repo) {
		this.repo = repo;
	}
	

	public List<ProductModel> getAll() {
		System.out.println("in service");
		return repo.findAll();
	}


	public ProductModel AddProd(ProductRequestDTO productDto) {
        
        System.out.println("In service: Processing new product and " + productDto.getImgFiles().size() + " files.");

        // 1. --- FILE UPLOAD LOGIC ---
        // Ensure imageUrls is a non-null List, even if empty.
        List<String> imageUrls = productDto.getImgFiles().stream()
            .filter(file -> file != null && !file.isEmpty())
            .map(this::uploadFileAndGetUrl) 
            .collect(Collectors.toList());

        // 2. --- DTO to Entity Conversion with NULL CHECKS ---
        ProductModel product = new ProductModel();
        
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        
        // CRITICAL FIX: NULL CHECKS for primitives
        // Ensures DTO's Double/Integer fields, if null, are converted to 0.0 or 0
        // to satisfy the primitive fields in ProductModel and the DB's NOT NULL constraints.
        product.setPrice(productDto.getPrice() != null ? productDto.getPrice() : 0.0);
        product.setSellingprice(productDto.getSellingprice() != null ? productDto.getSellingprice() : 0.0);
        
        // ⚠️ Correction: Use the setter name defined in ProductModel (setQTY or setQty)
        // Assuming your ProductModel uses setQTY(int qTY) or setQty(int qty) based on the previous code.
        product.setQty(productDto.getQty() != null ? productDto.getQty() : 0);
        
        // ⚠️ Correction: Use the setter name defined in ProductModel (setImage or setImg)
        // Assuming your ProductModel uses setImage(List<String> Img) or setImg(List<String> img)
        // Based on the failing row detail, using `setImg` seems safer here if `Img` is the model field.
        product.setImg(imageUrls); 
        System.out.println(product.getImg());
        System.out.println(product);

        // 3. --- PERSISTENCE ---
        return repo.save(product);
        
    }
    
    // Placeholder method to simulate file upload and URL generation
    private String uploadFileAndGetUrl(MultipartFile file) {
//        String mockUrl = "http://storage-server/images/" + file.getOriginalFilename();
    	 String mockUrl =  file.getOriginalFilename();
        System.out.println("Mock Upload: " + file.getOriginalFilename() + " -> " + mockUrl);
        return mockUrl;
    }


	public ProductModel UpadteProd(long id, ProductModel newData) {
	    // ... (logic remains the same)
	    ProductModel existingProduct = repo.findById(id).orElseThrow();
	    
	    existingProduct.setName(newData.getName());
	    existingProduct.setPrice(newData.getPrice());
	    
	    ProductModel savedProduct = repo.save(existingProduct);
	    
	    System.out.println("Product with ID " + id + " updated successfully.");
	    
	    return savedProduct;
	}


	public ResponseEntity<?> DeleteProd(long id) {
		System.out.println("deleted prod");
		repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("deleted");
	}


	public Optional<ProductModel> getOneProd(long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
}