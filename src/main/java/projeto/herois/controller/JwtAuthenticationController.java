package projeto.herois.controller;

import java.net.URI;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import projeto.herois.config.JwtTokenUtil;
import projeto.herois.exception.AppException;
import projeto.herois.model.DAOu;
import projeto.herois.model.JwtRequest;
import projeto.herois.model.JwtResponse;
import projeto.herois.model.Role;
import projeto.herois.payload.ApiResponse;
import projeto.herois.payload.SignUpRequest;
import projeto.herois.repository.CrudLogin;
import projeto.herois.repository.RoleRepository;
import projeto.herois.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
@Autowired
private CrudLogin LoginRepo;

@Autowired
RoleRepository roleRepository;

@Autowired
private PasswordEncoder bcryptEncoder;

@Autowired
private AuthenticationManager authenticationManager;

@Autowired
private JwtTokenUtil jwtTokenUtil;

@Autowired
private JwtUserDetailsService userDetailsService;

@RequestMapping(value = "/auth", method = RequestMethod.POST)
public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
	authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
	final UserDetails userDetails = userDetailsService
			.loadUserByUsername(authenticationRequest.getUsername());
	final String token = jwtTokenUtil.generateToken(userDetails);
return ResponseEntity.ok(new JwtResponse(token));
}


@RequestMapping(value = "/registro", method = RequestMethod.POST)
public ResponseEntity<?> saveUser(@RequestBody SignUpRequest username) {
	
	 if(LoginRepo.existsByUsername(username.getUsername())) {
         return new ResponseEntity<Object>(new ApiResponse(false, "\r\n"
         		+ "O nome de usuário já está em uso!"),
                 HttpStatus.BAD_REQUEST);
     }
	 DAOu user = new DAOu();
	 
	 user.setUsername(username.getUsername());

     user.setPassword(bcryptEncoder.encode(username.getPassword()));

     Role userRole = roleRepository.findByName("Default").orElseThrow(() -> new AppException("User Role not set."));

     user.setRole(Collections.singleton(userRole));

     DAOu result = LoginRepo.save(user);

     URI location = ServletUriComponentsBuilder
             .fromCurrentContextPath().path("/users/{username}")
             .buildAndExpand(result.getUsername()).toUri();

     return ResponseEntity.created(location).body(new ApiResponse(true, "Usuario registrado com sucesso"));
}

private void authenticate(String username, String password) throws Exception {
try {
	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
} catch (DisabledException e) {
	throw new Exception("Usuario Desabilitado", e);
} catch (BadCredentialsException e) {
	throw new Exception("Credencial Invalida", e);
}
}
}