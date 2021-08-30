package projeto.herois.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import projeto.herois.service.JwtUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

@Autowired
private JwtUserDetailsService jwtUserDetailsService;

@Autowired
private JwtTokenUtil jwtTokenUtil;

private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
throws ServletException, IOException {
	try {
	      String jwt = parseJwt(request);
	      if (jwt != null && jwtTokenUtil.validateJwtToken(jwt)) {
	        String username = jwtTokenUtil.getUserNameFromJwtToken(jwt);

	        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
	            userDetails.getAuthorities());
	        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	      }
	    } catch (Exception e) {
	      logger.error("Cannot set user authentication: {}", e.getMessage());
	    }

	    chain.doFilter(request, response);
	  }

	  private String parseJwt(HttpServletRequest request) {
	    String headerAuth = request.getHeader("Authorization");

	    if (headerAuth.startsWith("Bearer ")) {
	      return headerAuth.substring(7, headerAuth.length());
	    }

	    return null;
	  }
	}
