package rc.bootsecurity.db;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rc.bootsecurity.model.Product;
import rc.bootsecurity.model.User;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ProductRepo productsRepo;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder,ProductRepo productsRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.productsRepo=productsRepo;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();
        this.productsRepo.deleteAll();

        // Crete users
        User dan = new User("dan","dan123@gmail.com",passwordEncoder.encode("dan123"));
        User admin = new User("admin","admin123@gmail.com",passwordEncoder.encode("admin123"));
        User manager = new User("manager","manager123@gmail.com",passwordEncoder.encode("manager123"));
        
        //create products
        //(String title, String desc, String category, int prices, String imageUrl
        Product p1=new Product("Usha Ceiling Fan",
        		"A High speed fan which will freeze you",
        		"Electronics",
        		1500,
        		"https://5.imimg.com/data5/UF/EM/MY-5431215/usha-anti-dust-ceiling-fan-500x500.jpg");
        
        Product p2=new Product("Levis Trouser",
        		"When you wear it, then you feel it",
        		"Clothing",
        		2000,
        		"https://rukminim1.flixcart.com/image/332/398/jtn9bww0/trouser/r/n/2/32-74712-0000-levi-s-original-imafeydc8uhgdkvg.jpeg");
        
        Product p3=new Product("Realme X Smartphone",
        		"But it once, you will remember it",
        		"Electronics",
        		16000,
        		"https://i.gadgets360cdn.com/products/large/1557912398_635_realme_x.jpg");

        List<User> users = Arrays.asList(dan,admin,manager);
        List<Product> product=Arrays.asList(p1,p2,p3);

        // Save to db
        this.userRepository.saveAll(users);
        this.productsRepo.saveAll(product);
    }
}
