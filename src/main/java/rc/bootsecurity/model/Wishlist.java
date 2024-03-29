package rc.bootsecurity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="WISHLIST")
public class Wishlist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WID",updatable=true,unique=true,nullable=false)
	int wId;	
	@Column(name="PID",updatable=true,nullable=false)
	int pId;	
	@Column(name="TITLE", nullable = false)
	String title;
	@Column(name="DESCRIPTION",nullable = false)
	String descp;
	@Column(name="CATEGORY",nullable = false)
	String category;
	@Column(name="PRICE", nullable = false)
	int price;
	@Column(name="IMAGE_URL",nullable = false)
	String imgUrl;
	
	
	public Wishlist() {}
	
	

	public Wishlist(@NotBlank String title,@NotBlank String descp,@NotBlank String category,@NotBlank int prices,@NotBlank String imageUrl) {
		super();
		this.title = title;
		this.descp = descp;
		this.category = category;
		this.price = prices;
		this.imgUrl = imageUrl;
	}
	
	public int getwId() {
		return wId;
	}

	public void setwId(int wId) {
		this.wId = wId;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pId;
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
		Wishlist other = (Wishlist) obj;
		if (pId != other.pId)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Cart [pId=" + pId + ", title=" + title + ", descp=" + descp + ", category=" + category + ", price="
				+ price + ", imgUrl=" + imgUrl +"]";
	}





	

	
	
	
	
}
