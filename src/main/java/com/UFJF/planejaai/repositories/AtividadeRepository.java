package com.UFJF.planejaai.repositories;

import org.springframework.data.repository.CrudRepository;
import com.UFJF.planejaai.domain.Atividade;
import com.UFJF.planejaai.domain.AtividadeId;
import com.UFJF.planejaai.domain.Evento;

public interface AtividadeRepository extends CrudRepository<Atividade, AtividadeId> {
	Iterable<Atividade> findAllByEvento(Evento evento);
}
