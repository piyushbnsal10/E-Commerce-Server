package rc.bootsecurity.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "USERS")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "UID")
  private Long id;

  @Column(name = "USERNAME", unique = true, nullable = false)
  private String username;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @NotBlank
  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "ROLE", nullable = false)
  private String role = "user";

  @ManyToMany
  @JoinTable(name = "USER_CART", joinColumns = { @JoinColumn(name = "UID") }, inverseJoinColumns = {
      @JoinColumn(name = "CID") })
  private List<Cart> carts = new ArrayList<Cart>();
  
  @ManyToMany
  @JoinTable(name = "USER_WISHLIST", joinColumns = { @JoinColumn(name = "UID") }, inverseJoinColumns = {
      @JoinColumn(name = "WID") })
  private List<Wishlist> wishlists = new ArrayList<Wishlist>();
  
  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "USER_ORDERED", joinColumns = { @JoinColumn(name = "UID") }, inverseJoinColumns = {
	      @JoinColumn(name = "OID") })
  private List<PreviousOrder> previousOrders= new ArrayList<PreviousOrder>();

  public User() {
  }

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

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public List<Cart> getCarts() {
    return carts;
  }

  public Cart getProductFromCart(int pId) {
    int size = this.getCarts().size();

    for (int i = 0; i < size; i++) 
      if (this.getCarts().get(i).getpId() == pId)
        return this.getCarts().get(i);
    

    return null;
  }

  public void setCarts(List<Cart> carts) {
    this.carts = carts;
  }
  

  public List<Wishlist> getWishlists() {
	return wishlists;
}
  
  public Wishlist getProductFromWishlist(int pId) {
	    int size = this.getWishlists().size();

	    for (int i = 0; i < size; i++) 
	      if (this.getWishlists().get(i).getpId() == pId)
	        return this.getWishlists().get(i);
	    
	    return null;
  }
	    

  public void setWishlists(List<Wishlist> wishlists) {
	this.wishlists = wishlists;
  }

	public List<PreviousOrder> getPreviousOrders() {
	return previousOrders;
  }
	
	public PreviousOrder getProductFromPreviousOrder(int pId) {
	    int size = this.getPreviousOrders().size();

	    for (int i = 0; i < size; i++) 
	      if (this.getPreviousOrders().get(i).getpId() == pId)
	        return this.getPreviousOrders().get(i);
	    
	    return null;
  }

	public void setPreviousOrders(List<PreviousOrder> previousOrders) {
	this.previousOrders = previousOrders;
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

}
