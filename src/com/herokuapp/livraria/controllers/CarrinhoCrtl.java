package com.herokuapp.livraria.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.herokuapp.livraria.logica.Logica;
import com.herokuapp.livraria.models.Carrinho;
import com.herokuapp.livraria.models.EstadoCarrinho;
import com.herokuapp.livraria.models.Livro;
import com.herokuapp.livraria.models.User;

public class CarrinhoCrtl implements Logica {

	private Carrinho carrinho;
	private HttpSession session;
	private Set<Livro> livros;
	private User user;
	private EstadoCarrinho estadoDoCarrinho;

	@SuppressWarnings("unchecked")
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		String livroAremover = req.getParameter("item");
		String recalcular = req.getParameter("recalcular");

		session = req.getSession();

		livros = (Set<Livro>) session.getAttribute("livros");
		user = (User) session.getAttribute("usuLogado");

		if (livroAremover != null) {
			livros = livros.stream()
					.filter(livro -> !livro.getTitulo().equals(livroAremover))
					.collect(Collectors.toSet());
		}
		if (recalcular != null && recalcular.equals("1")) {
			livros.forEach(System.out::println);
		}
		session.setAttribute("livros", livros);

		carrinho = new Carrinho(livros, user, estadoDoCarrinho);

		session.setAttribute("carrinho", carrinho);

		return "/WEB-INF/jsp/carrinho/lista.jsp";
	}
}
