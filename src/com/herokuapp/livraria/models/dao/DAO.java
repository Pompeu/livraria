package com.herokuapp.livraria.models.dao;

import java.util.List;

public interface DAO<T> {
	
	T create(T obj);

	T update(T obj);

	boolean delete(T obj);

	T retriveById(Integer id);

	List<T> retriveAll();
}
