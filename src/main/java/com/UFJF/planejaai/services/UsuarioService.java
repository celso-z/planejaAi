package com.UFJF.planejaai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.UFJF.planejaai.domain.*;
import com.UFJF.planejaai.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repositorioUsuario;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void criarUsuario(Usuario usuario) throws IllegalArgumentException {
		if(usuario == null) {
			throw new IllegalArgumentException("Usuário não pode ser nulo!");
		}
		if(usuario.getTipoUsuario() == null) {
			throw new IllegalArgumentException("Usuário deve ter tipo especificado!");
		}
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		try {
	        switch (usuario.getTipoUsuario()) {
	            case ORGANIZADOR -> repositorioUsuario.save((Organizador) usuario);
	            case PALESTRANTE -> repositorioUsuario.save((Palestrante) usuario);
	            case PARTICIPANTE -> repositorioUsuario.save((Participante) usuario);
	            default -> throw new IllegalArgumentException("Tipo de usuário desconhecido");
	        }
	    } catch (DataIntegrityViolationException e) {
	        throw new IllegalArgumentException("Usuário não pode ser inserido, quebra restrições do banco");
	    }
		
	}
	
}
