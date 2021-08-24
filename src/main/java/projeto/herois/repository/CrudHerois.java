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
	
//	default void thanosDelete(Iterable<Herois> allHerois) {
//        Objects.requireNonNull(allHerois);
//        for (Herois t : allHerois) {
//    		System.out.println(t.getNomeHeroi());
//        	if (Math.random() > 0.5) {
//        		delete(t);
//        		System.out.println("RIP");
//        	}
//        }
//	}
}
