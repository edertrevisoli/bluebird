package com.bluebird.cadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluebird.cadastro.dao.IFornecedorDAO;
import com.bluebird.cadastro.model.Fornecedor;
import com.bluebird.cadastro.model.Student;
import com.bluebird.cadastro.repositories.FornecedorRepository;

@Service
public class StudentService {

	@Autowired
	private IFornecedorDAO fornecedorDao;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	static List<Student> list = new ArrayList<Student>();

	public List<Student> getStudents() {
		List<Fornecedor> all = fornecedorRepository.findByRazaoSocial("5S FIVE SOLUTIONS COMERCIO E SERVICOS LTDA.");
		//List<Fornecedor> all = fornRepository.findByRazaoSocial("ACAO LOGISTICA E TRANSPORTES");
		for (Object object : all) {
			Fornecedor forn = (Fornecedor) object;
			Student st = new Student(forn.getId(), forn.getNomeFantasia(), forn.getRazaoSocial());
			list.add(st);
		}
		return list;
	}
}
