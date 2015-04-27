package com.herokuapp.livraria.models.dao;

import java.util.List;

import com.herokuapp.livraria.models.Livro;

public interface LivroByTituloDAO {
	List<Livro> retriveLivroByTitulo(String input);
	List<?> getTituloAutoComplete(String input);
}
