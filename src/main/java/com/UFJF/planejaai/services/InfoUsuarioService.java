package com.UFJF.planejaai.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.UFJF.planejaai.domain.InfoUsuario;
import com.UFJF.planejaai.domain.Usuario;
import com.UFJF.planejaai.repositories.UsuarioRepository;

@Service
public class InfoUsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repositorioUsuarios;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repositorioUsuarios.findByEmail(email);
        return usuario.map(InfoUsuario::new).orElseThrow(()->new UsernameNotFoundException("E-mail não encontrado!"));
	}

}
