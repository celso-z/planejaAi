package com.UFJF.planejaai.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.UFJF.planejaai.domain.Atividade;
import com.UFJF.planejaai.domain.AtividadeDTO;
import com.UFJF.planejaai.domain.Evento;
import com.UFJF.planejaai.domain.TipoAtividade;
import com.UFJF.planejaai.repositories.AtividadeRepository;
import com.UFJF.planejaai.services.EventoService;

import ch.qos.logback.core.joran.conditional.IfAction;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository atividadeRepository;
	@Autowired
	private EventoService eventoService;
	@Autowired
	private SchedulerService schedulerService;
	
	public void criarAtividade(AtividadeDTO atividadeDto) {
		if(atividadeDto == null) {
			throw new IllegalArgumentException("Atividade não pode ser nula!");
		}
		Atividade atividade = new Atividade();
		atividade.setNome(atividadeDto.getNome());
		atividade.setDescricao(atividadeDto.getDescricao());
		atividade.setEvento(
				eventoService.findById(atividadeDto.getEventoId()));
		atividade.setMaxCapacidade(atividadeDto.getCapacidadeMaxima());
		atividade.setData(atividadeDto.getData());
		atividade.setMinutosDuracao(atividadeDto.getDuracao());
		atividade.setTipoAtividade(TipoAtividade.MINICURSO); //Temporário
		atividadeRepository.save(atividade);
		schedulerService.scheduleNotificacao(atividade);
	}
	
	public List<Atividade> getAllAtividades(){
		return (List<Atividade>) atividadeRepository.findAll();
	}
	
	public List<Atividade> getAllAtividadesEvento(Long eventoId){
		Evento evento = eventoService.findById(eventoId);
		return (List<Atividade>) atividadeRepository.findAllByEvento(evento);
	}
	
	public Atividade getById(Long id){
		Optional<Atividade> atividade = atividadeRepository.findById(id);
		return atividade.map(atv -> atv )
	      .orElseThrow(() -> new UsernameNotFoundException("Atividade não encontrada!"));
	}
	public void deleteById(Long id){
		Optional<Atividade> atividade = atividadeRepository.findById(id);
		atividade.ifPresentOrElse(atv -> {atividadeRepository.delete(atv);},
				() -> {throw new UsernameNotFoundException("Atividade não encontrada!");});
	}
	public Atividade updateAtividade(Atividade atividade) {
		if(atividade.getId() == null) return null;
		Optional<Atividade> atividadeAtual = atividadeRepository.findById(atividade.getId());
		if(atividadeAtual.isPresent()) {
			Atividade atv = atividadeAtual.get();
			if(atividade.getData() != null) atv.setData(atividade.getData());
			if(atividade.getMaxCapacidade() != null) atv.setMaxCapacidade(atividade.getMaxCapacidade());
			if(atividade.getNome() != null) atv.setNome(atividade.getNome());
			if(atividade.getDescricao() != null) atv.setDescricao(atividade.getDescricao());
			if(atividade.getTipoAtividade() != null) atv.setTipoAtividade(atividade.getTipoAtividade());
			if(atividade.getMinutosDuracao() != null) atv.setMinutosDuracao(atividade.getMinutosDuracao());
			atividadeRepository.save(atv);
			return atv;
		}
		else {
			return null;
		}
	}
}
