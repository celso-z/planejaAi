package com.UFJF.planejaai.domain;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(AtividadeId.class)
public class Atividade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date data;
	
	private Integer maxCapacidade;
	
	@Column(nullable = false)
	private String nome, descricao;
	
	@Column(name = "TIPO_ATIVIDADE", nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private TipoAtividade tipoAtividade;
	
	private Integer minutosDuracao;
	
	@Id
	@ManyToOne()
	@JoinColumn(name = "ID_EVENTO")
	private Evento evento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getMaxCapacidade() {
		return maxCapacidade;
	}

	public void setMaxCapacidade(Integer maxCapacidade) {
		this.maxCapacidade = maxCapacidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public Integer getMinutosDuracao() {
		return minutosDuracao;
	}

	public void setMinutosDuracao(Integer minutosDuracao) {
		this.minutosDuracao = minutosDuracao;
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
		Atividade other = (Atividade) obj;
		return Objects.equals(evento, other.evento) && Objects.equals(id, other.id);
	}
	
	

}
