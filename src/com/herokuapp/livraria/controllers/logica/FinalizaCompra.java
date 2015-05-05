package com.herokuapp.livraria.controllers.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.herokuapp.livraria.models.Carrinho;
import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.dao.CarrinhoDAO;
import com.herokuapp.livraria.models.dao.CarrinhoImpl;

public class FinalizaCompra implements Logica {

	private Carrinho carrinho;
	private CarrinhoDAO carrinhoDao;
	private HttpSession session;

	public FinalizaCompra() {
		carrinhoDao = new CarrinhoImpl(JdbcFactory.getInstance()
				.getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		session = req.getSession();
		carrinho = (Carrinho) session.getAttribute("carrinho");
		Carrinho carrinhoFechado = carrinhoDao.fecharCarrinho(carrinho);
		if( carrinhoFechado != null){
			req.setAttribute("codigoCarrinho", carrinhoFechado.getId());
			req.setAttribute("result", "compra Finalizada");
		}

		return null;
	}

}
