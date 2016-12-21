package com.bluebird.cadastro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluebird.cadastro.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findByNome(String nome);
}
