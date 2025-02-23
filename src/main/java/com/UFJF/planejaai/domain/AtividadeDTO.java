package com.UFJF.planejaai.domain;

import java.time.LocalDateTime;

public class AtividadeDTO {
	private String nome;
	private String descricao;
	private LocalDateTime data;
	private String local;
	private Integer capacidadeMaxima;
	private Integer tipoAtividade;
	private Integer duracao;
	private Long eventoId;
	
	public AtividadeDTO(String nome, String descricao, LocalDateTime data, String local, Integer capacidadeMaxima,
			Integer tipoAtividade, Integer duracao, Long eventoId) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
		this.local = local;
		this.capacidadeMaxima = capacidadeMaxima;
		this.tipoAtividade = tipoAtividade;
		this.duracao = duracao;
		this.eventoId = eventoId;
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
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Integer getCapacidadeMaxima() {
		return capacidadeMaxima;
	}
	public void setCapacidadeMaxima(Integer capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}
	public Integer getTipoAtividade() {
		return tipoAtividade;
	}
	public void setTipoAtividade(Integer tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}
	public Integer getDuracao() {
		return duracao;
	}
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	public Long getEventoId() {
		return eventoId;
	}
	public void setEventoId(Long eventoId) {
		this.eventoId = eventoId;
	}
	
}
