package com.herokuapp.livraria.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormLivro implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		return "/WEB-INF/jsp/livros/livro-form.jsp";
	}

}
