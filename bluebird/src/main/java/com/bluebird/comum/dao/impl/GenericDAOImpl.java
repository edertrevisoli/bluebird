package com.bluebird.comum.dao.impl;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bluebird.comum.dao.GenericDAO;
import com.bluebird.comum.filtro.Cond;
import com.bluebird.comum.filtro.WhereOrderParams;
import com.bluebird.comum.util.ReflectionUtils;


public abstract class GenericDAOImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {

	// An EntityManager will be automatically injected from entityManagerFactory
	// setup on DatabaseConfig class.
	@PersistenceContext
	protected EntityManager em;

	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		Type genericSuperclass;
		Class<?> parametrizedClass = getClass();
		do {
			genericSuperclass = parametrizedClass.getGenericSuperclass();
			if (genericSuperclass instanceof Class) {
				parametrizedClass = (Class<?>) genericSuperclass;
			}
		} while (genericSuperclass != null && !(genericSuperclass instanceof ParameterizedType));
		this.entityClass = (Class<T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
	}

	@Override
	public T save(final T t) {
		@SuppressWarnings("rawtypes")
		Class objectClass = t.getClass();

		for (Field field : objectClass.getDeclaredFields()) {
			if (field.getType().equals(String.class)) {
				try {
					String value = null;
					// capitalize strings before save
					Method getter = ReflectionUtils.findGetter(t, field.getName());
					Method setter = ReflectionUtils.findSetter(t, field.getName());
					if (getter != null) {
						value = (String) getter.invoke(t);
					}
					if (value != null) {
						if (setter != null)
							setter.invoke(t, value.toUpperCase());
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		this.em.persist(t);
		return t;
	}

	public static String capitalize(String str) {
		if ((str == null) || ((str.length()) == 0))
			return str;

		return Character.toTitleCase(str.charAt(0)) + str.substring(1);
	}

	@Override
	public void delete(final PK id) {
		this.em.remove(this.em.getReference(entityClass, id));
	}

	@Override
	public T find(final PK id) {
		return (T) this.em.find(entityClass, id);
	}

	@Override
	public T update(final T t) {
		return this.em.merge(t);
	}

	protected String compileOrderBy(WhereOrderParams params, String ascOrDesc) {
		StringBuilder sql = new StringBuilder();

		if (params != null && !params.getOrderByList().isEmpty()) {
			sql.append(" ORDER BY ");
			List<String> condList = params.getOrderByList();

			Iterator<String> iter = condList.iterator();

			while (iter.hasNext()) {
				sql.append(iter.next());

				if (iter.hasNext())
					sql.append(", ");
			}

			if(params.getSortOrder() == null){
				sql.append(" ").append(ascOrDesc).append(" ");
			}else{
				sql.append(" ").append(params.getSortOrder()).append(" ");
			}
		}

		return sql.toString();
	}

	protected String compileWhereClause(WhereOrderParams params, Boolean putWhere, Boolean subSelect) {
		StringBuilder sql = new StringBuilder();

		Map<Boolean, List<Cond>> mapCond = params.getMapCond();
		List<Cond> condList = mapCond.get(subSelect);

		if (condList != null && !condList.isEmpty()) {

			if (putWhere) {
				sql.append(" WHERE ");
			} else {
				sql.append(" AND ");
			}

			if (condList != null && !condList.isEmpty()) {
				Iterator<Cond> iter = condList.iterator();

				while (iter.hasNext()) {

					Cond cond = iter.next();

					if (cond != null) {
						sql.append(" ").append(cond.getCol()).append(" ").append(cond.getOpSql());

						sql.append(":");
						sql.append(cond.getParam());

					}
					if (iter.hasNext())
						sql.append(" and");
				}
			}
		}

		return sql.toString();
	}

	protected void setParameterWhereClause(WhereOrderParams params, Query query) {
		if (params != null) {

			List<Cond> allCondList = new ArrayList<Cond>();

			Map<Boolean, List<Cond>> mapCond = params.getMapCond();
			List<Cond> condListSubSelect = mapCond.get(true);
			List<Cond> condList = mapCond.get(false);

			if (condListSubSelect != null)
				allCondList.addAll(condListSubSelect);

			if (condList != null)
				allCondList.addAll(condList);

			Iterator<Cond> iter = allCondList.iterator();

			while (iter.hasNext()) {

				Cond cond = iter.next();

				if (cond.getOpSql().trim().equalsIgnoreCase("LIKE")) {
					query.setParameter(cond.getParam(), "%" + cond.getVal().toString().toUpperCase() + "%");
				} else {
					query.setParameter(cond.getParam(), cond.getVal());
				}
			}
		}
	}
}