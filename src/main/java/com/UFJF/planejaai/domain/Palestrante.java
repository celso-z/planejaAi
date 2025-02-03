package com.UFJF.planejaai.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PALESTRANTE")
public class Palestrante extends Usuario {
	public Palestrante() {
		super();
		this.setTipoUsuario(TipoUsuario.PALESTRANTE);
	}
}
