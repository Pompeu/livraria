package com.herokuapp.livraria.controllers.logicas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormLogar implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		return "/WEB-INF/jsp/logar/logar-form.jsp";
	}

}
