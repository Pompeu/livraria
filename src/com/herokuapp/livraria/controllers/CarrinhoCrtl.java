package com.herokuapp.livraria.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.herokuapp.livraria.controllers.logicas.Logica;
import com.herokuapp.livraria.models.Carrinho;
import com.herokuapp.livraria.models.EstadoCarrinho;
import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.Livro;
import com.herokuapp.livraria.models.User;
import com.herokuapp.livraria.models.dao.EnderecoImpl;
import com.herokuapp.livraria.models.dao.EndrecoDAO;

public class CarrinhoCrtl implements Logica {

	private Carrinho carrinho;
	private HttpSession session;
	private Set<Livro> livros;
	private User usuLogado;
	private EndrecoDAO endrecodao;
	private EstadoCarrinho estadoDoCarrinho;
	public CarrinhoCrtl() {
		endrecodao = new EnderecoImpl(JdbcFactory.getInstance().getConnection());
	}

	@SuppressWarnings("unchecked")
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		String livroAremover = req.getParameter("item");

		session = req.getSession();

		livros = (Set<Livro>) session.getAttribute("livros");
		usuLogado = (User) session.getAttribute("usuLogado");

		if (livroAremover != null) {
			livros = livros.stream()
					.filter(livro -> !livro.getTitulo().equals(livroAremover))
					.collect(Collectors.toSet());
		}

		if (usuLogado != null) {
			req.setAttribute("enderecos",
					endrecodao.getEnderecoByUser(usuLogado));
		}

		session.setAttribute("livros", livros);

		carrinho = new Carrinho(livros, usuLogado, estadoDoCarrinho);

		session.setAttribute("carrinho", carrinho);

		return "/WEB-INF/jsp/carrinho/lista.jsp";
	}
}
