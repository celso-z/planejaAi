package com.UFJF.planejaai.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.UFJF.planejaai.domain.Evento;
import com.UFJF.planejaai.repositories.EventoRepository;

public class EventoService {
	private EventoRepository eventoRepository;

	public Evento findById(Long eventoId) {
		Optional<Evento> atividade = eventoRepository.findById(eventoId);
		return atividade.map(atv -> atv )
	      .orElseThrow(() -> new UsernameNotFoundException("Evento não encontrado!"));
	}

}
