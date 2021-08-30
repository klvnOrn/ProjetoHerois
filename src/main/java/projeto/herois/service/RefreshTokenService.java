package projeto.herois.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import projeto.herois.exception.TokenException;
import projeto.herois.model.RefreshToken;
import projeto.herois.repository.CrudLogin;
import projeto.herois.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {
	@Value("${jwt.RefreshExpirationM}")
	  private Long refreshTokenDurationMs;

	  @Autowired
	  private RefreshTokenRepository refreshTokenRepository;

	  @Autowired
	  private CrudLogin userRepository;

	  public Optional<RefreshToken> findByToken(String token) {
	    return refreshTokenRepository.findByToken(token);
	  }

	  public RefreshToken createRefreshToken(UUID userId) {
		  RefreshToken refreshToken = new RefreshToken();

		  refreshToken.setUser(userRepository.findById(userId).get());
		  refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		  refreshToken.setToken(UUID.randomUUID().toString());

		  refreshToken = refreshTokenRepository.save(refreshToken);
	    return refreshToken;
	  }

	  public RefreshToken verifyExpiration(RefreshToken token) {
	    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
	    	refreshTokenRepository.delete(token);
	      throw new TokenException(token.getToken(), "Token expirado, por favor faça novamente o login.");
	    }

	    return token;
	  }

	  @Transactional
	  public int deleteByUserId(UUID id_user) {
	    return refreshTokenRepository.deleteByUser(userRepository.findById(id_user).get());
	  }
	
}
