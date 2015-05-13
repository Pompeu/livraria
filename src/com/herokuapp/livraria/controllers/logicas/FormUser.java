package com.herokuapp.livraria.controllers.logicas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormUser implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {		
		return "/WEB-INF/jsp/users/users-form.jsp";
	}

}
