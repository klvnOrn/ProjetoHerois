package projeto.herois.model;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity(name = "RefreshTk")
public class RefreshToken {
	@Id
	@GeneratedValue
	@Type(type = "uuid-char") 
	@Column(length = 36, unique= true)
    private UUID id;

	  @OneToOne
	  @JoinColumn(name = "user_id", referencedColumnName = "id_user")
	  private DAOu user;

	  @Column(nullable = false, unique = true)
	  private String token;

	  @Column(nullable = false)
	  private Instant expiryDate;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public DAOu getUser() {
		return user;
	}

	public void setUser(DAOu user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
}
