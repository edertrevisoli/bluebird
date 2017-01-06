package com.bluebird.cadastro.controller;



import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bluebird.acesso.model.Usuario;
import com.bluebird.acesso.service.UsuarioService;


@Named("usuarioListMB")
@Scope("view")
public class UsuarioListMB implements Serializable {

	private static final long serialVersionUID = 4741882241553805840L;

	private List<Usuario> usuarios;

	@Inject
	private UsuarioService usuarioService;

	@PostConstruct
	public void init() {
		usuarios = usuarioService.getUsuarios();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
}