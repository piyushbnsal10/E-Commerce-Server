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
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository	userRepository;
    
    @Autowired
	ProductRepo productsRepo;

    @GetMapping("/{id}/name")
    public String getUsername(@PathVariable Long id) {
        return userService.getUsername(id);
    }

    @PostMapping("")
    public User addUser(@RequestBody User user) {
    	System.out.println(user);
       return userService.addUser(user);
        
    }
    
    @GetMapping("/users")
    public List<User> getAllUser() {
    	List<User> user=userRepository.findAll();
		return user;
    }
    
    @GetMapping("/count")
    public long getUserCount() {
        return userService.getUserCount();
    }
    
    @GetMapping("/{username}/role")
    public String getUserCount(@PathVariable String username) {
    	User user=userRepository.findByUsername(username);
        return user.getRole();
    }
    
   
}
