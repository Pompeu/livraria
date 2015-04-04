package com.herokuapp.livraria.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstanteImpl implements Estante {

	@Override
	public List<Livro> showByCategory(String category) {
		List<Livro> livrosFiltrados = new ArrayList<>();
		return Collections.unmodifiableList(livrosFiltrados);
	}

}
