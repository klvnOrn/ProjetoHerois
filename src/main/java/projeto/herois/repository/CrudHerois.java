package projeto.herois.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projeto.herois.model.Herois;


@Repository
public interface CrudHerois extends CrudRepository<Herois, String> {
	
}
