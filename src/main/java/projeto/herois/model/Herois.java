package projeto.herois.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

@Entity
public class Herois implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Type(type = "uuid-char") @Column(length = 36)
	private UUID idHeroi;
	@Column(unique=true)
	@NotNull
	private String nomeHeroi;
	@CreationTimestamp
	private LocalDate dataCadastro;

	@ManyToOne
	private Universos universo;
	
	@ManyToMany
	private List<Poderes> poder;
	
	@OneToOne
	private Avatar avatar;
	
	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public List<Poderes> getPoder() {
		return poder;
	}

	public void setPoder(List<Poderes> poder) {
		this.poder = poder;
	}

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
