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

import rc.bootsecurity.db.ProductRepo;
import rc.bootsecurity.model.Product;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="/products")
public class ProductController {

	@Autowired
	ProductRepo productsRepo;
	
	@PostMapping(path="/")
	public ResponseEntity<Void> addProduct(@RequestBody Product products){
		productsRepo.save(products);
		System.out.println(products);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="/{pId}")
	public void deleteProduct(@PathVariable("pId") int pId){
		productsRepo.deleteById(pId);
	}
	
	@GetMapping(path="/{pId}")
	public Product getProductById(@PathVariable("pId") int pId){
		return productsRepo.findById(pId).get();
	}
	
	@PutMapping(path="/{pId}")
	public void updateProduct(@PathVariable("pId") int id, @RequestBody Product products) {
		Product product = getProductById(id);
		product.setTitle(products.getTitle());
		product.setPrice(products.getPrice());
		product.setCategory(products.getCategory());
		product.setImgUrl(products.getTitle());
		product.setDescp(products.getDescp());
		product.setQuantity(products.getQuantity());
		
		productsRepo.save(product);
	}
	
	@GetMapping(path="/")
	public List<Product> findAllProducts()
	{
		List<Product> products= productsRepo.findAll();
		return products;
	}
	
	@GetMapping(path="/title/{title}")
	public ResponseEntity<Product> findProductsByTitle(@PathVariable("title") String title)
	{
		Product products =productsRepo.findByTitle(title);
		ResponseEntity<Product> re = null;
		if(products == null){
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return re;
		}
		re = new ResponseEntity<>(products, HttpStatus.OK);
		return re;
	}
	
	@GetMapping(path="/category/{category}")
	public ResponseEntity<Product> findProductsByCategory(@PathVariable("category") String category)
	{
		Product products =productsRepo.findByCategory(category);
		ResponseEntity<Product> re = null;
		if(products == null){
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return re;
		}
		re = new ResponseEntity<>(products, HttpStatus.OK);
		return re;
	}
}
