package com.herokuapp.livraria.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.controllers.services.Api;

@WebServlet("/api")
public class ControllerServices extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PrintWriter saida;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("application/json");
		
		String parametro = req.getParameter("api");
		String className = "com.herokuapp.livraria.controllers.services."
				+ parametro;
		saida = res.getWriter();

		try {

			Class<?> classe = Class.forName(className);
			Api api = (Api) classe.newInstance();
			String pagina = api.response(req, res);
			saida.println(pagina);
			saida.flush();

		} catch (Exception e) {
			throw new ServletException(
					"A lógica de negócios causou uma exceção", e);
		}

	}

}
