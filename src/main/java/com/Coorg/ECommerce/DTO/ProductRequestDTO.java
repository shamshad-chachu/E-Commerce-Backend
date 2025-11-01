package com.Coorg.ECommerce.DTO;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

import java.util.List;

// This class will receive the FormData sent by the React frontend
// Generates getters, setters, toString, equals, and hashCode
public class ProductRequestDTO {
	
    private String name;
    private String category;
    

    private Double price; 
    
    private Double sellingprice; 
    
    private Integer qty; 
    
    // This MUST match the key used in the React FormData.append('imgFiles', file)
    private List<MultipartFile> imgFiles;

    // --- Recommended Clean Up ---
    // Remove the manual getters and setters below if you use @Data, 
    // as they are redundant and conflict with the Wrapper type fix above. 
    
    /* public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; } // Will now clash if this is here!
    // ... all other manual methods
    */
    
    // Keeping only the unique methods (though @Data handles all text fields too)
    
    public List<MultipartFile> getImgFiles() {
        return imgFiles;
    }
    
    public void setImgFiles(List<MultipartFile> imgFiles) {
        this.imgFiles = imgFiles;
        
        
    }

	@Override
	public String toString() {
		return "ProductRequestDTO [name=" + name + ", category=" + category + ", price=" + price + ", sellingprice="
				+ sellingprice + ", qty=" + qty + ", imgFiles=" + imgFiles + "]";
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

	public Double getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(Double sellingprice) {
		this.sellingprice = sellingprice;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
}