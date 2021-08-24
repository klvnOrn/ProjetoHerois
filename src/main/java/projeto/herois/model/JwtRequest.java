package projeto.herois.model;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class JwtRequest implements Serializable, UserDetails {

private static final long serialVersionUID = 5926468583005150707L;

private UUID id_user;

private String username;

private String password;

private Collection <? extends GrantedAuthority> authorities;

public JwtRequest() {}


public JwtRequest(String username, String password, Collection<? extends GrantedAuthority> authorities) {
	this.username = username;
	this.password = password;
	this.authorities = authorities;
}

public static JwtRequest create(DAOu usuario) {
    List<GrantedAuthority> authorities = usuario.getRole().stream().map(role ->
            new SimpleGrantedAuthority(role.getName())
    ).collect(Collectors.toList());

    return new JwtRequest(
            usuario.getUsername(),
            usuario.getPassword(),
            authorities
    );
}
@Override
public String getUsername() {
	return username;
}

@Override
public String getPassword() {
	return password;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
}

@Override
public boolean isAccountNonExpired() {
    return true;
}

@Override
public boolean isAccountNonLocked() {
    return true;
}

@Override
public boolean isCredentialsNonExpired() {
    return true;
}

@Override
public boolean isEnabled() {
    return true;
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JwtRequest that = (JwtRequest) o;
    return Objects.equals(id_user, that.id_user);
}

@Override
public int hashCode() {

    return Objects.hash(id_user);
}
}