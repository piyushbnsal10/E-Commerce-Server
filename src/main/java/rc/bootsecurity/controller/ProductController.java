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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.bootsecurity.model.Product;
import rc.bootsecurity.service.ProductService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="/products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping(path="")
	public ResponseEntity<String> addProduct(@RequestBody Product products){
		try {
			productService.addProduct(products);
			return new ResponseEntity<>("Product Added Successfully",HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>("Product can't be added",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path="/{pId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("pId") int pId){
		try {
			productService.deleteProduct(pId);
			return new ResponseEntity<>("Product Deleted Successfully",HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Product can't be Deleted",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/{pId}")
	public ResponseEntity<Product> getProductById(@PathVariable("pId") int pId){
		try {
			Product product=productService.getProductById(pId);
			return new ResponseEntity<>(product,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path="/{pId}")	
	public ResponseEntity<String> updateProduct(@PathVariable("pId") int id, @RequestBody Product products) {
		try {
			productService.updateProduct(id, products);
			return new ResponseEntity<>("Product Updated Successfully",HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Product Can't be Updated",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="")
	public ResponseEntity<List<Product>> findAllProducts() {
		try {
			List<Product> products= productService.findAllProducts();
			return new ResponseEntity<>(products,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/title/{title}")
	public ResponseEntity<Product> findProductsByTitle(@PathVariable("title") String title)
	{
		try { 
			Product product =productService.findProductsByTitle(title);
			return new ResponseEntity<>(product,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/category/{category}")
	public ResponseEntity<Product> findProductsByCategory(@PathVariable("category") String category)
	{
		try { 
			Product product =productService.findProductsByCategory(category);
			return new ResponseEntity<>(product,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
