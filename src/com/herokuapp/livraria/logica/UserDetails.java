package com.herokuapp.livraria.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.models.User;

public class UserDetails implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		User user = (User) req.getAttribute("user");
		User usulogado = (User) req.getSession().getAttribute("usuLogado");
		if (user != null)
			req.setAttribute("user", user);
		else if (usulogado != null) {
			req.setAttribute("user", usulogado);
		}

		return "/WEB-INF/jsp/users/users-details.jsp";
	}

}
