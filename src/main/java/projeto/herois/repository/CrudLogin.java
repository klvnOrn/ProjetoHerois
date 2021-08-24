package projeto.herois.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projeto.herois.model.DAOu;

@Repository
public interface CrudLogin extends CrudRepository<DAOu, UUID> {
    Optional<DAOu> findByUsername(String username);

    Boolean existsByUsername(String username);
}
