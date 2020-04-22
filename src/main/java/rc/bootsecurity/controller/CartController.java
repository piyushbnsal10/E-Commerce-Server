package rc.bootsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.bootsecurity.db.ProductRepo;
import rc.bootsecurity.db.UserRepository;
import rc.bootsecurity.model.Product;
import rc.bootsecurity.model.User;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class CartController {
	
	@Autowired
    private UserRepository	userRepository;
	
	@Autowired
	ProductRepo productsRepo;
	
	 @GetMapping("/users/{username}/product")
	    public List<Product> getProduct(@PathVariable String username) {
		 	User user=userRepository.findByUsername(username);
	    	return user.getProducts();
	    }
	    
	    @PostMapping(path="/users/{username}/product/{pId}")
	    public void addProductToCart(@PathVariable String username,@PathVariable Integer pId) {
	    	User user=userRepository.findByUsername(username);
	    	Product product=productsRepo.findById(pId).get();
	    	user.getProducts().add(product);
	    	
	    	userRepository.save(user);
	    	
	    }
	    
	    @DeleteMapping("/users/{username}/product/{pId}")
	    public void deleteProductFromCart(@PathVariable String username,@PathVariable Integer pId) throws Exception {
	    	User user=userRepository.findByUsername(username);
	    	Product product=productsRepo.findById(pId).get();
	    	user.getProducts().remove(product);
	    	
	    	userRepository.save(user);
	    }
}
