package com.herokuapp.livraria.controllers;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.controllers.logica.Logica;

@WebServlet("/crtl.do")
public class ControllerServlets extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ControllerServlets() {
		
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String parametro = req.getParameter("crtl");
		String className = "com.herokuapp.livraria.controllers." + parametro;
		
		try {
			
			Class<?> classe = Class.forName(className);

			Logica logica = (Logica) classe.newInstance();
			String pagina = logica.executa(req, res);
			res.setCharacterEncoding("UTF-8");  
			req.getRequestDispatcher(pagina).forward(req, res);

		} catch (Exception e) {
			throw new ServletException(
					"O Controller de neg�cios causou uma exce��o", e);
		}

	}
}
