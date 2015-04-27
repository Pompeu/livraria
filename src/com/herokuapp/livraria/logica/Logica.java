package com.herokuapp.livraria.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Logica {
	String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception;

}
