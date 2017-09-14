package com.boaheninc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/fonts/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.csrf().disable()
	.authorizeRequests()
		.antMatchers("/allLocations").permitAll()
		.antMatchers(HttpMethod.POST, "/authenticate").permitAll()
		.anyRequest().authenticated()
		.and()
	//.formLogin()
		//.loginPage("/login").permitAll()
		//.and()
		.addFilterBefore(new JWTLoginFilter("/authenticate", authenticationManager()), 
				UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	

	//@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
		.inMemoryAuthentication() //authentication with in memory credentials
		.withUser("admin@spotam.com")
		.password("password")
		.roles("ADMIN");
	}

}
