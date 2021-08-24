package projeto.herois.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;


@Entity
public class Universos {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char") @Column(length = 36)
	private UUID idUniverso;
	@Column(unique=true)
	@NotNull
	private String nomeUniverso;
	
	@OneToMany
	private List<Herois> herois;
	
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
