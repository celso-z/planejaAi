package com.UFJF.planejaai.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;


public class AtividadeId implements Serializable {
	private Long id;
	private Evento evento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	@Override
	public int hashCode() {
		return Objects.hash(evento, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtividadeId other = (AtividadeId) obj;
		return Objects.equals(evento, other.evento) && Objects.equals(id, other.id);
	}
	
	
	
}
