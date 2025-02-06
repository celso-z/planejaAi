package com.UFJF.planejaai.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class InfoUsuario implements UserDetails {
	
	private String email;
	private String senha;
	private List<GrantedAuthority> permissoes;
	
	public InfoUsuario(Usuario usuario) {
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.permissoes = List.of(new SimpleGrantedAuthority(usuario.getTipoUsuario().name()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissoes;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

}
