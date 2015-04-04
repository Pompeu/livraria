package com.herokuapp.livraria.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.User;
import com.herokuapp.livraria.models.dao.DAO;
import com.herokuapp.livraria.models.dao.UserImpl;

public class DeletarUser implements Logica {
	private DAO<User> userdao;
	private User user;

	public DeletarUser() {
		userdao = new UserImpl(JdbcFactory.getInstance().getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		Integer id = Integer.parseInt(req.getParameter("id"));

		user = userdao.retriveById(id);

		userdao.delete(user);

		String msg = "O " + user.getNome() + " foi removido";
		req.setAttribute("msg", msg);

		return "/WEB-INF/jsp/user/users-list.jsp";
	}

}
