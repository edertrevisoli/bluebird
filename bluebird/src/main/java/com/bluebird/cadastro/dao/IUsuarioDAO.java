package com.bluebird.cadastro.dao;

import java.util.List;

import com.bluebird.cadastro.model.Usuario;
import com.bluebird.comum.dao.GenericDAO;

public interface IUsuarioDAO extends GenericDAO<Usuario, Integer> {

	public List<Usuario> getAll();

	public Usuario getByNome(String nome);

}