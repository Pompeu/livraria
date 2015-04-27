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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.herokuapp.livraria.models.User;

@WebFilter("/service.do")
public class AdminFilter implements Filter {

	public AdminFilter() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String url = httpServletRequest.getRequestURI();
		String parameter = httpServletRequest.getParameter("service");
		HttpSession sessao = httpServletRequest.getSession();
		User user = (User) sessao.getAttribute("usuLogado");

		if (parameter.equals("FormLogar")
				|| parameter.equals("CarrinhoDetails")
				|| parameter.equals("CriarUser")
				|| parameter.equals("FormLogar")
				|| parameter.equals("FormEndereco")
				|| parameter.equals("AddEndereco")
				|| parameter.equals("GerarBoleto")
				|| parameter.equals("FormRecPassword")
				|| parameter.equals("FormUser")
				|| parameter.equals("UserDetails")
				|| parameter.equals("AddLivro")
				|| parameter.equals("BuscarLivro")|| user != null
				&& user.getNivel().matches("ADMIN")) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("/livraria");
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void destroy() {

	}
}
