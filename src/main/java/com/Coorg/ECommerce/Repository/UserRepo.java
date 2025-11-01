package com.Coorg.ECommerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Coorg.ECommerce.Model.UserModel;

public interface UserRepo extends JpaRepository<UserModel, Long> {

	// Custom query method to find a user by email
    // Spring Data JPA automatically implements this based on the method name
    Optional<UserModel> findByEmail(String email);
    
    // You could also add findByUsername(String username) or findByMobileNumber(String mobileNumber) 
    // for more robust checking if needed.
}
