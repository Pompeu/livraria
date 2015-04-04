package com.herokuapp.livraria.models.dao;

import java.util.List;

import com.herokuapp.livraria.models.Livro;

public interface LivroByTituloDAO {
	List<Livro> retriveLivroByTitulo(String titulo);
	List<String> getTituloAutoComplete(String input);
}
