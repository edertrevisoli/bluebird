package com.bluebird.cadastro.controller;



import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bluebird.cadastro.model.Cliente;
import com.bluebird.cadastro.service.ClienteService;


@Named("clienteListMB")
@Scope("view")
public class ClienteListMB implements Serializable {

	private static final long serialVersionUID = 4741882241553805840L;

	private List<Cliente> clientes;

	@Inject
	private ClienteService clienteService;

	@PostConstruct
	public void init() {
		//clientes = ClienteService.getClientes();
	}

	public List<Cliente> getclientes() {
		return clientes;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
}