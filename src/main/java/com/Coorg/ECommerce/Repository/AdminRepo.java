package com.Coorg.ECommerce.Repository;

//package com.Coorg.ECommerce.Repository; (or appropriate package)

import com.Coorg.ECommerce.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

 // Custom method to find an Admin by their email for login check
 Optional<Admin> findByEmail(String email);
}