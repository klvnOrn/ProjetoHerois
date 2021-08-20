package projeto.herois.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projeto.herois.model.Herois;
import projeto.herois.model.Poderes;




@Repository
public interface CrudPoderes extends CrudRepository<Poderes, Integer> {
	CrudPoderes findByNomePoder(String nomePoder);
}