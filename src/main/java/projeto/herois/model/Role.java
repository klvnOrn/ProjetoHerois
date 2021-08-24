package projeto.herois.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
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

    @NaturalId
    @Column(length = 60)
    private String name;

    public Role() {

    }
    
    public Role(String name) {
        this.name = name;
    }

	public UUID getIdRole() {
		return idRole;
	}

	public void setIdRole(UUID idRole) {
		this.idRole = idRole;
	}

	public String getName() {
		return name;
	}

	public void setPermission(String name) {
		this.name = name;
	}



}
