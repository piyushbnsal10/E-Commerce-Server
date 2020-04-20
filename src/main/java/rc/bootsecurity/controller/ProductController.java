package rc.bootsecurity.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rc.bootsecurity.db.ProductRepo;
import rc.bootsecurity.model.Product;





@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="/api")
public class ProductController {

	@Autowired
	ProductRepo productsRepo;
	
	@RequestMapping(path="/products" , method=RequestMethod.POST)
	public ResponseEntity<Void> addCourse(@RequestBody Product products){
		productsRepo.save(products);
		System.out.println(products);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/products/{pId}" , method=RequestMethod.DELETE)
	public void deleteCourse(@PathVariable("pId") int pId){
		productsRepo.deleteById(pId);
	}
	
	@RequestMapping(path="/products/pId/{pId}" , method=RequestMethod.GET)
	public Product getProductById(@PathVariable("pId") int pId){
		return productsRepo.findById(pId).get();
	}
	
	@RequestMapping(path="/products/pId/{pId}" , method=RequestMethod.PUT)
	public void updateProduct(@PathVariable("pId") int id, @RequestBody Product products) {
		Product product = getProductById(id);
		product.setTitle(products.getTitle());
		product.setPrice(products.getPrice());
		product.setCategory(products.getCategory());
		product.setDescp(products.getDescp());
		productsRepo.save(product);
	}
	
	@RequestMapping(path="/products",method=RequestMethod.GET)
	public List<Product> findAllProducts()
	{
		List<Product> products= productsRepo.findAll();
		return products;
	}
	
	@RequestMapping(path="/products/{title}", method=RequestMethod.GET)
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
	
	@RequestMapping(path="/products/{category}", method=RequestMethod.GET)
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
