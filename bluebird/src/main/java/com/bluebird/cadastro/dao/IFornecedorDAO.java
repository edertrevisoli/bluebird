package com.bluebird.cadastro.dao;

import java.util.List;

import com.bluebird.cadastro.model.Fornecedor;
import com.bluebird.comum.dao.GenericDAO;

public interface IFornecedorDAO extends GenericDAO<Fornecedor, Integer> {

	public List<Fornecedor> getAll();

	public Fornecedor getByEmail(String email);

}