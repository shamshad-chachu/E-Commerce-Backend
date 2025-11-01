package com.Coorg.ECommerce.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Coorg.ECommerce.Model.UserModel;
import com.Coorg.ECommerce.Repository.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ResponseEntity<?> Register(UserModel data) {
        
        if (userRepo.findByEmail(data.getEmail()).isPresent()) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Registration failed: User with this email already exists.");
            return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT); // HTTP 409
        }
        
        try {
            UserModel savedUser = userRepo.save(data);
            
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("success", true);
            responseBody.put("message", "User registered successfully!");
            responseBody.put("userId", savedUser.getId());
            // RETURN USERNAME on registration success
            responseBody.put("username", savedUser.getUsername()); 
            responseBody.put("email", savedUser.getEmail());
            
            return new ResponseEntity<>(responseBody, HttpStatus.CREATED); // HTTP 201
            
        } catch (Exception e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "An internal error occurred during registration.");
            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500
        }
    }

    	
    public ResponseEntity<?> Login(UserModel data) {
        
        Optional<UserModel> userOptional = userRepo.findByEmail(data.getEmail());
        Map<String, Object> responseBody = new HashMap<>();
        
        if (userOptional.isEmpty()) {
            responseBody.put("message", "Login failed: Invalid email or password.");
            return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
        }
        
        UserModel user = userOptional.get();
        
        // **Password Check**
        if (!user.getPassword().equals(data.getPassword())) {
            responseBody.put("message", "Login failed: Invalid email or password.");
            return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
        }
        
        // Successful login: Return the required data
        responseBody.put("message", "Login successful!");
        responseBody.put("success", true);
        responseBody.put("userId", String.valueOf(user.getId()));
        
        // CORRECTED: Use the standard getter
        responseBody.put("username", user.getUsername()); 
        responseBody.put("email", user.getEmail());       
        
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public List<UserModel> getAll() {
        return userRepo.findAll();
    }
}