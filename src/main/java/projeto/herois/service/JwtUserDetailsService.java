package projeto.herois.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import projeto.herois.model.DAOu;
import projeto.herois.model.JwtRequest;
import projeto.herois.repository.CrudLogin;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private CrudLogin LoginRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		if ("wabalabdubdub".equals(usuario)) {
			return new User("wabalabdubdub", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Usuario não encontrado: " + usuario);
		}
	}
	public CrudLogin save(JwtRequest usuario) {
		DAOu nUsuario = new DAOu();
		nUsuario.setUsuario(usuario.getUsuario());
		nUsuario.setSenha(bcryptEncoder.encode(usuario.getSenha()));
		return (CrudLogin) LoginRepo.save(nUsuario);
	}
}
