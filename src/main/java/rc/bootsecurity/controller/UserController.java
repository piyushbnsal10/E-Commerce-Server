package rc.bootsecurity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rc.bootsecurity.model.User;
import rc.bootsecurity.service.UserService;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/{id}/name")
    public String getUsername(@PathVariable Long id) {
        return userService.getUsername(id);
    }

    @PostMapping("")
    public User addUser(@RequestBody User user) {
       return userService.addUser(user);
        
    }
    
    @GetMapping("/users")
    public List<User> getAllUser() {
    	List<User> user=userService.getAllUser();
		return user;
    }
    
    @GetMapping("/count")
    public long getUserCount() {
        return userService.getUserCount();
    }
    
    @GetMapping("/{username}/role")
    public String getUserCount(@PathVariable String username) {
        return userService.getUserRole(username);
    }
    
    @GetMapping("/{username}/roleCartWishlist")
    public ResponseEntity<HashMap<String,String>> getRoleCartWishlistCount(@PathVariable String username){
    	HashMap<String,String> item=userService.getRoleCartWishlistCount(username);
    	return new ResponseEntity<>(item,HttpStatus.OK);
    }
    
   
}
