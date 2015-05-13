package com.herokuapp.livraria.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.controllers.logicas.Logica;
import com.herokuapp.livraria.models.dao.LivroDAO;
import com.herokuapp.livraria.models.dao.LivroImpl;
import com.herokuapp.livraria.uteis.JdbcFactory;

public class EstanteCrtl implements Logica {

	private LivroDAO livrodao;

	public EstanteCrtl() {
		livrodao = new LivroImpl(JdbcFactory.getInstance().getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		String offset = req.getParameter("pagina");

		if (offset != null) {

			req.setAttribute("livros",
					livrodao.retriveAll(Integer.valueOf(offset)));

			req.setAttribute("pagina", Integer.valueOf(offset) + 6);

		} else {
			req.setAttribute("livros", livrodao.retriveAll());
		}
		return "/WEB-INF/jsp/estante/list.jsp";

	}

}
