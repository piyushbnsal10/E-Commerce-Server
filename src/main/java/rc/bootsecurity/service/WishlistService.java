package rc.bootsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rc.bootsecurity.db.ProductRepo;
import rc.bootsecurity.db.UserRepository;
import rc.bootsecurity.db.WishlistRepo;
import rc.bootsecurity.model.Product;
import rc.bootsecurity.model.User;
import rc.bootsecurity.model.Wishlist;

@Service
public class WishlistService {
	
	@Autowired
    private UserRepository	userRepository;
	
	@Autowired
	private WishlistRepo wishlistRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	    public List<Wishlist> getProductFromWishlist(String username) {
		 	User user=userRepository.findByUsername(username);
	    	return user.getWishlists();
	    }
	    
	    public String addProductToWishlist(String username,Integer pId) throws Exception {
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
	    
	  
	    public void deleteProductFromWishlist(String username,Integer wId) throws Exception {
	    	User user=userRepository.findByUsername(username);
	    	Wishlist userWishlist=wishlistRepo.findById(wId).get();
	    	user.getWishlists().remove(userWishlist);
	    	wishlistRepo.delete(userWishlist);
	    }
	    
	    public int wishlistCount(String username) {
	    	User user=userRepository.findByUsername(username);
	    	return user.getWishlists().size();
	    }
}

