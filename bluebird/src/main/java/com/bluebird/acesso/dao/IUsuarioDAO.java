package com.bluebird.acesso.dao;

import java.util.List;

import com.bluebird.acesso.model.Usuario;
import com.bluebird.comum.dao.GenericDAO;

public interface IUsuarioDAO extends GenericDAO<Usuario, Integer> {

	public List<Usuario> getAll();

	public Usuario getByLogin(String login);

}