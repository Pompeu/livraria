package com.herokuapp.livraria.controllers.logicas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.herokuapp.livraria.models.Nivel;
import com.herokuapp.livraria.models.User;
import com.herokuapp.livraria.models.dao.DAO;
import com.herokuapp.livraria.models.dao.UserImpl;
import com.herokuapp.livraria.uteis.JdbcFactory;

public class CriarUser implements Logica {

	private DAO<User> userdao;
	private User user;

	public CriarUser() {
		userdao = new UserImpl(JdbcFactory.getInstance().getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		Map<String, String[]> parameterMap = req.getParameterMap();

		List<String> params = new ArrayList<>();

		parameterMap.values().forEach(s -> params.add(s[0]));
		
	
		if (!params.get(1).equals("")){
			user = new User(params.get(2),
					params.get(3), params.get(4));
			user.setId(Integer.valueOf(params.get(1)));
			req.setAttribute("user", userdao.update(user));
		}else{
			String password = BCrypt.hashpw(params.get(5), BCrypt.gensalt(12));
			user = new User(Nivel.valueOf(params.get(6)), params.get(2),
					params.get(3), params.get(4), password);
			req.setAttribute("user", userdao.create(user));
		}
		return "/WEB-INF/jsp/users/users-details.jsp";
	}

}
