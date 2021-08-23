package projeto.herois.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;


@Entity
public class Universos {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char") @Column(length = 36)
	private UUID idUniverso;
	private String nomeUniverso;
	
	public UUID getIdUniverso() {
		return idUniverso;
	}
	
	public void setIdUniverso(UUID idUniverso) {
		this.idUniverso = idUniverso;
	}
	
	public String getNomeUniverso() {
		return nomeUniverso;
	}
	
	public void setNomeUniverso(String nomeUniverso) {
		this.nomeUniverso = nomeUniverso;
	}
}
