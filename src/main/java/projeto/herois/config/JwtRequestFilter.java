package projeto.herois.config;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
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

String username = null;
String jwtToken = null;

if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
	jwtToken = requestTokenHeader.substring(7);
try {
	username = jwtTokenUtil.getUsernameFromToken(jwtToken);
} catch (IllegalArgumentException e) {
	System.out.println("Não foi possivel resgatar o Token!");
} catch (ExpiredJwtException e) {
	System.out.println("Token Expirado!");
}
} else {
	logger.warn("O token JWT não começa com a string do portador! Por favor, verifique.");
}

if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);



if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
			userDetails, null, userDetails.getAuthorities());
	String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
	System.out.println("Authorities granted : " + authorities);
	usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
}
else {
    System.out.println("Not Valid Token");
}

}else {
    System.out.println("No Token");}

chain.doFilter(request, response);
}
}
