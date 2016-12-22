package com.bluebird.cadastro.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bluebird.cadastro.dao.IClienteDAO;
import com.bluebird.cadastro.model.Cliente;
import com.bluebird.comum.dao.impl.GenericDAOImpl;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ClienteDAO extends GenericDAOImpl<Cliente, Integer> implements IClienteDAO {

	public List<Cliente> getAll() {
		return em.createQuery("select u from cliente u").setMaxResults(100).getResultList();
	}

	@Override
	public Cliente save(Cliente t) {
		return save(t);
	}

	@Override
	public void delete(Integer id) {
		delete(id);
	}

	@Override
	public Cliente find(Integer id) {
		return em.find(Cliente.class, id);
	}

	@Override
	public Cliente update(Cliente cliente) {
		return null;
	}

}