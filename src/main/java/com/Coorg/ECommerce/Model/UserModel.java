package com.Coorg.ECommerce.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Users")
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    
    // ADDED: The username field
    @Column(name = "username", nullable = false)
    private String username;
	
	@Column(name = "email", nullable = false, unique = true) // Added unique constraint for email
    private String email;
	
	@Column(name = "password", nullable = false)
    private String password;
	
    // Changed column name for best practice (snake_case)
	@Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}
    
    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "UserModel [Id=" + Id + ", username=" + username + ", email=" + email + ", password=" + password + ", mobileNumber=" + mobileNumber
				+ "]";
	}
}