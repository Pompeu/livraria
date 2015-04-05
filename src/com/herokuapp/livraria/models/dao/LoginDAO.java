package com.herokuapp.livraria.models.dao;

import com.herokuapp.livraria.models.User;

public interface LoginDAO {

	User logar(User user);

	String recPassoword(String email, String newpass);

	boolean verificarNivel(String email);
	

}
