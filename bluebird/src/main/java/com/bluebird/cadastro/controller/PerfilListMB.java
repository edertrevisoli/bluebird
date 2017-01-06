package com.bluebird.cadastro.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bluebird.acesso.model.Perfil;
import com.bluebird.acesso.service.PerfilService;


@Named("PerfilListMB")
@Scope("view")
public class PerfilListMB implements Serializable {

	private static final long serialVersionUID = 4741882241553805840L;

	private List<Perfil> Perfils;

	@Inject
	private PerfilService PerfilService;

	@PostConstruct
	public void init() {
		Perfils = PerfilService.getPerfils();
	}

	public List<Perfil> getPerfils() {
		return Perfils;
	}

	public void setPerfilService(PerfilService PerfilService) {
		this.PerfilService = PerfilService;
	}
	
}