package com.UFJF.planejaai.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.UFJF.planejaai.domain.Atividade;
import com.UFJF.planejaai.domain.Inscricao;
import com.UFJF.planejaai.domain.InscricaoId;
import com.UFJF.planejaai.domain.Participante;

public interface InscricaoRepository extends CrudRepository<Inscricao, InscricaoId> {
	Iterable<Inscricao> findAllByParticipante(Participante participante);
	Iterable<Inscricao> findAllByAtividade(Atividade atividade);
	Optional<Inscricao> findById(Long id);
}
