package com.UFJF.planejaai.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.UFJF.planejaai.domain.Usuario;
import com.UFJF.planejaai.domain.TipoUsuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	Optional<Usuario> findByEmail(String email);
	Iterable<Usuario> findAllByTipoUsuario(TipoUsuario tipo);
}
