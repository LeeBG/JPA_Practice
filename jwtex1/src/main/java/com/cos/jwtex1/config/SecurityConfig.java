package com.cos.jwtex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.jwtex1.config.jwt.JwtLoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception{
		return authenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Bearer Auth : 토큰 기반 인증 방식 : JWT혹은 OAuth에 대한 토큰 사용
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.addFilter(new JwtLoginFilter(authenticationManager()))
		//.addFilter(null)
		.csrf().disable()
		.formLogin().disable()
		.httpBasic().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//원래 서버가STATEFUL
		.and()
		.authorizeRequests()
		.antMatchers("/user/**").access("hasRole('ROLE_USER')")
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll();
	}

}
