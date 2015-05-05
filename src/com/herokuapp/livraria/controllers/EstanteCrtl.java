package com.herokuapp.livraria.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.controllers.logica.Logica;
import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.dao.LivroDAO;
import com.herokuapp.livraria.models.dao.LivroImpl;

public class EstanteCrtl implements Logica {

	private LivroDAO livrodao;

	public EstanteCrtl() {
		livrodao = new LivroImpl(JdbcFactory.getInstance().getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String parameter = req.getParameter("pagina");

		if (parameter != null) {
			req.setAttribute("livros",
					livrodao.retriveAll(Integer.valueOf(parameter)));
		} else {
			req.setAttribute("livros", livrodao.retriveAll());
		}
		return "/WEB-INF/jsp/estante/list.jsp";

	}

}
