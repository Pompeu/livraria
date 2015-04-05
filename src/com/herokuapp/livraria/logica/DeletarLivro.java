package com.herokuapp.livraria.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.Livro;
import com.herokuapp.livraria.models.dao.LivroDAO;
import com.herokuapp.livraria.models.dao.LivroImpl;

public class DeletarLivro implements Logica {

	private LivroDAO livrodao;
	private Livro livro;

	public DeletarLivro() {
		livrodao = new LivroImpl(JdbcFactory.getInstance().getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		Integer id = Integer.parseInt(req.getParameter("id"));

		livro = livrodao.retriveById(id);
		livrodao.delete(livro);

		String msg = "O " + livro.getTitulo() + " foi removido";

		req.setAttribute("msg", msg);

		return "/WEB-INF/jsp/livros/livro-list.jsp";
	}

}
