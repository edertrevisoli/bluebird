package com.bluebird.acesso.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluebird.acesso.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findByNome(String nome);
}
