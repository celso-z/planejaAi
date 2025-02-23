package com.UFJF.planejaai.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.UFJF.planejaai.domain.Atividade;
import com.UFJF.planejaai.domain.Inscricao;
import com.UFJF.planejaai.domain.InscricaoId;
import com.UFJF.planejaai.domain.Participante;

@Repository
public interface InscricaoRepository extends CrudRepository<Inscricao, InscricaoId> {
	Iterable<Inscricao> findAllByParticipante(Participante participante);
	Iterable<Inscricao> findAllByAtividade(Atividade atividade);
	@Query("SELECT i FROM Inscricao i WHERE i.participante.id = :participanteId AND i.atividade.id = :atividadeId")
    Optional<Inscricao> findInscricao(@Param("participanteId") Long participanteId, 
                                          @Param("atividadeId") Long atividadeId);
}
