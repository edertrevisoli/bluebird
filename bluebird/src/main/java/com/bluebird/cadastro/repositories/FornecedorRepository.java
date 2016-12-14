package com.bluebird.cadastro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluebird.cadastro.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
	List<Fornecedor> findByRazaoSocial(String razaoSocial);
}
