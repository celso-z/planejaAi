package com.UFJF.planejaai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.UFJF.planejaai.domain.Evento;
import com.UFJF.planejaai.domain.EventoDTO;
import com.UFJF.planejaai.services.EventoService;

public class EventoController {
	@Autowired
	private EventoService eventoService;
	
	@PostMapping("/evento")
	public ResponseEntity<?> criarEvento(@RequestBody EventoDTO evento){
		try {
			eventoService.criarEvento(evento);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/eventos")
	public List<Evento> getAllEventos(){
		return eventoService.findAllEventos();
	}
	
	@GetMapping("/evento/{id}")
	public Evento getAtividadeById(@PathVariable Long id) {
		return eventoService.findById(id);
	}
	
}
