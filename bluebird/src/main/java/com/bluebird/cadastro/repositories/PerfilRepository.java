package com.bluebird.cadastro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluebird.cadastro.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	List<Perfil> findById(String Id);
}


