package rc.bootsecurity.db;

import org.springframework.data.jpa.repository.JpaRepository;

import rc.bootsecurity.model.Cart;

public interface CartRepo extends JpaRepository<Cart,Integer>{
	
	public Cart findByTitle(String title);
	
	public Cart findByCategory(String Category);
	
	public Cart findBypId(int pId);
}
