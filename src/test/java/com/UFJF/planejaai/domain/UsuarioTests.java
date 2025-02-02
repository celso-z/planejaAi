package com.UFJF.planejaai.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;


@SpringBootTest
public class UsuarioTests {
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	@Test
	void constraintsTest() {
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Usuario>> violations;
		Usuario example = new Participante();
		example.setNome("");
		example.setEmail("user@system.com");
		example.setSenha("hardcodedValue");
		
		violations = validator.validate(example);
		Assertions.assertTrue(violations.size() == 1, "Validação de campo nome vazio incorreta!");
		violations.clear();
		example.setNome("user");
		example.setEmail("");
		
		violations = validator.validate(example);
		Assertions.assertTrue(violations.size() == 1, "Validação de campo email vazio incorreta!");
		violations.clear();
		example.setEmail("user@system.com");
		example.setSenha("");
		
		violations = validator.validate(example);
		Assertions.assertTrue(violations.size() == 1, "Validação de campo senha vazio incorreta!");
		
		
	}
}
