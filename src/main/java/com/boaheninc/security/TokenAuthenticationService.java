package com.boaheninc.security;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class TokenAuthenticationService {
	
	  static final long EXPIRATIONTIME = 60_000_000; //864_000_000; // 10 days
	  static final String SECRET = "ThisIsASecret";
	  static final String TOKEN_PREFIX = "SpotAm";
	  static final String HEADER_STRING = "Authorization";
	  
	  static void addAuthentication(HttpServletResponse response, String username){
		  String JWT = Jwts.builder()
				  .setSubject(username)
				  .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				  .signWith(SignatureAlgorithm.HS256, SECRET)
				  .compact();
		  response
		  .setHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	  }
	  
	  static Authentication getAuthentication(HttpServletRequest request){
		  String token = request.getHeader(HEADER_STRING);
		  if(token != null){
			  //parse the token
			  String user=Jwts.parser()
					  .setSigningKey(SECRET)
					  .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					  .getBody()
					  .getSubject();
			  
	return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
		  }
		  return null;
	  }

	private static Collection<? extends GrantedAuthority> emptyList() {
		// TODO Auto-generated method stub
		return null;
	}

}
