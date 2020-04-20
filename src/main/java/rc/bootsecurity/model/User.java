package rc.bootsecurity.model;

import com.fasterxml.jackson.annotation.JsonProperty;

//import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    //@Column(unique = true, nullable = false)
    private String username;

  //  @Column(unique = true, nullable = false)
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String email;

  //  @NotBlank
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

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

//    public User(Long id, @NotBlank String username, @NotBlank String email, @NotBlank String password) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//    }

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
