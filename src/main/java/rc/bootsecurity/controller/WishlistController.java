package rc.bootsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.bootsecurity.model.Wishlist;
import rc.bootsecurity.service.WishlistService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="/users/{username}/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	 @GetMapping("")
	    public ResponseEntity<List<Wishlist>> getProduct(@PathVariable String username) {
		 try {
		 		List<Wishlist> wishlists=wishlistService.getProductFromWishlist(username);
		 		return new ResponseEntity<>(wishlists,HttpStatus.OK);
		 	} catch(Exception e) {
		 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 	}
	    }
	    
	    @PostMapping("/{pId}")
	    public ResponseEntity<String> addProductToWishlist(@PathVariable String username,@PathVariable Integer pId) throws Exception {
	    	try {
		 		wishlistService.addProductToWishlist(username, pId);
		 		return new ResponseEntity<>("Product added to Wishlist",HttpStatus.CREATED);
		 	} catch(Exception e) {
		 		return new ResponseEntity<>("Product Can't be added",HttpStatus.INTERNAL_SERVER_ERROR);
		 	}
	    	
	    }
	    
	    @DeleteMapping("/{wId}")
	    public ResponseEntity<String> deleteProductFromWishlist(@PathVariable String username,@PathVariable Integer wId) throws Exception {
	    	try {
		 		wishlistService.deleteProductFromWishlist(username,wId);
		 		return new ResponseEntity<>("Product deleted from Wishlist",HttpStatus.OK);
		 	} catch(Exception e) {
		 		return new ResponseEntity<>("Product Can't be deleted",HttpStatus.INTERNAL_SERVER_ERROR);
		 	}
	    }
	    
	    @GetMapping("/count")
	    public ResponseEntity<Integer> wishlistCount(@PathVariable String username) {
	    	try {
		 		int count=wishlistService.wishlistCount(username);
		 		return new ResponseEntity<>(count,HttpStatus.OK);
		 	} catch(Exception e) {
		 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 	}
	    }
}
