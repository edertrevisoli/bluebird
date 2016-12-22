package com.bluebird.cadastro.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bluebird.acesso.controller.AcessoManagerMB;
import com.bluebird.cadastro.model.Menu;
import com.bluebird.cadastro.service.MenuService;


@Named("menuManagerMB")
@Scope("view")
public class MenuManagerMB implements Serializable {

	private static final long serialVersionUID = 4741882241553805840L;

	private List<Menu> menus;
	private List<Menu> menuSup;
	private List<Menu> menuInt;
		
	@Inject
	private MenuService menuService;
	@Inject
	private AcessoManagerMB acesso;

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

	public List<Menu> getMenuSup() {
		
		if (menuSup == null)
			menuSup = menuService.lista(acesso.getUsuario());

		return menuSup;

	}
	
	public void setMenuSup(List<Menu> menuSup) {
		this.menuSup = menuSup;
	}
	
	public List<Menu> getMenuInt(String menu) {
		
		Menu menuSup1 = new Menu();
		menuSup1.setId(menu);

		if (menuInt == null)
			menuInt = menuService.lista(menuSup1, acesso.getUsuario());

		return menuInt;

	}

	public void setMenuInt(List<Menu> menuInt) {
		this.menuInt = menuInt;
	}
	
}