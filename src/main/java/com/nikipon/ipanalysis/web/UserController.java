package com.nikipon.ipanalysis.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nikipon.ipanalysis.domain.Client;
import com.nikipon.ipanalysis.domain.ClientRepository;
import com.nikipon.ipanalysis.domain.SignupForm;

@Controller
public class UserController {
	@Autowired
	private ClientRepository urepo;
	
	// display register page
	@GetMapping("register")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "register";
	}
	
    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "registeruser")
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getConfirmPassword())) { // check password match
	    		String passwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPasswd = bc.encode(passwd);
            	
		    	if (signupForm.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	    		} else {
	          		bindingResult.rejectValue("email", "err.email", "Incorrect email format");
	        		return "register";
	    		}
	
		    	Client newUser = new Client();
		    	newUser.setPasswordHash(hashPasswd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setEmail(signupForm.getEmail());
		    	newUser.setRole("USER");
		    	if (urepo.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		urepo.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "register";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("confirmPassword", "err.confirmPassword", "Passwords do not match");    	
    			return "register";
    		}
    	}
    	 else {
    		return "register";
    	}
    	return "redirect:/login";    	
    }    
    
}
	
