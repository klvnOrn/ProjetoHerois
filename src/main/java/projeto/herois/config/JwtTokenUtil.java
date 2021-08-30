package projeto.herois.config;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import projeto.herois.model.JwtRequest;

@Component
@SuppressWarnings("deprecation")
public class JwtTokenUtil implements Serializable {
private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
private static final long serialVersionUID = -2550185165626007488L;
public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 12;

@Value("${jwt.secret}")
private String secret;

@Value("${jwt.ExpirationMs}")
private int jwtExpirationMs;

public String generateJwtToken(JwtRequest userPrincipal) {
    return generateTokenFromUsername(userPrincipal.getUsername());
  }

  public String generateTokenFromUsername(String username) {
    return Jwts.builder().setSubject(username).setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  
public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
  }

public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(authToken);
      return true;
    } 
      catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
      }catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

}