package com.UFJF.planejaai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.UFJF.planejaai.repositories.AtividadeRepository;
import com.UFJF.planejaai.repositories.InscricaoRepository;
import com.UFJF.planejaai.repositories.UsuarioRepository;
import com.UFJF.planejaai.domain.Inscricao;
import com.UFJF.planejaai.domain.InscricaoDTO;
import com.UFJF.planejaai.domain.Participante;
import com.UFJF.planejaai.domain.Atividade;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class InscricaoService {

	@Autowired
	private InscricaoRepository repositorioInscricao;
	@Autowired 
	private UsuarioRepository participanteRepository;
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	public void criarInscricao(InscricaoDTO inscricaoDto) throws NoSuchAlgorithmException {
		Participante participante = (Participante) participanteRepository.findById(inscricaoDto.idParticipante())
			.map(part -> part)
			.orElseThrow(() -> new UsernameNotFoundException("Participante não encontrado!"));
		Atividade atividade = atividadeRepository.findById(inscricaoDto.idAtividade())
			.map(atv -> atv)
			.orElseThrow(() -> new UsernameNotFoundException("Atividade não encontrada!"));
		if(participante == null || atividade == null) return;
		Inscricao inscricao = new Inscricao();
		inscricao.setPresencaConfirmada(false);
		inscricao.setAtividade(atividade);
		inscricao.setParticipante(participante);
		inscricao.generateEndpointCode();
		repositorioInscricao.save(inscricao);
	}
	
	public List<Inscricao> getAllInscricoesPorAtividade(Atividade atividade){
		if(atividade == null) throw new IllegalArgumentException("Atividade não pode ser nula!");
		return (List<Inscricao>) repositorioInscricao.findAllByAtividade(atividade);
	}
	
	public List<Inscricao> getAllInscricoesPorParticipante(Long participanteId){
		Participante participante = (Participante) participanteRepository.findById(participanteId).get();
		if(participante == null) throw new IllegalArgumentException("Participante não encontrado!");
		return (List<Inscricao>) repositorioInscricao.findAllByParticipante(participante);
	}
	
	public Inscricao getInscricao(Long participanteId, Long atividadeId) {
		if(participanteId < 0 || atividadeId < 0 ) throw new IllegalArgumentException("ID não pode ser nulo ou negativo!");
		Optional<Inscricao> inscricao = repositorioInscricao.findInscricao(participanteId, atividadeId);
		if(inscricao.isEmpty()) throw new IllegalArgumentException("Inscricao não encontrada!");
		return inscricao.get();
	}
	
	public void deletaInscricao(Long participanteId, Long atividadeId) {
		if(participanteId < 0 || atividadeId < 0 ) throw new IllegalArgumentException("ID não pode ser nulo ou negativo!");
		Optional<Inscricao> inscricao = repositorioInscricao.findInscricao(participanteId, atividadeId);
		if(inscricao.isEmpty()) throw new IllegalArgumentException("Inscricao não encontrada!");
		repositorioInscricao.delete(inscricao.get());
	}
	
	public List<Inscricao> getAllInscricoes(){
		return (List<Inscricao>) repositorioInscricao.findAll();
	}
}
