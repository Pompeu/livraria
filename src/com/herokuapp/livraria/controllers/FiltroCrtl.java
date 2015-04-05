package com.herokuapp.livraria.controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import com.herokuapp.livraria.models.Console;

@WebFilter("/*")
public class FiltroCrtl implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		long tempoInicial = System.currentTimeMillis();

		chain.doFilter(request, response);

		long tempoFinal = System.currentTimeMillis();

		String uri = ((HttpServletRequest) request).getRequestURI();
		String parametros = ((HttpServletRequest) request)
				.getParameter("service");

		if (parametros == null) {
			parametros = ((HttpServletRequest) request).getParameter("crtl");
		}

		Console.log("Tempo da requisicao de " + uri + "?" + takeLogica(uri)
				+ "=" + parametros + " demorou : "
				+ (tempoFinal - tempoInicial) + "(ms)");

	}

	private String takeLogica(String uri) {
		switch (uri) {
		case "/livraria/service.do":
			return uri.substring(10, 17);
		case "/livraria/crtl.do":
			return uri.substring(10, 14);
		default:
			return uri;
		}
	}

	public void destroy() {

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
