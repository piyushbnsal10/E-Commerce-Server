package rc.bootsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rc.bootsecurity.model.PreviousOrder;
import rc.bootsecurity.service.PreviousOrderService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="/users/{username}")
public class PreviousOrderController {
	
	@Autowired
    private PreviousOrderService previousOrderService;
	
	@GetMapping("/previousOrder")
	public ResponseEntity<List<PreviousOrder>> getPreviousOrder(@PathVariable String username) {
		try {
			List<PreviousOrder> previousOrders= previousOrderService.getPreviousOrder(username);
			return new ResponseEntity<>(previousOrders,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/checkout")
	public ResponseEntity<String> checkout(@PathVariable String username) {
		try {
			previousOrderService.checkout(username);
			return new ResponseEntity<>("Successfully CheckedOut",HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Some Error Occured",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
