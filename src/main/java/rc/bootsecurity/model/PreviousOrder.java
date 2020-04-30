package rc.bootsecurity.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "PREVIOUS_ORDER")
public class PreviousOrder {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="OID",updatable=true,unique=true,nullable=false)
  int OId;
  @Column(name = "PID",nullable=false)
  int pId;
  @Column(name = "TITLE", nullable = false)
  String title;
  @Column(name = "DESCRIPTION", nullable = false)
  String descp;
  @Column(name = "CATEGORY", nullable = false)
  String category;
  @Column(name = "PRICE", nullable = false)
  int price;
  @Column(name = "IMAGE_URL", nullable = false)
  String imgUrl;
  @Column(name = "QUANTITY", nullable = false)
  int quantity;
  @Column(name = "local_time", columnDefinition = "TIME")
  private LocalTime localTime;
   
  @Column(name = "local_date", columnDefinition = "DATE")
  private LocalDate localDate;
  
  public PreviousOrder() {
  }

  public PreviousOrder(@NotBlank String title, @NotBlank String descp, @NotBlank String category, @NotBlank int prices,
      @NotBlank String imageUrl, @NotBlank int quantity) {
    super();
    this.title = title;
    this.descp = descp;
    this.category = category;
    this.price = prices;
    this.imgUrl = imageUrl;
    this.quantity = quantity;
  }
  
  public int getOId() {
	return OId;
  }

  public void setOId(int oId) {
	OId = oId;
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

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  
  @PrePersist
  protected void onLocalTime() {
    localTime = LocalTime.now();
    localDate = LocalDate.now();
  }
    
  
public LocalTime getLocalTime() {
	return localTime;
}

public void setLocalTime(LocalTime localTime) {
	this.localTime = localTime;
}

public LocalDate getLocalDate() {
	return localDate;
}

public void setLocalDate(LocalDate localDate) {
	this.localDate = localDate;
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
    PreviousOrder other = (PreviousOrder) obj;
    if (pId != other.pId)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Product [pId=" + pId + ", title=" + title + ", descp=" + descp + ", category=" + category + ", price="
        + price + ", imgUrl=" + imgUrl + ", quantity=" + quantity + "]";
  }

}
