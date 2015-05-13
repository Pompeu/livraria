package com.herokuapp.livraria.testes;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.herokuapp.livraria.models.Livro;
import com.herokuapp.livraria.models.dao.LivroByTituloDAO;
import com.herokuapp.livraria.models.dao.LivrosByTituloImpl;
import com.herokuapp.livraria.uteis.JdbcFactory;

public class LivrosByTituloTest {
	private LivroByTituloDAO livrodao;

	@Before
	public void before() {
		livrodao = new LivrosByTituloImpl(JdbcFactory.getInstance()
				.getConnection());
	}

	@Test
	public void getLivroByTitulo() {
		List<Livro> livros = livrodao.retriveLivroByTitulo("Java");
		assertTrue(livros != null);
		assertTrue(livros.size() > 0);
		assertTrue(livros.get(0) instanceof Livro);
	}

	@Test
	public void getTituloForAutoComplete() {
		String input = "n";

		List<?> tituloAutoComplete = livrodao.getTituloAutoComplete(input);

		assertTrue(tituloAutoComplete != null);
		assertTrue(tituloAutoComplete.size() > 0);
		assertTrue(tituloAutoComplete.get(0) instanceof String);

	}

}
