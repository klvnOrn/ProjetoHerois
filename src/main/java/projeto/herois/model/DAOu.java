package projeto.herois.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;




@Entity
@Table(name = "login", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username")})
public class DAOu implements Serializable {
	
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue
		@Type(type = "uuid-char") @Column(length = 36)
		private UUID id_user;
		
		@NotBlank
		@Size(min = 3, max = 20)
		private String username;
		
		@NotBlank
		private String password;
		
//		@OneToMany(mappedBy = "daou", cascade= {CascadeType.ALL}, orphanRemoval=true)
//		private List<Herois> herois;
		
		 @ManyToMany(fetch = FetchType.LAZY)
		    @JoinTable(name = "user_roles",
		            joinColumns = @JoinColumn(name = "user_id"),
		            inverseJoinColumns = @JoinColumn(name = "role_id"))
		    private Set<Role> role = new HashSet<>();
		
		
		public DAOu() {
			
		}
		
		public DAOu(String username, String password) {
			this.username = username;
			this.password = password;
			
		}
		

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}


		public UUID getId_user() {
			return id_user;
		}


		public void setId_user(UUID id_user) {
			this.id_user = id_user;
		}


		public Set<Role> getRole() {
			return role;
		}


		public void setRole(Set<Role> role) {
			this.role = role;
		}
		
		
}
