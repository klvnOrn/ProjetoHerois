package projeto.herois.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

@Entity
public class Herois implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Type(type = "uuid-char") @Column(length = 36)
	private UUID idHeroi;
	private String nomeHeroi;
	@CreationTimestamp
	private LocalDate dataCadastro;

	@ManyToOne
	private Universos universo;
	
	public Universos getUniverso() {
		return universo;
	}

	public void setUniverso(Universos universo) {
		this.universo = universo;
	}

	public UUID getIdHeroi() {
		return idHeroi;
	}
	
	public void setIdHeroi(UUID idHeroi) {
		this.idHeroi = idHeroi;
	}
	
	public String getNomeHeroi() {
		return nomeHeroi;
	}
	
	public void setNomeHeroi(String nomeHeroi) {
		this.nomeHeroi = nomeHeroi;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
