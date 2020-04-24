package rc.bootsecurity.db;

import org.springframework.data.jpa.repository.JpaRepository;

import rc.bootsecurity.model.Wishlist;

public interface WishlistRepo extends JpaRepository<Wishlist,Integer>{
	
	public Wishlist findByTitle(String title);
	
	public Wishlist findByCategory(String Category);
	
	public Wishlist findBypId(int pId);
}
