package projeto.herois.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import projeto.herois.model.DAOu;
import projeto.herois.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
	@Override
    Optional<RefreshToken> findById(UUID id);

    Optional<RefreshToken> findByToken(String token);
    
    @Modifying
    int deleteByUser(DAOu id_user);
	
	
}
