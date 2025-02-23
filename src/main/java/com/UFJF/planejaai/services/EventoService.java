package com.UFJF.planejaai.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.UFJF.planejaai.domain.Evento;
import com.UFJF.planejaai.domain.EventoDTO;
import com.UFJF.planejaai.domain.eventoUpdateDTO;
import com.UFJF.planejaai.repositories.EventoRepository;

@Service
public class EventoService {
	 @Autowired
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
		Optional<Evento> evento = eventoRepository.findById(eventoId);
		return evento.map(atv -> atv )
	      .orElseThrow(() -> new UsernameNotFoundException("Evento não encontrado!"));
	}
	
	public void deleteById(Long eventoId) {
		Optional<Evento> evento = eventoRepository.findById(eventoId);
		Evento e = evento.map(evt -> evt)
	      .orElseThrow(() -> new UsernameNotFoundException("Evento não encontrado!"));
		eventoRepository.delete(e);
	}
	
	public List<Evento> findAllEventos(){
		return (List<Evento>) eventoRepository.findAll();
	}

	public Evento atualizaEvento(Long eventoId, eventoUpdateDTO updateRequest) {
		Optional<Evento> evento = eventoRepository.findById(eventoId);
		Evento e = evento.map(evt -> evt)
	      .orElseThrow(() -> new UsernameNotFoundException("Evento não encontrado!"));
		if (updateRequest.nome() != null) {
            e.setNome(updateRequest.nome());
        }
		if (updateRequest.descricao() != null) {
            e.setDescricao(updateRequest.descricao());
        }
		if (updateRequest.local() != null) {
            e.setLocal(updateRequest.local());
        }
		if (updateRequest.maxAtividade() != null) {
            e.setMaxAtividades(updateRequest.maxAtividade());
        }
		if(updateRequest.dataInicio() != null) {
			e.setDataInicio(updateRequest.dataInicio());
		}
		if(updateRequest.dataFim() != null) {
			e.setDataFim(updateRequest.dataFim());
		}
		eventoRepository.save(e);
		return e;
	}

}
