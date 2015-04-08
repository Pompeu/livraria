package com.herokuapp.livraria.logica;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.dao.LivroByTituloDAO;
import com.herokuapp.livraria.models.dao.LivroDAO;
import com.herokuapp.livraria.models.dao.LivroImpl;
import com.herokuapp.livraria.models.dao.LivrosByTituloImpl;

public class BuscarLivro implements Logica {

	private LivroByTituloDAO livrotitulodao;
	private LivroDAO livrodao;
	private Connection con;

	public BuscarLivro() {
		con = JdbcFactory.getInstance().getConnection();
		livrotitulodao = new LivrosByTituloImpl(con);
		livrodao = new LivroImpl(con);
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		String titulo = req.getParameter("titulo");
		if (titulo.matches("^[a-zA-Z]+$"))
			req.setAttribute("livros",
					livrotitulodao.retriveLivroByTitulo(titulo));
		else
			req.setAttribute("livros", livrodao.retriveAll());

		return "/WEB-INF/jsp/estante/list.jsp";
	}

}
