package com.herokuapp.livraria.controllers.logica;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.Livro;
import com.herokuapp.livraria.models.dao.LivroDAO;
import com.herokuapp.livraria.models.dao.LivroImpl;

public class AddLivro implements Logica {

	private LivroDAO livrodao;	
	private Set<Livro> livros = new HashSet<>();
	private HttpSession session;

	public AddLivro() {
		livrodao = new LivroImpl(JdbcFactory.getInstance().getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		session = req.getSession();

		livros = getLivrosSession();

		Livro livro = livrodao.retriveById(Integer.valueOf(req
				.getParameter("id")));
		
		livro.setQtd(Integer.valueOf(req
				.getParameter("qtd")));
		
		livros.add(livro);

		session.setAttribute("livros", livros);

		return "crtl.do?crtl=CarrinhoCrtl";
	}

	@SuppressWarnings("unchecked")
	private Set<Livro> getLivrosSession() {
		if( session.getAttribute("livros") != null){
			return (Set<Livro>) session.getAttribute("livros");
		}
		return livros;		
	}

}
