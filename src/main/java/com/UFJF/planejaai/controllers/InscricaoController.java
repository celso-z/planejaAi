package com.UFJF.planejaai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.UFJF.planejaai.domain.Inscricao;
import com.UFJF.planejaai.domain.InscricaoDTO;
import com.UFJF.planejaai.services.InscricaoService;

public class InscricaoController {
	@Autowired
	private InscricaoService inscricaoService;
	
	@PostMapping("/inscricao")
	public ResponseEntity<?> criarAtividade(@RequestBody InscricaoDTO inscricao){
		try {
			inscricaoService.criarInscricao(inscricao);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/inscricoes")
	public List<Inscricao> getAllAtividades(){
		return inscricaoService.getAllInscricoes();
	}
	
	@DeleteMapping("/inscricao/{id}")
	public Inscricao getInscricaoById(@PathVariable Long id) {
		return inscricaoService.getInscricaoPorId(id);
	}
}
