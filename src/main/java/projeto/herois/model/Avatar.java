package projeto.herois.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;
	
@Entity
public class Avatar implements Serializable{
		
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue
	@Type(type = "uuid-char") @Column(length = 36)
	private UUID idAvatar;
	private String pathAvatar;
	private String nomeAvatar;
	
	@OneToOne
	private Herois herois;
	
	public UUID getIdAvatar() {
		return idAvatar;
	}
	public void setIdAvatar(UUID idAvatar) {
		this.idAvatar = idAvatar;
	}
	public String getPathAvatar() {
		return pathAvatar;
	}
	public void setPathAvatar(String pathAvatar) {
		this.pathAvatar = pathAvatar;
	}
	public String getNomeAvatar() {
		return nomeAvatar;
	}
	public void setNomeAvatar(String nomeAvatar) {
		this.nomeAvatar = nomeAvatar;
	}
	
	
}
