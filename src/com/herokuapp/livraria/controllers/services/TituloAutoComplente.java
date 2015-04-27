package com.herokuapp.livraria.controllers.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.dao.LivroByTituloDAO;
import com.herokuapp.livraria.models.dao.LivrosByTituloImpl;

@WebServlet("/autocomplettitulo")
public class TituloAutoComplente implements Api {

	private Gson json;
	private LivroByTituloDAO livrodao;

	public TituloAutoComplente() {
		json = new Gson();
		livrodao = new LivrosByTituloImpl(JdbcFactory.getInstance()
				.getConnection());
	}

	@Override
	public String response(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		String input = req.getParameter("q");		
		
		List<?> titulos = livrodao.getTituloAutoComplete(input);

		if (input != null && input.matches("^[a-zA-Z -]*[A-Za-z]$")) {
			return json.toJson(titulos);

		}
		return json.toJson(null);
	}
}
