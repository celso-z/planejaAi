package com.UFJF.planejaai.services;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.UFJF.planejaai.domain.InfoUsuario;
import com.UFJF.planejaai.domain.Participante;
import com.UFJF.planejaai.domain.Usuario;
import com.UFJF.planejaai.repositories.UsuarioRepository;

@SpringBootTest
public class InfoUsuarioTests {
	
	@Autowired
	private InfoUsuarioService infoUsuario;
	@Autowired
	private UsuarioService usuarioServ;
	private final String dummyPasswordHash = "";
	
	/*
	@BeforeAll
	static void init() {
		
		
	}*/
	
	void teardown() {
		usuarioServ.deleteByEmail("dummy");
	}

	@Test
	void loadUserByName() {
		Usuario dummy = new Participante();
		dummy.setEmail("dummy");
		dummy.setNome("dummy");
		dummy.setSenha("dummy");
		usuarioServ.criarUsuario(dummy);
		
		InfoUsuario dadosUsuario;
		String username = null;
		
		try {
			infoUsuario.loadUserByUsername(username);
			Assertions.fail("Usuario encontrado passando NULL para método de busca por nome");
		}catch(UsernameNotFoundException ex){
			
		}catch(Exception e) {
			Assertions.fail("Exceção não tratada ao passar nome de usuário nulo.\nClasse InfoUsuarioService");
		}
		
		username = "dummy";
		
		try {
			dadosUsuario = (InfoUsuario) infoUsuario.loadUserByUsername(username);
			Assertions.assertAll("Verificação de credenciais de usuário",
					() -> Assertions.assertEquals(dadosUsuario.getUsername(), "dummy"),
					() -> Assertions.assertEquals(dadosUsuario.getPassword(), dummyPasswordHash)
					//TODO: Checar também se altoridade de participante foi setada
					);
		}catch(UsernameNotFoundException ex){
			Assertions.fail("Usuário existente não encontrado no banco!");
		}catch(Exception e) {
			Assertions.fail("Exceção não tratada ao passar nome de usuário nulo.\nClasse InfoUsuarioService");
		}
		
	}
}
