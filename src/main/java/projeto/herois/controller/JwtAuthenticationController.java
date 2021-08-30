package projeto.herois.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projeto.herois.config.JwtTokenUtil;
import projeto.herois.config.TokenRefreshRequest;
import projeto.herois.exception.AppException;
import projeto.herois.exception.TokenException;
import projeto.herois.model.DAOu;
import projeto.herois.model.EnRole;
import projeto.herois.model.JwtRequest;
import projeto.herois.model.JwtResponse;
import projeto.herois.model.Poderes;
import projeto.herois.model.RefreshToken;
import projeto.herois.model.Role;
import projeto.herois.payload.ApiResponse;
import projeto.herois.payload.LogOutRequest;
import projeto.herois.payload.LoginRequest;
import projeto.herois.payload.SignUpRequest;
import projeto.herois.payload.TokenRefreshResponse;
import projeto.herois.repository.CrudLogin;
import projeto.herois.repository.RoleRepository;
import projeto.herois.service.RefreshTokenService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {
	
@Autowired
private CrudLogin LoginRepo;

@Autowired
RoleRepository roleRepository;

@Autowired
RoleRepository refreshTokenRepository;

@Autowired
private PasswordEncoder bcryptEncoder;

@Autowired
private AuthenticationManager authenticationManager;

@Autowired
private JwtTokenUtil jwtTokenUtil;


@Autowired
private RefreshTokenService refreshTokenService;

@PostMapping("/logar")
public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

  Authentication authentication = authenticationManager
      .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

  SecurityContextHolder.getContext().setAuthentication(authentication);

  JwtRequest userDetails = (JwtRequest) authentication.getPrincipal();

  String jwt = jwtTokenUtil.generateJwtToken(userDetails);

  List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
      .collect(Collectors.toList());

  RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId_user());

  return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId_user(),
      userDetails.getUsername(), roles));
}


@PostMapping(value = "/registro")
public ResponseEntity<?> registroUsuario(@Valid @RequestBody SignUpRequest signUpRequest) {
    if (LoginRepo.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new AppException("Erro: Usuário já existe"));
    }

    DAOu user = new DAOu(signUpRequest.getUsername(),
    		bcryptEncoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(EnRole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Erro: Função não encontrada."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(EnRole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Erro: Função não encontrada."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(EnRole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Erro: Função não encontrada."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(EnRole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Erro: Função não encontrada."));
          roles.add(userRole);
        }
      });
    }

    user.setRole(roles);
    LoginRepo.save(user);

    return ResponseEntity.ok(new AppException("Usuário registrado com sucesso!"));
  }

@PostMapping("/renovar")
public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
	String requestRefreshToken = request.getRefreshToken();

    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwtTokenUtil.generateTokenFromUsername(user.getUsername());
          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        })
        .orElseThrow(() -> new TokenException(requestRefreshToken,
            "Token de renovação não se encontra no banco de dados!"));
  }
  


@PostMapping("/sair")
public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
  refreshTokenService.deleteByUserId(logOutRequest.getIduser());
  return ResponseEntity.ok(new AppException("Desconectado com Sucesso!"));
}

//@DeleteMapping("/deletarUsuario/{id_user}")
//public ResponseEntity<?> deleteUsuario(@Valid @PathVariable("id_user") UUID id_user) throws NotFoundException{
//	DAOu doau = LoginRepo.findById(id_user).orElse(null);
////	Role role = roleRepository.findById(id_user).orElse(null);
////	RefreshToken rfToken = refreshTokenRepository.findById(id_user).orElse(null);
//	if (doau == null) {
//		throw new NotFoundException();  	
//	}
//	else {
//		refreshTokenRepository.deleteById(id_user);
//		roleRepository.deleteById(id_user);
//		LoginRepo.deleteById(id_user);
//	}
//  return ResponseEntity.ok(new AppException("Usuario "+ id_user + " deletado com Sucesso!"));
//}

}