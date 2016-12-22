package com.bluebird.cadastro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "cliente")
@Table(name = "cliente")
@SequenceGenerator(allocationSize = 1, sequenceName = "seq_cliente", name = "seq_cliente_gen")
public class Cliente implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_cliente_gen", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String razaoSocial;

	@Column(nullable = false)
	private String nomeComercial;
	
	public Cliente() {
	
	}

	public Cliente(Integer id, String razaoSocial, String nomeComercial){
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.nomeComercial = nomeComercial;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeComercial() {
		return nomeComercial;
	}

	public void setNomeComercial(String nomeComercial) {
		this.nomeComercial = nomeComercial;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Cliente clone() {

		try {

			return (Cliente) super.clone();

		} catch (CloneNotSupportedException ex) {

			throw new AssertionError();

		}

	}

}
