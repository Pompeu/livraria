package com.herokuapp.livraria.controllers;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.herokuapp.livraria.logica.Logica;
import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.dao.LoginDAO;
import com.herokuapp.livraria.models.dao.LoginImpl;

public class RecPasswordCrtl implements Logica {

	private LoginDAO userdao;

	public RecPasswordCrtl() {
		userdao = new LoginImpl(JdbcFactory.getInstance().getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		String email = req.getParameter("email");
		String newpass = gerarTempPassword();

		if (userdao.verificarNivel(email)
				&& userdao.recPassoword(email,
						BCrypt.hashpw(newpass, BCrypt.gensalt(12))) != null) {
			req.setAttribute("newpass", newpass);
		} else {
			req.setAttribute("result", "email n�o encontrado");
		}

		return "/WEB-INF/jsp/logar/rec-password-form.jsp";
	}

	private String gerarTempPassword() {

		Random gerador = new Random();

		int numero = gerador.nextInt(9000000);

		return String.valueOf(numero);
	}

}