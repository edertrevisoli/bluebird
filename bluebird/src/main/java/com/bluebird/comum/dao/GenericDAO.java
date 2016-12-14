package com.bluebird.comum.dao;

import java.io.Serializable;

public interface GenericDAO<T, PK extends Serializable> {

	T save(T t);

	void delete(PK id);

	T find(PK id);

	T update(T t);
}