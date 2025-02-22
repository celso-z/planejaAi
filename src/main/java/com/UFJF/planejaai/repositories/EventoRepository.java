package com.UFJF.planejaai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.UFJF.planejaai.domain.Evento;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long> {
}
