package rc.bootsecurity.model;

import java.util.ArrayList;
import java.util.List;

//import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="USER")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID")
    private Long id;

    @Column(name = "USERNAME",unique = true, nullable = false)
    private String username;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotBlank
	@Column(name = "PASSWORD",nullable = false)
    private String password;
    
//    @OneToMany(mappedBy="User",
//    			cascade= {CascadeType.PERSIST,CascadeType.MERGE,
//    						CascadeType.DETACH,CascadeType.REFRESH
//    			})
    @ManyToMany
    @JoinTable(name = "USER_PRODUCT", 
    joinColumns = { @JoinColumn(name = "UID") }, 
    inverseJoinColumns = { @JoinColumn(name = "PID") })
    private List<Product> products=new ArrayList<Product>(); 

    public User() {
    }

//    public User(Long id) {
//        this.id = id;
//    }

//    public User(String username, @NotBlank String password) {
//        this.username = username;
//        this.password = password;
//    }

    public User(@NotBlank String username, @NotBlank String email, @NotBlank String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
