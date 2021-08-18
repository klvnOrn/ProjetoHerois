package projeto.herois.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projeto.herois.model.DAOu;

@Repository
public interface CrudLogin extends CrudRepository<DAOu, Integer> {
	CrudLogin findByUsuario(String usuario);
}
