package com.herokuapp.livraria.models.dao;

import java.util.List;

import com.herokuapp.livraria.models.Livro;

public interface LivroDAO {
	
	Livro create(Livro obj);

	Livro update(Livro obj);

	boolean delete(Livro obj);

	Livro retriveById(Integer id);

	List<Livro> retriveAll();

	List<Livro> retriveAll(int offset);
}
