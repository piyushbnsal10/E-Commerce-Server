package rc.bootsecurity.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.bootsecurity.model.Cart;
import rc.bootsecurity.service.CartService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="/users/{username}/cart")
public class CartController {
	
	
	@Autowired
	private CartService cartService;
		
	 @GetMapping("")
	    public ResponseEntity<List<Cart>> getProduct(@PathVariable String username) {
		 	try {
		 		List<Cart> carts=cartService.getProductsFromCart(username);
		 		return new ResponseEntity<>(carts,HttpStatus.OK);
		 	} catch(Exception e) {
		 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 	}
		 	
	    }
	    
	    @PostMapping(path="/{pId}")
	    public ResponseEntity<String> addProductToCart(@PathVariable String username,@PathVariable Integer pId) throws Exception {
	    	
	    	try {
	    		cartService.addProductToCart(username, pId);
		 		return new ResponseEntity<>("Product added successfully",HttpStatus.CREATED);
		 	} catch(Exception e) {
		 		return new ResponseEntity<>("Product can't be added",HttpStatus.INTERNAL_SERVER_ERROR);
		 	}
	    }
	    
	    @DeleteMapping("/{cId}")
	    public ResponseEntity<String> deleteProductFromCart(@PathVariable String username,@PathVariable Integer cId) throws Exception {
	    	try {
	    		cartService.deleteProductFromCart(username, cId);	
		 		return new ResponseEntity<>("Product deleted successfully",HttpStatus.CREATED);
		 	} catch(Exception e) {
		 		return new ResponseEntity<>("Product can't be deleted",HttpStatus.INTERNAL_SERVER_ERROR);
		 	}
	    }
	    
	    @PostMapping("/{pId}/edit")
	    public ResponseEntity<String> editCartProductQuantity(@PathVariable String username,@PathVariable Integer pId,@RequestBody HashMap<String,Integer> edit) throws Exception {
	    	try {
	    		cartService.editCartProductQuantity(username, pId, edit);
		 		return new ResponseEntity<>("Product quanity changed",HttpStatus.OK);
		 	} catch(Exception e) {
		 		return new ResponseEntity<>("Product quantity can't be changed",HttpStatus.INTERNAL_SERVER_ERROR);
		 	}
	    	
	    }
	    
	    @GetMapping("/count")
	    public ResponseEntity<Integer> CartCount(@PathVariable String username) {
	    	try {
	    		int count=cartService.CartCount(username);
		 		return new ResponseEntity<>(count,HttpStatus.OK);
		 	} catch(Exception e) {
		 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		 	}
	    }
}
