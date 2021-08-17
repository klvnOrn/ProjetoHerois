package projeto.herois.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import projeto.herois.service.JwtUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

@Autowired
private JwtUserDetailsService jwtUserDetailsService;

@Autowired
private JwtTokenUtil jwtTokenUtil;

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
throws ServletException, IOException {
	final String requestTokenHeader = request.getHeader("Authorization");

String usuario = null;
String jwtToken = null;

// JWT Token está no form "Bearer token". Remova a palavra Bearer e pegue somente o Token
if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	jwtToken = requestTokenHeader.substring(7);
try {
	usuario = jwtTokenUtil.getUsernameFromToken(jwtToken);
} catch (IllegalArgumentException e) {
	System.out.println("Não foi possivel resgatar o Token!");
} catch (ExpiredJwtException e) {
	System.out.println("Token Expirado!");
}
} else {
	logger.warn("O token não possui as iniciais do titular! Por favor, verifique.");
}

// Tendo o token, valide o.
if (usuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(usuario);



if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
			userDetails, null, userDetails.getAuthorities());
	usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
}
}
chain.doFilter(request, response);
}

}