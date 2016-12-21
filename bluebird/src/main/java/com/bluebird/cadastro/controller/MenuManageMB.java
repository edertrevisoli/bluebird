package com.bluebird.cadastro.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bluebird.cadastro.model.Menu;
import com.bluebird.cadastro.model.Usuario;
import com.bluebird.cadastro.service.MenuService;


@Named("menuManageMB")
@Scope("view")
public class MenuManageMB implements Serializable {

	private static final long serialVersionUID = 4741882241553805840L;

	private List<Menu> menus;
	private List<Menu> menuSup;
	private List<Menu> menuInt;
	
	private Usuario admin = new Usuario(1,"admin","admin@");
	
	
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

	public List<Menu> getMenuSup() {
		
		if (menuSup == null)
			menuSup = menuService.lista(admin);

		return menuSup;

	}
	
	public void setMenuSup(List<Menu> menuSup) {
		this.menuSup = menuSup;
	}
	
	public List<Menu> getMenuInt(String menu) {
		
		Menu menuSup1 = new Menu();
		menuSup1.setId(menu);

		if (menuInt == null)
			menuInt = menuService.lista(menuSup1, admin);

		return menuInt;

	}

	public void setMenuInt(List<Menu> menuInt) {
		this.menuInt = menuInt;
	}
	
}