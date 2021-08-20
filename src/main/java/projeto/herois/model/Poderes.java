package projeto.herois.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;



@Entity
public class Poderes {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char") @Column(length = 36)
	private UUID idPoder;
    
	private String nomePoder;
	
//	@OneToMany
	private Herois heroi;
	
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
	
	public Herois getHeroi() {
		return heroi;
	}
	
	public void setHeroi(Herois heroi) {
		this.heroi = heroi;
	}
}
