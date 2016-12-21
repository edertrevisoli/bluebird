package com.bluebird.cadastro.dao;

import java.util.List;

import com.bluebird.cadastro.model.Perfil;
import com.bluebird.cadastro.model.Usuario;
import com.bluebird.comum.dao.GenericDAO;

public interface IPerfilDAO extends GenericDAO<Perfil, Integer> {

	public List<Perfil> getAll();

	public Perfil getById(String Id);
	
}