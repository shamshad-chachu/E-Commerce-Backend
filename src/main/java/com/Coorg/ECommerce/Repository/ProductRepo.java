package com.Coorg.ECommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Coorg.ECommerce.Model.ProductModel;

@Repository
public interface ProductRepo extends JpaRepository<ProductModel,Long> {

	
}
