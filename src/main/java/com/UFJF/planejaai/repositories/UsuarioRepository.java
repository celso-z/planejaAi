package com.UFJF.planejaai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.UFJF.planejaai.domain.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
}
