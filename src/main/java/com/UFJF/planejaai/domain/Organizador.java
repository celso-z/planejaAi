package com.UFJF.planejaai.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ORGANIZADOR")
public class Organizador extends Usuario {
	public Organizador() {
		super();
		this.setTipoUsuario(TipoUsuario.ORGANIZADOR);
	}
}
