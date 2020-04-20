package rc.bootsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int pId;
	
	String title;
	String descp;
	String category;
	int price;
	String imgUrl;
	
	public Product() {}
	
	

	public Product(String title, String descp, String category, int prices, String imageUrl) {
		super();
		this.title = title;
		this.descp = descp;
		this.category = category;
		this.price = prices;
		this.imgUrl = imageUrl;
	}



	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int prices) {
		this.price = prices;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imageUrl) {
		this.imgUrl = imageUrl;
	}



	@Override
	public String toString() {
		return "Products [pId=" + pId + ", title=" + title + ", descp=" + descp + ", category=" + category + ", price="
				+ price + ", imgUrl=" + imgUrl + "]";
	}

	
	
	
	
}
