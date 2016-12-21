package com.bluebird.cadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluebird.cadastro.dao.IPerfilDAO;
import com.bluebird.cadastro.model.Perfil;
import com.bluebird.cadastro.model.Usuario;
import com.bluebird.cadastro.repositories.PerfilRepository;


@Service
public class PerfilService {

	@Autowired
	private IPerfilDAO PerfilDAO;

	@Autowired
	private PerfilRepository PerfilRepository;

	static List<Perfil> list = new ArrayList<Perfil>();

	public List<Perfil> getPerfils() {
		List<Perfil> all = PerfilRepository.findAll();
		for (Object object : all) {
			Perfil m = (Perfil) object;
			Perfil st = new Perfil(m.getId(), m.getDescricao());
			list.add(st);
		}
		return list;
	}
	
	public List<Perfil> getPerfilById() {
		List<Perfil> all = PerfilRepository.findById("cadastros");
		for (Object object : all) {
			Perfil m = (Perfil) object;
			Perfil st = new Perfil(m.getId(), m.getDescricao());
			list.add(st);
		}
		return list;
	}


}

