package com.bluebird.cadastro.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bluebird.cadastro.dao.IFornecedorDAO;
import com.bluebird.cadastro.model.Fornecedor;
import com.bluebird.comum.dao.impl.GenericDAOImpl;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class FornecedorDAO extends GenericDAOImpl<Fornecedor, Integer> implements IFornecedorDAO {

	public List<Fornecedor> getAll() {
		return em.createQuery("select f from fornecedor f").setMaxResults(100).getResultList();
	}

	public Fornecedor getByEmail(String email) {
		return (Fornecedor) em.createQuery("select f from fornecedor f where email = :email")
				.setParameter("email", email).getSingleResult();
	}

	@Override
	public Fornecedor save(Fornecedor t) {
		return save(t);
	}

	@Override
	public void delete(Integer id) {
		delete(id);
	}

	@Override
	public Fornecedor find(Integer id) {
		return em.find(Fornecedor.class, id);
	}

	@Override
	public Fornecedor update(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
		return null;
	}

}