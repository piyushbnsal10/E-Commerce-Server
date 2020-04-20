package rc.bootsecurity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rc.bootsecurity.db.UserRepository;
import rc.bootsecurity.model.User;
import rc.bootsecurity.service.UserService;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository	userRepository;

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
}

//cf7587c9-0670-4da8-9fda-b473b2c65f46