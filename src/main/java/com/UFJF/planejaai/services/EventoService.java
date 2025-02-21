package com.UFJF.planejaai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.UFJF.planejaai.domain.Evento;
import com.UFJF.planejaai.domain.EventoDTO;
import com.UFJF.planejaai.repositories.EventoRepository;

public class EventoService {
	private EventoRepository eventoRepository;
	
	public void criarEvento(EventoDTO evento) {
		Evento e = new Evento();
		e.setNome(evento.getNome());
		e.setDataFim(evento.getDataFim());
		e.setDataInicio(evento.getDataInicio());
		e.setDescricao(evento.getDescricao());
		e.setLocal(evento.getLocal());
		e.setMaxAtividades(evento.getMaxAtividades());
		eventoRepository.save(e);
	}

	public Evento findById(Long eventoId) {
		Optional<Evento> atividade = eventoRepository.findById(eventoId);
		return atividade.map(atv -> atv )
	      .orElseThrow(() -> new UsernameNotFoundException("Evento não encontrado!"));
	}
	
	public List<Evento> findAllEventos(){
		return (List<Evento>) eventoRepository.findAll();
	}

}
