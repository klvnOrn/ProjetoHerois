package projeto.herois.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;



@Entity
@Table(name = "roles")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Type(type = "uuid-char") 
	@Column(length = 36, unique= true)
    private UUID idRole;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
    private EnRole name;

    public Role() {

    }
    
    public Role(EnRole name) {
        this.name = name;
    }

	public UUID getIdRole() {
		return idRole;
	}

	public void setIdRole(UUID idRole) {
		this.idRole = idRole;
	}

	public EnRole getName() {
		return name;
	}

	public void setName(EnRole name) {
		this.name = name;
	}



}
