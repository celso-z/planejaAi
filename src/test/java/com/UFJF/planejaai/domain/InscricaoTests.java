package com.UFJF.planejaai.domain;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InscricaoTests {
	
	@Test
	void setConfirmadaTest() {
		Inscricao example = new Inscricao();
		
		Assertions.assertFalse(example.isPresencaConfirmada(), "Inscrição deve ser automaticamente inicializada para false");
		example.setPresencaConfirmada(true);
		Assertions.assertTrue(example.isPresencaConfirmada(), "Método setter do campo confirmação de inscrição não funciona!");
		example.setPresencaConfirmada(false);
		Assertions.assertTrue(example.isPresencaConfirmada(), "Classe Inscrição não deve permitir desconfirmar inscrições confirmadas.");
	}
	
	@Test
	void toStringTest() {
		Inscricao example = new Inscricao();
		
		try{
			example.toString();
			Assertions.fail("Classe inscrição permitindo uso de campos não propriamente inicializados");
		}catch(IllegalStateException e){
			
		}
	}
	
	@Test
	void endpointTest() {
		Inscricao example = new Inscricao();
		
		try {
			example.generateEndpointCode();
			Assertions.fail();
		}catch(IllegalStateException e){
			
		}catch(Exception e) {
			Assertions.fail("Implemente ou altere função de hashing");
		}
		
		example.setPresencaConfirmada(true);
		example.setId(Long.valueOf(0));
		example.setParticipante(new Participante());
		example.setAtividade(new Atividade());
		
		try {
			example.generateEndpointCode();
			String endpoint = example.getEndpointCode();
			
			if(endpoint == null || endpoint == "") {
				Assertions.fail("Função geradora de endereço de endpoints não funciona!");
			}
			if(endpoint.length() != 40) {
				Assertions.fail("Função geradora de endereço de enpoints fora de especificação: " +
						"tamanho diferente de 40.");
			}
		}catch(Exception e) {
			Assertions.fail("Função geradora de endereço de endpoints não funciona!");
		}
		
		Inscricao example2 = new Inscricao();
		example2.setPresencaConfirmada(false);
		example2.setId(Long.valueOf(1));
		example2.setParticipante(new Participante());
		example2.setAtividade(new Atividade());
		try {
			example2.generateEndpointCode();
			Assertions.assertNotEquals(example.getEndpointCode(), example2.getEndpointCode(), "Função geradora de endpoints é pura");
		}catch(Exception e) {
			Assertions.fail("Função geradora de endereço de endpoints não funciona!");
		}
	}
	
	
}
