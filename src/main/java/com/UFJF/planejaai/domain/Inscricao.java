package com.UFJF.planejaai.domain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(InscricaoId.class)
public class Inscricao {
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean presencaConfirmada;
	
	@Id
	@ManyToOne
	@JoinColumn(name="ID_PARTICIPANTE")
	private Participante participante;
	
	//@MapsId("atividadeId")
	@Id
	@ManyToOne()
	@JoinColumn(referencedColumnName = "ID", name="ID_ATIVIDADE")
	private Atividade atividade;
	
	@Column(nullable = false, unique = true)
	private String endpointCode;
	
	
	public Participante getParticipante() {
		return participante;
	}

	public boolean isPresencaConfirmada() {
		return presencaConfirmada;
	}

	public void setPresencaConfirmada(Boolean presencaConfirmada) {
		if(this.presencaConfirmada) {
			return;
		}
		this.presencaConfirmada = presencaConfirmada;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	
	@Override
	public String toString() {
		if( participante == null || atividade == null) {
			throw new IllegalStateException("Inscrição não corretamente inicializada!");
		}
		return participante.toString() + atividade.toString();
	}
	
	public void generateEndpointCode() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");

        try {
        	byte[] messageDigest = md.digest(this.toString().getBytes());
        	BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 40) {
                hashtext = "0" + hashtext;
            }
            this.endpointCode = hashtext;
        }catch(IllegalStateException e) {
        	throw new IllegalStateException("Impossível gerar endpoint! Inscrição não inicializada");
        	
        }
        
	}

	public String getEndpointCode() {
		return endpointCode;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

}
