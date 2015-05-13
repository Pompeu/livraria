package com.herokuapp.livraria.controllers.logicas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.models.Endereco;
import com.herokuapp.livraria.models.Estados;
import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.User;
import com.herokuapp.livraria.models.dao.EnderecoImpl;
import com.herokuapp.livraria.models.dao.EndrecoDAO;

public class AddEndereco implements Logica {

	private Endereco endereco;
	private EndrecoDAO enderecoDao;
	private User user;
	private Map<String, String[]> parameterMap;
	private List<String> params;

	public AddEndereco() {
		enderecoDao = new EnderecoImpl(JdbcFactory.getInstance()
				.getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		user = (User) req.getSession().getAttribute("usuLogado");

		parameterMap = req.getParameterMap();
		params = new ArrayList<>();

		parameterMap.values().forEach(e -> params.add(e[0]));

		endereco = new Endereco(user, Estados.valueOf(params.get(3)),
				params.get(2), params.get(4), params.get(5), params.get(6),
				params.get(7), params.get(8));

		req.setAttribute("endereco", enderecoDao.addEndereco(endereco));

		return "crtl.do?crtl=CarrinhoCrtl";
	}

}
