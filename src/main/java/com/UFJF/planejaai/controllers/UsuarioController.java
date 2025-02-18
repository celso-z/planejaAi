package com.UFJF.planejaai.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.UFJF.planejaai.domain.Organizador;
import com.UFJF.planejaai.domain.Palestrante;
import com.UFJF.planejaai.domain.Participante;
import com.UFJF.planejaai.domain.TipoUsuario;
import com.UFJF.planejaai.domain.Usuario;
import com.UFJF.planejaai.domain.UsuarioDTO;
import com.UFJF.planejaai.services.UsuarioService;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/organizador")
	public ResponseEntity<?> criarOrganizador(@RequestBody Organizador organizador) {
		try {
			usuarioService.criarUsuario(organizador);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}

	@PostMapping("/palestrante")
	public ResponseEntity<?> criarPalestrante(@RequestBody Palestrante palestrante) {
		try {
			usuarioService.criarUsuario(palestrante);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}

	@PostMapping("/participante")
	public ResponseEntity<?> criarParticipante(@RequestBody Participante participante) {
		try {
			usuarioService.criarUsuario(participante);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/usuarios")
	public List<UsuarioDTO> getUsuarios() {
		return usuarioService.getAllUsuarios();
	}

	@GetMapping("/usuario")
	public ResponseEntity<?> getUsuario(@RequestParam String email) {
		try {
			UsuarioDTO dto = usuarioService.getUsuario(email);
			if (dto != null) {
				return ResponseEntity.ok(dto);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (UsernameNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno");
		}
	}

	@GetMapping("/organizador")
	public List<UsuarioDTO> getOrganizadores() {
		return usuarioService.getAllUsuariosPorTipo(TipoUsuario.ORGANIZADOR);
	}

	@GetMapping("/palestrante")
	public List<UsuarioDTO> getPalestrante() {
		return usuarioService.getAllUsuariosPorTipo(TipoUsuario.PALESTRANTE);
	}

	@GetMapping("/participante")
	public List<UsuarioDTO> getParticipante() {
		return usuarioService.getAllUsuariosPorTipo(TipoUsuario.PARTICIPANTE);
	}

	@DeleteMapping("/usuario")
	public ResponseEntity<?> deleteUsuario(@RequestBody String email) {
		try {
			usuarioService.deleteByEmail(email);
		} catch (UsernameNotFoundException ex) {
		} catch (Exception e) {
			// Caso encontre usuario mas não consiga deletar por erro de banco
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/organizador")
	public ResponseEntity<?> atualizarUsuario(@RequestBody Organizador organizador) {
		try {
			usuarioService.atualizarUsuario(organizador);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().build();
		} catch (UsernameNotFoundException ex) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}

}
