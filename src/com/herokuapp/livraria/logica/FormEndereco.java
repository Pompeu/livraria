package com.herokuapp.livraria.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.models.Estados;

public class FormEndereco implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		req.setAttribute("estados", Estados.values());

		return "/WEB-INF/jsp/endereco/endereco-form.jsp";
	}

}
