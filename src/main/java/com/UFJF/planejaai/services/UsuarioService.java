package com.UFJF.planejaai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	
	public List<UsuarioDTO> getAllUsuarios() {
		List<Usuario> usuarios = (List<Usuario>) repositorioUsuario.findAll();
		return usuarios.stream().map((usuario) -> {
			UsuarioDTO dto = new UsuarioDTO(usuario);
			return dto;
			}
		).toList();	
	}
	
	public UsuarioDTO getUsuario(String email){
		Optional<Usuario> usuario = repositorioUsuario.findByEmail(email);
		return usuario.map(UsuarioDTO::new).orElseThrow(()->new UsernameNotFoundException("E-mail não encontrado!"));
	}
	
	public List<UsuarioDTO> getAllUsuariosPorTipo(TipoUsuario tipo){
		List<Usuario> usuarios = (List<Usuario>) repositorioUsuario.findAllByTipoUsuario(tipo);
		return usuarios.stream().map((usuario) -> {
			UsuarioDTO dto = new UsuarioDTO(usuario);
			return dto;
			}
		).toList();	
	}
	
	public void deleteByEmail(String email) {
		Optional<Usuario> paraDeletar = repositorioUsuario.findByEmail(email);
		paraDeletar.ifPresentOrElse(
				(deletado) -> {repositorioUsuario.delete(deletado);},
				() -> {throw new UsernameNotFoundException("E-mail não encontrado!");});
	}
	
	public void atualizarUsuario(Usuario usuario) throws IllegalArgumentException {
		if(usuario == null) {
			throw new IllegalArgumentException("Usuário não pode ser nulo!");
		}
		Optional<Usuario> encontrado = repositorioUsuario.findByEmail(usuario.getEmail());
		encontrado.ifPresentOrElse(
				(entidade) -> {
					usuario.setId(entidade.getId());
					usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
					try{
						repositorioUsuario.save(usuario);
					}catch (DataIntegrityViolationException e) {
				        throw new IllegalArgumentException("Usuário não pode ser atualizado, quebra restrições do banco");
				    }
				},
				() -> {throw new UsernameNotFoundException("E-mail não encontrado!");});
	}
}
