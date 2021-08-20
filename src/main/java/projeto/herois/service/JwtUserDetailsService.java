package projeto.herois.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import projeto.herois.model.DAOu;
import projeto.herois.model.JwtRequest;
import projeto.herois.repository.CrudLogin;

@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private CrudLogin LoginRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {

		DAOu user = LoginRepo.findByUsuario(usuario);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + usuario);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getSenha(),
				new ArrayList<>());
	}

	public DAOu salvar(JwtRequest usuario) {
		DAOu nUsuario = new DAOu();
		nUsuario.setUsuario(usuario.getUsuario());
		nUsuario.setSenha(bcryptEncoder.encode(usuario.getSenha()));
		return LoginRepo.save(nUsuario);
	}
}