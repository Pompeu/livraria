package com.herokuapp.livraria.controllers.logicas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.dao.EnderecoImpl;
import com.herokuapp.livraria.models.dao.EndrecoDAO;

public class CarrinhoDetails implements Logica {

	private EndrecoDAO enderecoDao;

	public CarrinhoDetails() {
		enderecoDao = new EnderecoImpl(JdbcFactory.getInstance()
				.getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		String id = req.getParameter("endereco").substring(13, 15).split(",")[0];

		if (id != null) {
			req.setAttribute("endereco",
					enderecoDao.getEnderecoById(Integer.valueOf(id)));
			return "/WEB-INF/jsp/carrinho/carrinho-details.jsp";
		}

		return "/WEB-INF/jsp/carrinho/lista.jsp";
	}

}
