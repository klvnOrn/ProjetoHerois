package projeto.herois.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;
	
@Entity
public class Avatar implements Serializable{
		
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue
	@Type(type = "uuid-char") @Column(length = 36)
	private UUID idAvatar;
	@Lob
	private byte[] avatar;
	
	@OneToMany
	private List<Herois> herois;
	
	public UUID getIdAvatar() {
		return idAvatar;
	}
	public void setIdAvatar(UUID idAvatar) {
		this.idAvatar = idAvatar;
	}
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
}
