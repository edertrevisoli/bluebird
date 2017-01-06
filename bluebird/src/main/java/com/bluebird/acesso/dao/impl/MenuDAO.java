package com.bluebird.acesso.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bluebird.acesso.dao.IMenuDAO;
import com.bluebird.acesso.model.Menu;
import com.bluebird.acesso.model.Usuario;
import com.bluebird.comum.dao.impl.GenericDAOImpl;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class MenuDAO extends GenericDAOImpl<Menu, Integer> implements IMenuDAO {

	public List<Menu> getAll() {
		return em.createQuery("select m from menu m").getResultList();
	}

	public Menu getById(String id) {
		return (Menu) em.createQuery("select m from menu m where id = :id").setParameter("id", id).getSingleResult();
	}

	@Override
	public Menu save(Menu t) {
		return save(t);
	}

	@Override
	public void delete(Integer id) {
		delete(id);
	}

	@Override
	public Menu find(Integer id) {
		return em.find(Menu.class, id);
	}

	@Override
	public Menu update(Menu menu) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> lista(Usuario usuario) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select DISTINCT m.id, m.label,m.link,m.ordem from menu m	");
		sb.append(" inner join permissoes per on  m.id = per.menu_id			");
		sb.append(" inner join perfil p on per.perfil_id = p.id 				");
		sb.append(" inner join usuario u on u.perfil_id = p.id 					");
		sb.append(" where m.menupai_id IS NULL									");
		sb.append(" and u.id = :uid												");
		sb.append(" and m.interno IS FALSE										");
		sb.append(" order by m.ordem											");
		Query query = em.createNativeQuery(sb.toString());

		try {

			List<Object[]> result = query.setParameter("uid", usuario.getId()).getResultList();
			List<Menu> menuList = new ArrayList<Menu>();

			for (Object[] registro : result) {
				menuList.add(new Menu(registro[0].toString(), registro[1].toString(), registro[2].toString()));
			}

			return menuList;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> lista(Menu menu, Usuario usuario) {
		StringBuilder sb = new StringBuilder();

		sb.append(" select DISTINCT m.id, m.label,m.link,m.ordem from menu m	");
		sb.append(" inner join permissoes per on  m.id = per.menu_id			");
		sb.append(" inner join perfil p on per.perfil_id = p.id					");
		sb.append(" inner join usuario u on u.perfil_id = p.id					");
		sb.append(" where m.menupai_id = :mid									");
		sb.append(" and u.id = :uid												");
		sb.append(" and m.interno IS TRUE										");
		sb.append(" order by m.ordem											");
		Query query = em.createNativeQuery(sb.toString());

		try {

			List<Object[]> result = query.setParameter("uid", usuario.getId()).setParameter("mid", menu.getId())
					.getResultList();
			List<Menu> menuList = new ArrayList<Menu>();

			for (Object[] registro : result) {
				menuList.add(new Menu(registro[0].toString(), registro[1].toString(), registro[2].toString()));
			}

			return menuList;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> listaPermissoes(Usuario usuario) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select DISTINCT m.id, m.label,m.link,m.ordem from menu m	");
		sb.append(" inner join permissoes per on  m.id = per.menu_id			");
		sb.append(" inner join perfil p on per.perfil_id = p.id 				");
		sb.append(" inner join usuario u on u.perfil_id = p.id 					");
		sb.append(" where m.menupai_id IS NULL									");
		sb.append(" and u.id = :uid												");
		sb.append(" order by m.ordem											");
		Query query = em.createNativeQuery(sb.toString());

		try {

			List<Object[]> result = query.setParameter("uid", usuario.getId()).getResultList();
			List<Menu> menuList = new ArrayList<Menu>();

			for (Object[] registro : result) {
				menuList.add(new Menu(registro[0].toString(), registro[1].toString(), registro[2].toString()));
			}

			return menuList;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public String getByLink(String link) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select id from menu m       ");
		sb.append(" where m.link = :link        ");

		Query query = em.createNativeQuery(sb.toString());
		query.setParameter("link", link);

		try {
			List<Object> resultList = query.getResultList();

			if (resultList != null && resultList.size() > 0) {
				return resultList.get(0).toString();
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}