package com.UFJF.planejaai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.UFJF.planejaai.domain.Organizador;
import com.UFJF.planejaai.domain.Usuario;
import com.UFJF.planejaai.repositories.OrganizadorRepository;

@Service
public class OrganizadorService {
	@Autowired
	private OrganizadorRepository repositorioOrganizadores;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void criarOrganizador(String nome, String email, String senha) throws IllegalArgumentException {
		if(nome == null || email == null || senha == null) {
			throw new IllegalArgumentException("Campos de nome, e-mail e senha não podem ser nulos!");
		}
		Organizador novoOrg = new Organizador();
		novoOrg.setNome(nome);
		novoOrg.setEmail(email);
		novoOrg.setSenha(passwordEncoder.encode(senha));
		repositorioOrganizadores.save(novoOrg);
		
	}
	public void criarOrganizadorPorUsuario(Usuario usuario) throws IllegalArgumentException {
		if(usuario.getNome() == null || usuario.getEmail() == null || usuario.getSenha() == null) {
			throw new IllegalArgumentException("Campos de nome, e-mail e senha não podem ser nulos!");
		}
		Organizador novoOrg = new Organizador();
		novoOrg.setNome(usuario.getNome());
		novoOrg.setEmail(usuario.getEmail());
		novoOrg.setSenha(passwordEncoder.encode(usuario.getSenha()));
		repositorioOrganizadores.save(novoOrg);
		
	}
}
