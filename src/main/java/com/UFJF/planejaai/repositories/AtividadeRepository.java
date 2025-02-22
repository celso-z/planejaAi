package com.UFJF.planejaai.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.UFJF.planejaai.domain.Atividade;
import com.UFJF.planejaai.domain.Evento;

@Repository
public interface AtividadeRepository extends CrudRepository<Atividade, Long> {
	Iterable<Atividade> findAllByEvento(Evento evento);

	Optional<Atividade> findById(Long id);
}
