package rc.bootsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.bootsecurity.db.CartRepo;
import rc.bootsecurity.db.ProductRepo;
import rc.bootsecurity.db.UserRepository;
import rc.bootsecurity.db.WishlistRepo;
import rc.bootsecurity.model.Cart;
import rc.bootsecurity.model.Product;
import rc.bootsecurity.model.User;
import rc.bootsecurity.model.Wishlist;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="/users/{username}/wishlist")
public class WishlistController {
	
	@Autowired
    private UserRepository	userRepository;
	
	@Autowired
	private WishlistRepo wishlistRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	 @GetMapping("")
	    public List<Wishlist> getProduct(@PathVariable String username) {
		 	User user=userRepository.findByUsername(username);
	    	return user.getWishlists();
	    }
	    
	    @PostMapping("/{pId}")
	    public String addProductToWishlist(@PathVariable String username,@PathVariable Integer pId) throws Exception {
	    	User user=userRepository.findByUsername(username);
	    	Product product=productRepo.findById(pId).get();
	    	
	    	if(user.getProductFromWishlist(product.getpId())!=null) {
	    		return "Product is already added to Wishlist";
	    		
	    	} else {
	    		Wishlist userWishlist=new Wishlist();
	    		userWishlist.setpId(product.getpId());
	    		userWishlist.setCategory(product.getCategory());
	    		userWishlist.setDescp(product.getDescp());
	    		userWishlist.setImgUrl(product.getImgUrl());
	    		userWishlist.setTitle(product.getTitle());
	    		userWishlist.setPrice(product.getPrice());
	    		
	    		wishlistRepo.save(userWishlist);
	    		user.getWishlists().add(userWishlist);
	    	}
	    	
	    	userRepository.save(user);
	    	
	    	return "Product added to wishlist";
	    	
	    }
	    
	    @DeleteMapping("/{pId}")
	    public void deleteProductFromWishlist(@PathVariable String username,@PathVariable Integer pId) throws Exception {
	    	User user=userRepository.findByUsername(username);
	    	Wishlist userWishlist=wishlistRepo.findBypId(pId);
	    	user.getWishlists().remove(userWishlist);
	    	wishlistRepo.delete(userWishlist);
	    }
	    
	    @GetMapping("/count")
	    public int wishistCount(@PathVariable String username) {
	    	User user=userRepository.findByUsername(username);
	    	return user.getWishlists().size();
	    }
}
