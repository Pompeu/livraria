package com.herokuapp.livraria.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.herokuapp.livraria.controllers.logicas.Logica;
import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.User;
import com.herokuapp.livraria.models.dao.LoginDAO;
import com.herokuapp.livraria.models.dao.LoginImpl;

public class LogarCrtl implements Logica {
	private HttpSession session;
	private User user;
	private LoginDAO logindao;
	
	public LogarCrtl() {
		logindao = new LoginImpl(JdbcFactory.getInstance().getConnection());
	}
	
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		session = req.getSession();
		session.setMaxInactiveInterval(30 * 60);
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		                
		user = new User(email);
	
		User usulogado = logindao.logar(user);		

		if (usulogado != null && BCrypt.checkpw(password, usulogado.getPassword())) {
			session.setAttribute("usuLogado", usulogado);				
		} else {
			req.setAttribute("result", "login ou senha invalidos");
			return "/WEB-INF/jsp/logar/logar-form.jsp";
		}
		
		return "crtl.do?crtl=EstanteCrtl";
	}

}
