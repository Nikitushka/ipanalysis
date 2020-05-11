package com.nikipon.ipanalysis.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignupForm {
    
    private long id;
    
    @NotEmpty
    @Size(min=4, max=20)
    private String username = "";
    
    @NotEmpty
    @Size(min = 4, max = 40)
    private String password = "";
    
    @NotEmpty
    @Size(min = 4, max = 40)
    private String confirmPassword = "";
    
    @NotEmpty
    private String role = "USER";
    
    // regex that checks for a semi-valid email format
    
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email = "";
    

    public String getUsername() {
        return username;
    }

    public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

}