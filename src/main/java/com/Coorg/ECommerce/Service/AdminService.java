package com.Coorg.ECommerce.Service;

//package com.Coorg.ECommerce.Service; (or appropriate package)

import com.Coorg.ECommerce.Model.Admin;
import com.Coorg.ECommerce.Repository.AdminRepo;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

 private final AdminRepo adminRepo;

 public AdminService(AdminRepo adminRepo) {
     this.adminRepo = adminRepo;
 }
 
// @PostConstruct
// public void initAdminData() {
//     // Check if an admin with a known dummy email already exists
////     if (adminRepo.findByEmail("admin@test.com").isEmpty()) {
////         System.out.println("No initial admin found. Creating dummy admin user.");
////         
////         Admin dummyAdmin = new Admin();
////         dummyAdmin.setUsername("ADMIN");
////         dummyAdmin.setEmail("admin@test.com");
////         // WARNING: Storing plain text password for development simplicity only.
////         // Replace "password123" with a BCrypt hash in production!
////         dummyAdmin.setPassword("12345"); 
////         dummyAdmin.setRole("admin"); 
////
////         // Save the dummy admin to the database
////         adminRepo.save(dummyAdmin);
//         System.out.println("✅ Dummy Admin created: admin@test.com / password123");
//     }


 
 public Map<String, Object> authenticateAdmin(Map<String, String> loginData) {
     Map<String, Object> response = new HashMap<>();
     String email = loginData.get("email");
     String password = loginData.get("password");

     // 1. Find the Admin by email
     Admin admin = adminRepo.findByEmail(email).orElse(null);

     if (admin == null) {
         response.put("success", false);
         response.put("message", "Admin not found.");
         return response;
     }

     // 2. Validate the password (MOCK IMPLEMENTATION)
     // NOTE: Replace the simple equality check with a secure BCrypt check in production!
     if (admin.getPassword().equals(password)) { 
         response.put("success", true);
         response.put("message", "Login successful.");
         response.put("adminId", admin.getId());
         response.put("username", admin.getUsername());
         response.put("email", admin.getEmail());
         // In a real app, you would generate and return a JWT/token here
     } else {
         response.put("success", false);
         response.put("message", "Invalid credentials.");
     }

     return response;
 }

 public Admin Create(Admin data) {
	return adminRepo.save(data);
 }
 
 public List<Admin> getAll(){
	 return adminRepo.findAll();
 }
 
 // Add other Admin service methods here (e.g., create, update, manage products)
}