package com.herokuapp.livraria.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.logica.Logica;

@WebServlet("/service.do")
public class ControllerLogicas extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String parametro = req.getParameter("service");
		String className = "com.herokuapp.livraria.logica." + parametro;

		try {

			Class<?> classe = Class.forName(className);
			Logica logica = (Logica) classe.newInstance();
			String pagina = logica.executa(req, res);
			res.setCharacterEncoding("UTF-8");
			if(pagina.isEmpty()) return;
			else req.getRequestDispatcher(pagina).forward(req, res);

		} catch (Exception e) {
			throw new ServletException(
					"A lógica de negócios causou uma exceção", e);
		}

	}

}
