package com.Coorg.ECommerce.Model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    
    // Explicitly set column name and NOT NULL constraint for clarity
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "category", nullable = false)
    private String category;
    
    // Ensure the column name matches the database (if needed) and enforce NOT NULL
    @Column(name = "price", nullable = false)
    private double price;
    
    // FIX: Explicitly map the column and enforce NOT NULL, matching the database constraint
    @Column(name = "sellingprice", nullable = false) // Use snake_case if your DB uses it
    private double sellingprice;
    
    @Column(name = "qty", nullable = false)
    private int qty;
    
    @ElementCollection
    @CollectionTable(name= "Product_Images", joinColumns = @JoinColumn(name="product_id"))
    @Column(name = "Image_url", nullable = false)
    private List<String> Img;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(double sellingprice) {
		this.sellingprice = sellingprice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public List<String> getImg() {
		return Img;
	}

	public void setImg(List<String> img) {
		Img = img;
	}

	@Override
	public String toString() {
		return "ProductModel [Id=" + Id + ", name=" + name + ", category=" + category + ", price=" + price
				+ ", sellingprice=" + sellingprice + ", qty=" + qty + ", Img=" + Img + "]";
	}

   
    
   
}