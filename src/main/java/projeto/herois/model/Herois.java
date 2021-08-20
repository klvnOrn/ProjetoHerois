package projeto.herois.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class Herois implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Type(type = "uuid-char") @Column(length = 36)
	private UUID idHeroi;
	private String nomeHeroi;
	private LocalDate dataCadastro;
	
	public UUID getidHeroi() {
		return idHeroi;
	}
	
	public void setCodigo(UUID idHeroi) {
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
