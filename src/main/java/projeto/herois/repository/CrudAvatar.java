package projeto.herois.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import projeto.herois.model.Avatar;

public interface CrudAvatar extends CrudRepository<Avatar, UUID> {

}
