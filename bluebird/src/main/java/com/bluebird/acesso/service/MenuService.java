package com.bluebird.acesso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluebird.acesso.dao.IMenuDAO;
import com.bluebird.acesso.model.Menu;
import com.bluebird.acesso.model.Usuario;
import com.bluebird.acesso.repositories.MenuRepository;

@Service
public class MenuService {

	@Autowired
	private IMenuDAO menuDAO;

	@Autowired
	private MenuRepository menuRepository;

	static List<Menu> list = new ArrayList<Menu>();

	public List<Menu> getMenus() {
		List<Menu> all = menuRepository.findAll();
		for (Object object : all) {
			Menu m = (Menu) object;
			Menu st = new Menu(m.getId(), m.getLabel(), m.getLink());
			list.add(st);
		}
		return list;
	}

	public List<Menu> getMenuById() {
		List<Menu> all = menuRepository.findById("cadastros");
		for (Object object : all) {
			Menu m = (Menu) object;
			Menu st = new Menu(m.getId(), m.getLabel(), m.getLink());
			list.add(st);
		}
		return list;
	}

	public List<Menu> lista(Usuario usuario) {
		return menuDAO.lista(usuario);
	}

	public List<Menu> lista(Menu menu, Usuario usuario) {
		return menuDAO.lista(menu, usuario);
	}

	public List<Menu> listaPermissoes(Usuario usuario) {
		return menuDAO.listaPermissoes(usuario);
	}

	public String getByLink(String link) {
		return menuDAO.getByLink(link);
	}
}
