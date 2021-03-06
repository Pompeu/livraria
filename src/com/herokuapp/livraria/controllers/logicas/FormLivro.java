package com.herokuapp.livraria.controllers.logicas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.models.Livro;
import com.herokuapp.livraria.models.dao.LivroDAO;
import com.herokuapp.livraria.models.dao.LivroImpl;
import com.herokuapp.livraria.uteis.JdbcFactory;

public class FormLivro implements Logica {

	private LivroDAO livroDao;

	public FormLivro() {
		livroDao = new LivroImpl(JdbcFactory.getInstance().getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String id = req.getParameter("id");

		if (id != null && id.matches("\\d+")) {
			req.setAttribute("livro", livroDao.retriveById(Integer.valueOf(id)));
		}

		return "/WEB-INF/jsp/livros/livro-form.jsp";
	}

}
