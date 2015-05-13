package com.herokuapp.livraria.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.herokuapp.livraria.controllers.logicas.Logica;

public class LogoutCrtl implements Logica {

	private HttpSession session;

	public LogoutCrtl() {
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		session = req.getSession();
		//session.setAttribute("usuLogado", null);
		session.invalidate();
		return "crtl.do?crtl=EstanteCrtl";
	}

}
