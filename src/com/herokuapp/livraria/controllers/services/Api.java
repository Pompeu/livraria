package com.herokuapp.livraria.controllers.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Api {
	String response(HttpServletRequest req , HttpServletResponse res) throws IOException;
}
