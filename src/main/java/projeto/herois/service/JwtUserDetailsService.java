package projeto.herois.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto.herois.model.DAOu;
import projeto.herois.model.JwtRequest;
import projeto.herois.repository.CrudLogin;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private CrudLogin LoginRepo;

	@Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        DAOu user = LoginRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado : " + username)
        );

        return JwtRequest.create(user);
    }

	
}