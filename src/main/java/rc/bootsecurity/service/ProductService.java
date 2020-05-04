package rc.bootsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import rc.bootsecurity.db.ProductRepo;
import rc.bootsecurity.model.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductRepo productsRepo;
	
	public void addProduct(Product products){
		productsRepo.save(products);
	}
	
	public void deleteProduct(int pId){
		productsRepo.deleteById(pId);
	}
	
	public Product getProductById(int pId){
		return productsRepo.findById(pId).get();
	}
	
	
	public void updateProduct(int id, Product products) {
		Product product = getProductById(id);
		product.setTitle(products.getTitle());
		product.setPrice(products.getPrice());
		product.setCategory(products.getCategory());
		product.setImgUrl(products.getTitle());
		product.setDescp(products.getDescp());
		product.setQuantity(products.getQuantity());
		
		productsRepo.save(product);
	}
	
	
	public List<Product> findAllProducts()
	{
		List<Product> products= productsRepo.findAll();
		return products;
	}
	
	
	public Product findProductsByTitle(String title){
		return productsRepo.findByTitle(title);
	}
	
	
	public Product findProductsByCategory(@PathVariable("category") String category){
		return productsRepo.findByCategory(category);
	}

}
