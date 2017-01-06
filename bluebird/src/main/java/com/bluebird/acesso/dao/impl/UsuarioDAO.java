package com.bluebird.acesso.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bluebird.acesso.dao.IUsuarioDAO;
import com.bluebird.acesso.model.Usuario;
import com.bluebird.comum.dao.impl.GenericDAOImpl;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class UsuarioDAO extends GenericDAOImpl<Usuario, Integer> implements IUsuarioDAO {

	public List<Usuario> getAll() {
		return em.createQuery("select u from usuario u").setMaxResults(100).getResultList();
	}

	public Usuario getByLogin(String login) {
		return (Usuario) em.createQuery("select u from usuario u where login = :login")
				.setParameter("login", login).getSingleResult();
	}
	
	@Override
	public Usuario save(Usuario t) {
		return save(t);
	}

	@Override
	public void delete(Integer id) {
		delete(id);
	}

	@Override
	public Usuario find(Integer id) {
		return em.find(Usuario.class, id);
	}

	@Override
	public Usuario update(Usuario Usuario) {
		return null;
	}

}