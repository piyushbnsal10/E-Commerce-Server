package rc.bootsecurity.db;

import org.springframework.data.jpa.repository.JpaRepository;

import rc.bootsecurity.model.Product;



public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	public Product findByTitle(String title);
	
	public Product findByCategory(String Category);

}
