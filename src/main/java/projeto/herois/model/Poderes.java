package projeto.herois.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;



@Entity
public class Poderes {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char") @Column(length = 36)
	private UUID idPoder;
	private String nomePoder;
	
	public UUID getIdPoder() {
		return idPoder;
	}
	
	public void setIdPoder(UUID idPoder) {
		this.idPoder = idPoder;
	}
	
	public String getnomePoder() {
		return nomePoder;
	}
	
	public void setNomePoder(String nomePoder) {
		this.nomePoder = nomePoder;
	}
}
