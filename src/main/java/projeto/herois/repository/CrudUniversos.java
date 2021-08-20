package projeto.herois.repository;



import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projeto.herois.model.Universos;



@Repository
public interface CrudUniversos extends CrudRepository<Universos, UUID> {
	Optional<Universos> findById(UUID idUniverso);
	void deleteById(UUID idUniverso);
}