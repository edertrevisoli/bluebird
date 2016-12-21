package com.bluebird.cadastro.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "perfil")
@Table(name = "perfil")
@SequenceGenerator(allocationSize = 1, sequenceName = "seq_perfil", name = "seq_perfil_gen")
public class Perfil implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_perfil_gen", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private String descricao;
		
	@ManyToMany(targetEntity = Menu.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "permissoes", joinColumns = { @JoinColumn(name = "perfil_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id", referencedColumnName = "id") })
	private List<Menu> permissoes;

	
	public Perfil() {
		
	}

	public Perfil(Integer id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Menu> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Menu> permissoes) {
		this.permissoes = permissoes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Perfil clone() {

		try {

			return (Perfil) super.clone();

		} catch (CloneNotSupportedException ex) {

			throw new AssertionError();

		}

	}

}