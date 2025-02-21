package com.UFJF.planejaai.domain;

import java.sql.Date;

import jakarta.persistence.Column;

public class EventoDTO {
	private String nome;
	private String local;
	private String descricao;
	private Integer maxAtividades;
	private Date dataInicio;
	private Date dataFim;
	public EventoDTO(String nome, String local, String descricao, Integer maxAtividades, Date dataInicio,
			Date dataFim) {
		super();
		this.nome = nome;
		this.local = local;
		this.descricao = descricao;
		this.maxAtividades = maxAtividades;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getMaxAtividades() {
		return maxAtividades;
	}
	public void setMaxAtividades(Integer maxAtividades) {
		this.maxAtividades = maxAtividades;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
}
