package rc.bootsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.bootsecurity.db.CartRepo;
import rc.bootsecurity.db.UserRepository;
import rc.bootsecurity.model.Cart;
import rc.bootsecurity.model.PreviousOrder;
import rc.bootsecurity.model.User;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="/users/{username}")
public class PreviousOrderController {
	
	@Autowired
    private UserRepository	userRepository;
	
	@Autowired
	private CartRepo cartRepo;
	
	@GetMapping("/previousOrder")
	public List<PreviousOrder> getPreviousOrder(@PathVariable String username) {
		User user= userRepository.findByUsername(username);
		return user.getPreviousOrders();
	}
	
	@PostMapping("/checkout")
	public void checkout(@PathVariable String username) {
		User user= userRepository.findByUsername(username);
		List<Cart> cartProduct=user.getCarts();
		
		List<Integer> pID_deleted=new ArrayList<Integer>();
		
		for(int i=0;i<cartProduct.size();i++)
		{
			PreviousOrder previousOrder=new PreviousOrder();
			previousOrder.setpId(cartProduct.get(i).getpId());
			previousOrder.setTitle(cartProduct.get(i).getTitle());
			previousOrder.setCategory(cartProduct.get(i).getCategory());
			previousOrder.setDescp(cartProduct.get(i).getDescp());
			previousOrder.setImgUrl(cartProduct.get(i).getImgUrl());
			previousOrder.setPrice(cartProduct.get(i).getPrice());
			previousOrder.setQuantity(cartProduct.get(i).getQuantity());
			
			pID_deleted.add(cartProduct.get(i).getcId());
			//add at the starting of the array;
			user.getPreviousOrders().add(0,previousOrder);
		}
		
		user.getCarts().clear();
		for(int i=0;i<pID_deleted.size();i++)
		cartRepo.deleteById(pID_deleted.get(i));
		
		userRepository.save(user);
	}

}
