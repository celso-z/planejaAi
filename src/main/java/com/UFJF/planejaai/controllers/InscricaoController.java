package com.UFJF.planejaai.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UFJF.planejaai.domain.Inscricao;
import com.UFJF.planejaai.domain.InscricaoDTO;
import com.UFJF.planejaai.services.InscricaoService;

@RestController
public class InscricaoController {
	@Autowired
	private InscricaoService inscricaoService;
	
	@PostMapping("/inscricao")
	public ResponseEntity<?> criarAtividade(@RequestBody InscricaoDTO inscricao) throws NoSuchAlgorithmException{
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
	
	@GetMapping("/inscricao/{participanteId}/{atividadeId}")
	public Inscricao getInscricaoById(@PathVariable Long participanteId, @PathVariable Long atividadeId) {
		return inscricaoService.getInscricao(participanteId, atividadeId);
	}
	
	@DeleteMapping("/inscricao/{participanteId}/{atividadeId}")
	public void deletaInscricaoById(@PathVariable Long participanteId, @PathVariable Long atividadeId) {
		inscricaoService.deletaInscricao(participanteId, atividadeId);
	}
	
	@GetMapping("/inscricao/{participanteId}")
	public List<Inscricao> getInscricaoById(@PathVariable Long participanteId) {
		return inscricaoService.getAllInscricoesPorParticipante(participanteId);
	}
}
