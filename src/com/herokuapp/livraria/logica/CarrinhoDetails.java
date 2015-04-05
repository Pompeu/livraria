package com.herokuapp.livraria.logica;

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

		String id = req.getParameter("endereco").substring(13, 15);
		
		if (id != null) {
			req.setAttribute("endereco",
					enderecoDao.getEnderecoById(Integer.valueOf(id)));
			return "/WEB-INF/jsp/carrinho/carrinho-details.jsp";
		}

		return "/WEB-INF/jsp/carrinho/lista.jsp";
	}

}