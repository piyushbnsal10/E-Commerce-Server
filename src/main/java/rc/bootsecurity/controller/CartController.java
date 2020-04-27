package rc.bootsecurity.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.bootsecurity.db.CartRepo;
import rc.bootsecurity.db.ProductRepo;
import rc.bootsecurity.db.UserRepository;
import rc.bootsecurity.model.Cart;
import rc.bootsecurity.model.Product;
import rc.bootsecurity.model.User;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="/users/{username}/cart")
public class CartController {
	
	@Autowired
    private UserRepository	userRepository;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	 @GetMapping("")
	    public List<Cart> getProduct(@PathVariable String username) {
		 	User user=userRepository.findByUsername(username);
	    	return user.getCarts();
	    }
	    
	    @PostMapping(path="/{pId}")
	    public void addProductToCart(@PathVariable String username,@PathVariable Integer pId) throws Exception {
	    	User user=userRepository.findByUsername(username);
	    	Product product=productRepo.findById(pId).get();
	    	
	    	if(user.getProductFromCart(product.getpId())!=null) {
	    		
	    		Cart userProduct=user.getProductFromCart(product.getpId());
	    		int quantity=userProduct.getQuantity();
	    		
	    		if(product.getQuantity()<1)
	    			throw new Exception("Product Quantity is less !!");
	    		
	    		product.setQuantity(product.getQuantity()-1);
	    		userProduct.setQuantity(quantity+1);
	    		
	    		cartRepo.save(userProduct);
	    		
	    	} else {
	    		Cart userProduct=new Cart();
	    		userProduct.setpId(product.getpId());
	    		userProduct.setCategory(product.getCategory());
	    		userProduct.setDescp(product.getDescp());
	    		userProduct.setImgUrl(product.getImgUrl());
	    		userProduct.setTitle(product.getTitle());
	    		userProduct.setPrice(product.getPrice());
	    		userProduct.setQuantity(1);
	    		
	    		product.setQuantity(product.getQuantity()-1);
	    	
	    		cartRepo.save(userProduct);
	    		user.getCarts().add(userProduct);
	    	}
	    	
	    	userRepository.save(user);
	    	
	    }
	    
	    @DeleteMapping("/{cId}")
	    public void deleteProductFromCart(@PathVariable String username,@PathVariable Integer cId) throws Exception {
	    	User user=userRepository.findByUsername(username);
	    	Cart userProduct=cartRepo.findById(cId).get();
	    	Product product=productRepo.findById(userProduct.getpId()).get();
	    	product.setQuantity(product.getQuantity()+userProduct.getQuantity());
	    	user.getCarts().remove(userProduct);
	    	cartRepo.delete(userProduct);
	    	productRepo.save(product);
	    	
	    }
	    
	    @PostMapping("/{pId}/edit")
	    public void editCartProductQuantity(@PathVariable String username,@PathVariable Integer pId,@RequestBody HashMap<String,Integer> edit) throws Exception {
	    	User user=userRepository.findByUsername(username);
	    	Product product=productRepo.findById(pId).get();
	    	
	    	if(edit.containsKey("add")) {
	    		
	    		if(product.getQuantity()<edit.get("add"))
	    			throw new Exception("Sufficient quantity is not available");
	    		
	    		Cart userProduct=user.getProductFromCart(product.getpId());
	    		product.setQuantity(product.getQuantity()-edit.get("add"));
	    		userProduct.setQuantity(userProduct.getQuantity()+edit.get("add"));
	    		
	    		cartRepo.save(userProduct);
	    		
	    	}
	    	
	    	else if(edit.containsKey("sub")) {
	    		
	    		Cart userProduct=user.getProductFromCart(product.getpId());
	    		
	    		if(userProduct.getQuantity()<edit.get("sub"))
	    			throw new Exception("Sufficient quantity is not available in the cart");
	    		
	    		product.setQuantity(product.getQuantity()+edit.get("sub"));
	    		userProduct.setQuantity(userProduct.getQuantity()-edit.get("sub"));
	    		
	    		cartRepo.save(userProduct);
	    	}
	    	
	    	userRepository.save(user);
	    }
	    
	    @GetMapping("/count")
	    public int CartCount(@PathVariable String username) {
	    	User user=userRepository.findByUsername(username);
	    	return user.getCarts().size();
	    }
}
