package projeto.herois.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class JwtResponse implements Serializable {

private static final long serialVersionUID = -8091879091924046844L;
private final String jwttoken;

private String type = "Bearer";
private String refreshToken;
private UUID id;
private String username;

private List<String> roles;


public JwtResponse(String jwttoken) {
	this.jwttoken = jwttoken;
}

public JwtResponse(String jwttoken, String refreshToken, UUID id, String username, List<String> roles) {
	this.jwttoken = jwttoken;
	this.refreshToken = refreshToken;
	this.id = id;
	this.username = username;
	this.roles = roles;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getRefreshToken() {
	return refreshToken;
}

public void setRefreshToken(String refreshToken) {
	this.refreshToken = refreshToken;
}

public UUID getId() {
	return id;
}

public void setId(UUID id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public List<String> getRoles() {
	return roles;
}

public void setRoles(List<String> roles) {
	this.roles = roles;
}

public String getToken() {
	return this.jwttoken;
}


}
