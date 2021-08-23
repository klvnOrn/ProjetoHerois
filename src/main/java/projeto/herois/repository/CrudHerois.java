package projeto.herois.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projeto.herois.model.Herois;


@Repository
public interface CrudHerois extends CrudRepository<Herois, UUID> {
	Optional<Herois> findById(UUID idHerois);
	void deleteById(UUID idHerois);
}
