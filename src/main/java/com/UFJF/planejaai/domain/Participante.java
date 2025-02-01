package com.UFJF.planejaai.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PARTICIPANTE")
public class Participante extends Usuario {
	public Participante() {
		super();
		this.setTipoUsuario(TipoUsuario.PARTICIPANTE);
	}

}
