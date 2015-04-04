package com.herokuapp.livraria.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.logica.Logica;
import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.Livro;
import com.herokuapp.livraria.models.dao.DAO;
import com.herokuapp.livraria.models.dao.LivroImpl;

public class EstanteCrtl implements Logica {

	private DAO<Livro> livrodao;

	public EstanteCrtl() {
		livrodao = new LivroImpl(JdbcFactory.getInstance().getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		req.setAttribute("livros", livrodao.retriveAll());
		
		return "/WEB-INF/jsp/estante/list.jsp";

	}

}
