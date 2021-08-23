package projeto.herois.repository;



import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projeto.herois.model.Poderes;




@Repository
public interface CrudPoderes extends CrudRepository<Poderes, UUID> {
	Optional<Poderes> findById(UUID idPoderes);
	void deleteById(UUID idPoderes);
}