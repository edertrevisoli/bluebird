package com.bluebird.cadastro.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluebird.cadastro.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	List<Menu> findById(String Id);
}


