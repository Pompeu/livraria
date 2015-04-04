package com.herokuapp.livraria.models;

import java.util.List;

public interface Estante {
	
	List<Livro> showByCategory(String category);
}
