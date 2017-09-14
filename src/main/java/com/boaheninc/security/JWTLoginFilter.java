package com.boaheninc.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.boaheninc.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{

	public JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		User userCreds=new ObjectMapper()
				.readValue(request.getInputStream(), User.class);
				return getAuthenticationManager().authenticate(
                  new UsernamePasswordAuthenticationToken(userCreds.getEmail(), userCreds.getPassword(),
                		  Collections.emptyList()
                		  ));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain, Authentication auth) throws IOException, ServletException{
		TokenAuthenticationService.addAuthentication(response, auth.getName());
		
	}

}
