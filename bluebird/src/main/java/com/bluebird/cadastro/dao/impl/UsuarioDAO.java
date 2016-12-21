package com.bluebird.cadastro.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bluebird.cadastro.dao.IUsuarioDAO;
import com.bluebird.cadastro.model.Usuario;
import com.bluebird.comum.dao.impl.GenericDAOImpl;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class UsuarioDAO extends GenericDAOImpl<Usuario, Integer> implements IUsuarioDAO {

	public List<Usuario> getAll() {
		return em.createQuery("select f from usuario f").setMaxResults(100).getResultList();
	}

	public Usuario getByNome(String nome) {
		return (Usuario) em.createQuery("select u from usuario f where nome = :nome")
				.setParameter("nome", nome).getSingleResult();
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
		// TODO Auto-generated method stub
		return null;
	}

}