package rc.bootsecurity.service;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import rc.bootsecurity.db.UserRepository;
import rc.bootsecurity.model.User;


@Service
public class UserService {
	
    private PasswordService passwordService;
    private UserRepository userRepository;
    
    @Autowired
    private CartService cartService;
    @Autowired
    private WishlistService wishlistService;

    @Autowired
    public UserService(PasswordService passwordService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public User getUser(Long id) throws Exception {
        Optional<User> userOptional =  userRepository.findById(id);

        if(!userOptional.isPresent()) {
            throw new Exception("User not found");
        }
        return userOptional.get();
    }

    public User addUser(User user) {
        user.setPassword(passwordService.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public String getUsername(Long id) {
        User user = userRepository.findById(id).get();
        return user.getUsername();
    }

    public long getUserCount() {
        return userRepository.count();
    }
    
    public User getUserByUsername(String username) {
    	User user =  userRepository.findByUsername(username);       
        return user;
    }
    
    public List<User> getAllUser() {
    	List<User> user=userRepository.findAll();
		return user;
    }
    
    public String getUserRole(String username) {
    	User user=userRepository.findByUsername(username);
        return user.getRole();
    }
    
    public HashMap<String,String> getRoleCartWishlistCount(@PathVariable String username) {
    	HashMap<String,String> data= new HashMap<String,String>();
    	User user=userRepository.findByUsername(username);
    	data.put("role", user.getRole());
    	data.put("cartCount", cartService.CartCount(username)+"");
    	data.put("wishlistCount", wishlistService.wishlistCount(username)+"");
    	return data;
    }
}
