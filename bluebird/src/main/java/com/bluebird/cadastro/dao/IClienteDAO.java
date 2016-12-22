package com.bluebird.cadastro.dao;

import java.util.List;

import com.bluebird.cadastro.model.Cliente;
import com.bluebird.comum.dao.GenericDAO;

public interface IClienteDAO extends GenericDAO<Cliente, Integer> {

	public List<Cliente> getAll();

}