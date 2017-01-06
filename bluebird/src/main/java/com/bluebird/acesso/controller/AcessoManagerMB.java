package com.bluebird.acesso.controller;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import com.bluebird.acesso.SessionUtil;
import com.bluebird.acesso.model.Usuario;

@Named("acessoManagerMB")
@SessionScoped
public class AcessoManagerMB {

	public Usuario getUsuario() {
		return (Usuario) SessionUtil.getSession().getAttribute("usuario");
	}

}
