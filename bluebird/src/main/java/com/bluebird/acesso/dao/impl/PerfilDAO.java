package com.bluebird.acesso.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bluebird.acesso.dao.IPerfilDAO;
import com.bluebird.acesso.model.Perfil;
import com.bluebird.comum.dao.impl.GenericDAOImpl;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class PerfilDAO extends GenericDAOImpl<Perfil, Integer> implements IPerfilDAO {

	public List<Perfil> getAll() {
		return em.createQuery("select m from Perfil m").getResultList();
	}

	public Perfil getById(String id) {
		return (Perfil) em.createQuery("select m from Perfil m where id = :id")
				.setParameter("id", id).getSingleResult();
	}
	
	@Override
	public Perfil save(Perfil t) {
		return save(t);
	}

	@Override
	public void delete(Integer id) {
		delete(id);
	}

	@Override
	public Perfil find(Integer id) {
		return em.find(Perfil.class, id);
	}

	@Override
	public Perfil update(Perfil Perfil) {
		return null;
	}
	
}