package rc.bootsecurity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rc.bootsecurity.db.ProductRepo;
import rc.bootsecurity.db.UserRepository;
import rc.bootsecurity.model.LoginViewModel;
import rc.bootsecurity.model.Product;
import rc.bootsecurity.model.User;
import rc.bootsecurity.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository	userRepository;
    
    @Autowired
	ProductRepo productsRepo;

    @GetMapping("/users/{id}/name")
    public String getUsername(@PathVariable Long id) {
        return userService.getUsername(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
    	System.out.println(user);
       return userService.addUser(user);
        
    }
    
    @GetMapping("/users")
    public List<User> getAllUser() {
    	List<User> user=userRepository.findAll();
		return user;
    }
    
    @GetMapping("/users/count")
    public long getUserCount() {
        return userService.getUserCount();
    }
    
    @PostMapping("/login") 
    public void login(@RequestBody LoginViewModel model,HttpServletRequest request,HttpServletResponse response) throws IOException {
    	User user=userService.getUserByUsername(model.getUsername());
    	System.out.println(request);
    	response.getWriter().print(user.getId());
    	
    }
    
   
}

//cf7587c9-0670-4da8-9fda-b473b2c65f46