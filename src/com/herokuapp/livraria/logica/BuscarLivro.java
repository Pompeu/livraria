package com.herokuapp.livraria.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.Livro;
import com.herokuapp.livraria.models.dao.LivroByTituloDAO;
import com.herokuapp.livraria.models.dao.LivrosByTituloImpl;

public class BuscarLivro implements Logica {

	private LivroByTituloDAO livrodao;

	public BuscarLivro() {
		livrodao = new LivrosByTituloImpl(JdbcFactory.getInstance()
				.getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		String titulo = req.getParameter("titulo");

		List<Livro> livros = livrodao.retriveLivroByTitulo(titulo);

		req.setAttribute("livros", livros);

		return "/WEB-INF/jsp/estante/list.jsp";
	}

}
