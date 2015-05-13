package com.herokuapp.livraria.controllers.logicas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.herokuapp.livraria.models.Estados;
import com.herokuapp.livraria.models.User;

public class FormEndereco implements Logica {
	private HttpSession session;

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		session = req.getSession();
		User user = (User) session.getAttribute("usuLogado");
		
		if (user == null) {
			return "/WEB-INF/jsp/logar/logar-form.jsp";
		} else {
			req.setAttribute("estados", Estados.values());
			return "/WEB-INF/jsp/endereco/endereco-form.jsp";
		}

	}

}
