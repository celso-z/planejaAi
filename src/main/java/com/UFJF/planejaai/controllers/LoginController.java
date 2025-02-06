package com.UFJF.planejaai.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UFJF.planejaai.domain.CredenciaisUsuario;
import com.UFJF.planejaai.domain.Organizador;
import com.UFJF.planejaai.services.JwtService;
import com.UFJF.planejaai.services.OrganizadorService;

@RestController
public class LoginController {
	private final JwtService jwt;
	private final AuthenticationManager authManager;
	private final OrganizadorService servicoOrganizador;
	
	public LoginController(JwtService jwt, AuthenticationManager authManager, OrganizadorService servicoOrganizador) {
		this.jwt = jwt;
		this.authManager = authManager;
		this.servicoOrganizador = servicoOrganizador;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> getToken(@RequestBody CredenciaisUsuario credenciais){
		UsernamePasswordAuthenticationToken autenticador = new UsernamePasswordAuthenticationToken(
				credenciais.email(), credenciais.senha());
		try {
			Authentication autenticacao = authManager.authenticate(autenticador);
			String jwts = jwt.getToken(autenticacao.getName());
			
			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer" + jwts).header(
					HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization").build();
		//TODO: Tratar separadamente cada uma das três excessões caso implementemos bloqueio/inativação de usuários
		}catch(Exception senhaIncorreta) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
	}
	//SOMENTE PARA TESTES DE API DE VALIDAÇÃO
	@PostMapping("/create")
	public ResponseEntity<?> criarUsuario(@RequestBody Organizador organizador){
		servicoOrganizador.criarOrganizadorPorUsuario(organizador);
		return ResponseEntity.ok().build();
	}
	
}
