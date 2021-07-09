package org.prueba.repository;

import org.prueba.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Usuariorepository extends JpaRepository<Usuario, Integer> {

	public Usuario findByUsuarioOrEmail(String username, String email);
}
