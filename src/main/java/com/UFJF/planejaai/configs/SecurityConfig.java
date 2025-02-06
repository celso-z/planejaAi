package com.UFJF.planejaai.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.UFJF.planejaai.services.InfoUsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private InfoUsuarioService infoUsuarioService;
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(infoUsuarioService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
    
    @Bean
    SecurityFilterChain filtro(HttpSecurity http) throws Exception{
    	http.csrf((csrf) -> csrf.disable())
    		.sessionManagement((sessionManagement) ->
    			sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		).authorizeHttpRequests((authorizeHttpRequest) ->
    			/* POR ENQUANTO ACEITA TODAS AS REQUISICÕES, EM PRODUÇÃO LEMBRAR DE ALTERAR A 
    			   ÚLTIMA CHAMADA DE PERMITALL PARA AUTHENTICATED
    			*/
    			authorizeHttpRequest.requestMatchers(HttpMethod.POST, "/login").permitAll().anyRequest().permitAll());
    	return http.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
}
