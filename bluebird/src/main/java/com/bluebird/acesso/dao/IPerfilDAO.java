package com.bluebird.acesso.dao;

import java.util.List;

import com.bluebird.acesso.model.Perfil;
import com.bluebird.acesso.model.Usuario;
import com.bluebird.comum.dao.GenericDAO;

public interface IPerfilDAO extends GenericDAO<Perfil, Integer> {

	public List<Perfil> getAll();

	public Perfil getById(String Id);
	
}