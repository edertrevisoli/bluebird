package com.bluebird.cadastro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluebird.cadastro.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
}
