package com.bluebird.acesso.dao;

import java.util.List;

import com.bluebird.acesso.model.Menu;
import com.bluebird.acesso.model.Usuario;
import com.bluebird.comum.dao.GenericDAO;

public interface IMenuDAO extends GenericDAO<Menu, Integer> {

	public List<Menu> getAll();

	public Menu getById(String Id);
	
	public List<Menu> lista(Usuario usuario);
	
	public List<Menu> lista(Menu menu, Usuario usuario);

	public List<Menu> listaPermissoes(Usuario usuario);
	
	public String getByLink(String link);

}