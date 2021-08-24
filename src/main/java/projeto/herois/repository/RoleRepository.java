package projeto.herois.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.herois.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
	 Optional<Role> findByName(String roleName);
}
