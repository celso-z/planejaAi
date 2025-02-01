package com.UFJF.planejaai.domain;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity(name = "Usuario")
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_USUARIO")
public abstract class Usuario {
	@Column(nullable = false)
	@NotBlank
	private String nome;
	
	@Id
	@Column(nullable = false)
	@NotBlank
	private String email;
	
	@Column(nullable = false)
	@NotBlank
	private String senha;
	
	@Column(name = "TIPO_USUARIO", nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;

	protected void setNome(String nome) {
		this.nome = nome;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	protected void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	
}
