package projeto.herois.repository;



import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projeto.herois.model.Herois;
import projeto.herois.model.Universos;




@Repository
public interface CrudUniversos extends CrudRepository<Universos, Integer> {
	CrudUniversos findByIdUniverso(UUID idUniverso);
}