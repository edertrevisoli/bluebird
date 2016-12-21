package com.bluebird.cadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluebird.cadastro.dao.IUsuarioDAO;
import com.bluebird.cadastro.model.Usuario;
import com.bluebird.cadastro.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioDAO usuarioDAO;

	@Autowired
	private UsuarioRepository UsuarioRepository;

	static List<Usuario> list = new ArrayList<Usuario>();

	public List<Usuario> getUsuarios() {
		List<Usuario> all = UsuarioRepository.findByNome("EDER");
		for (Object object : all) {
			Usuario u = (Usuario) object;
			Usuario st = new Usuario(u.getId(), u.getNome(), u.getLogin());
			list.add(st);
		}
		return list;
	}

}

