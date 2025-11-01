package com.Coorg.ECommerce.Controller;

import com.Coorg.ECommerce.Model.Admin;

//package com.Coorg.ECommerce.Controller; (or appropriate package)

import com.Coorg.ECommerce.Service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Admin")
@CrossOrigin() 
public class AdminController {

 private final AdminService adminService;

 public AdminController(AdminService adminService) {
     this.adminService = adminService;
 }

 @PostMapping("/Register")
 public Admin createAdmin( @RequestBody Admin data) {	 
	 return adminService.Create(data);
 }
 
 @GetMapping("/")
 public List<Admin> getAll(){
	 
	 return adminService.getAll();
 }
 @PostMapping("/Login")
 public ResponseEntity<Map<String, Object>> loginAdmin(@RequestBody Map<String, String> loginData) {
	 System.out.println("in login");
	 System.out.println( loginData);
     Map<String, Object> result = adminService.authenticateAdmin(loginData);

     if ((Boolean) result.get("success")) {
         // Return 200 OK with admin details
         return ResponseEntity.ok(result);
     } else {
         // Return 401 Unauthorized or 400 Bad Request
         return ResponseEntity.status(401).body(result);
     }
 }
 
}
