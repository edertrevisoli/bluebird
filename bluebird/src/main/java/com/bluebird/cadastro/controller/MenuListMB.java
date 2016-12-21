package com.bluebird.cadastro.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bluebird.cadastro.model.Menu;
import com.bluebird.cadastro.service.MenuService;


@Named("menuListMB")
@Scope("view")
public class MenuListMB implements Serializable {

	private static final long serialVersionUID = 4741882241553805840L;

	private List<Menu> menus;

	@Inject
	private MenuService menuService;

	@PostConstruct
	public void init() {
		menus = menuService.getMenus();
	}

	public List<Menu> getmenus() {
		return menus;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
}