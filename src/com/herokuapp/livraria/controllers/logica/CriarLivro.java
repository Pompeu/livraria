package com.herokuapp.livraria.controllers.logica;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.herokuapp.livraria.models.ImagemBase64;
import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.Livro;
import com.herokuapp.livraria.models.dao.LivroDAO;
import com.herokuapp.livraria.models.dao.LivroImpl;

public class CriarLivro implements Logica {

	private LivroDAO livrodao;
	private Livro livro;

	public CriarLivro() {
		livrodao = new LivroImpl(JdbcFactory.getInstance().getConnection());

	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		Map<String, String[]> parameterMap = req.getParameterMap();

		List<String> params = new ArrayList<>();

		parameterMap.values().forEach(s -> params.add(s[0]));

		Part part = req.getPart("file");

		InputStream is = part.getInputStream();

		byte[] buffer = new byte[is.available()];
		is.read(buffer);
		is.close();

		try {
			livro = new Livro(params.get(2), params.get(3), params.get(4),
					params.get(5), Integer.parseInt(params.get(6)),
					new BigDecimal(Double.valueOf(params.get(7))),
					new ImagemBase64(buffer));

		} catch (NumberFormatException e) {
			throw new RuntimeException("Erro no valor " + e);
		}

		if (params.get(1).equals("")) {
			req.setAttribute("livro", livrodao.create(livro));
		} else {
			livro.setId(Integer.valueOf(params.get(1)));
			livro.setImagem(new ImagemBase64(params.get(8).replaceAll(
					"data:image/jpeg;base64,", "")));
			req.setAttribute("livro", livrodao.update(livro));
		}
		return "/WEB-INF/jsp/livros/livro-details.jsp";
	}

}
