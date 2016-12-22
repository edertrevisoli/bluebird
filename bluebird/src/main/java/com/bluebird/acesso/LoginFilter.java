package com.bluebird.acesso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import com.bluebird.cadastro.model.Menu;
import com.bluebird.cadastro.model.Usuario;
import com.bluebird.cadastro.service.MenuService;
import com.bluebird.cadastro.service.UsuarioService;

public abstract class LoginFilter implements Filter {

	@Inject
	private UsuarioService usuarioService;
	@Inject
	private MenuService menuService;
	

	protected String getPgInicial(Usuario user) {
		List<Menu> menus = menuService.lista(user);
		return menus.get(0).getLink();
	}

	protected boolean hasUserOnSession(HttpServletRequest request) {
		if (request.getSession() != null && request.getSession().getAttribute("usuario") == null) {
			return false;
		}
		return true;
	}

	protected boolean hasPermission(HttpServletRequest request) {

		Usuario user = getUserOnSession(request);

		@SuppressWarnings("unchecked")
		List<String> perm = (List<String>) request.getSession().getAttribute("perm_" + user.getId());

		if (perm == null || perm.isEmpty()) {
			return false;
		} else if (!request.getRequestURI().contains("inicio")) {
			String idMenu = menuService.getByLink(request.getRequestURI().replaceAll("/nec/view", ".."));
			if (idMenu == null) {
				return true;
			} else {
				if (!perm.contains(idMenu)) {
					return false;
				}
			}
		}
		return true;
		
	}

	protected void putAuthorizationsOnSession(HttpServletRequest request) {
		putUserOnSession(request);
		putPermissionsOnSession(request);
	}

	private void putUserOnSession(HttpServletRequest request) {
		String login = request.getUserPrincipal().getName();
		Usuario user = usuarioService.get(login);
		request.getSession().setAttribute("usuario", user);
	}

	private void putPermissionsOnSession(HttpServletRequest request) {

		Usuario user = getUserOnSession(request);

		List<String> permissoes = new ArrayList<String>(50);
		List<Menu> menus = menuService.listaPermissoes(user);

		if (menus != null && !menus.isEmpty()) {
			for (Menu menu : menus) {
				permissoes.add(menu.getId());
				List<Menu> subMenus = menuService.lista(new Menu(menu.getId()), user);
				if (subMenus != null && !subMenus.isEmpty()) {
					for (Menu subMenu : subMenus) {
						permissoes.add(subMenu.getId());
					}
				}
			}
			request.getSession().setAttribute("perm_" + user.getId(), permissoes);
		}
		
	}

	protected Usuario getUserOnSession(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user != null) {
			return user;
		}
		return null;
	}
}
