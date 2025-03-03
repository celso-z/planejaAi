package com.UFJF.planejaai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UFJF.planejaai.domain.AtividadeDTO;
import com.UFJF.planejaai.domain.Atividade;
import com.UFJF.planejaai.services.AtividadeService;

@RestController 

public class AtividadeController {
	@Autowired
	private AtividadeService atividadeService;
	
	@PostMapping("/atividade")
	public ResponseEntity<?> criarAtividade(@RequestBody AtividadeDTO atividade){
		try {
			atividadeService.criarAtividade(atividade);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/atividades")
	public List<Atividade> getAllAtividades(){
		return atividadeService.getAllAtividades();
	}
	
	@GetMapping("/atividade/{id}")
	public void getAtividadeById(@PathVariable Long id) {
		atividadeService.getById(id);
	}
	
	@DeleteMapping("/atividade/{id}")
	public void deleteAtividadeById(@PathVariable Long id) {
		atividadeService.deleteById(id);
	}
	
	@GetMapping("/atividades/{eventoId}")
	public void getAtividadeByEvento(@PathVariable Long eventoId) {
		atividadeService.getById(eventoId);
	}
	
	@PatchMapping("/atividade")
	public ResponseEntity<?> atualizaEvento(@RequestBody Atividade atividade) {
		Atividade atv = atividadeService.updateAtividade(atividade);
		if(atv == null) return ResponseEntity.badRequest().build();
		else return ResponseEntity.ok(atv);
		
	}
	
}
