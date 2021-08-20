package projeto.herois.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "login",
       uniqueConstraints=
       @UniqueConstraint(columnNames= {"usuario"})
)
public class DAOu implements Serializable {
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue
		@Type(type = "uuid-char") @Column(length = 36)
		private UUID id;
		
		@Column
		@NotNull
		@Size(min=6)
		private String usuario;
		
		@Column
		@NotNull
		@JsonIgnore
		@Size(min=8)
		private String senha;

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}
		
		
}
