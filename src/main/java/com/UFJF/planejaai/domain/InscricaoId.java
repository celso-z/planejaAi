package com.UFJF.planejaai.domain;

import java.io.Serializable;
import java.util.Objects;


public class InscricaoId implements Serializable {
	private Long id;
	private Participante participante;
	private Atividade atividade;
	
	public Long getId() {
		return id;
	}
	public void setId(Long inscricaoId) {
		this.id = inscricaoId;
	}
	public Participante getParticipante() {
		return participante;
	}
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	
	@Override
	public String toString() {
		return id.toString() + participante.toString();
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	@Override
	public int hashCode() {
		return Objects.hash(atividade, id, participante);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InscricaoId other = (InscricaoId) obj;
		return Objects.equals(atividade, other.atividade) && Objects.equals(id, other.id)
				&& Objects.equals(participante, other.participante);
	}
	
	
	
}
