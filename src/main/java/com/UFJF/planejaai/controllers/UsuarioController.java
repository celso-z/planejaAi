package com.UFJF.planejaai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UFJF.planejaai.domain.Organizador;
import com.UFJF.planejaai.domain.Palestrante;
import com.UFJF.planejaai.domain.Participante;
import com.UFJF.planejaai.services.UsuarioService;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/organizador")
	public ResponseEntity<?> criarOrganizador(@RequestBody Organizador organizador){
		usuarioService.criarUsuario(organizador);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/palestrante")
	public ResponseEntity<?> criarPalestrante(@RequestBody Palestrante palestrante){
		usuarioService.criarUsuario(palestrante);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/participante")
	public ResponseEntity<?> criarParticipante(@RequestBody Participante participante){
		usuarioService.criarUsuario(participante);
		return ResponseEntity.ok().build();
	}
	
}
