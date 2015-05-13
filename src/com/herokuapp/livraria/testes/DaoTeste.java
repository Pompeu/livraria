package com.herokuapp.livraria.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import org.mindrot.jbcrypt.BCrypt;

import com.herokuapp.livraria.models.User;
import com.herokuapp.livraria.models.dao.LoginDAO;
import com.herokuapp.livraria.models.dao.LoginImpl;
import com.herokuapp.livraria.uteis.JdbcFactory;

public class DaoTeste {
	private User user = null;
	private Connection con = JdbcFactory.getInstance().getConnection();
	
	private LoginDAO login;
		
	
	public void loginTest(){
		login = new LoginImpl(con);
		user = new User("itacir@hotmail.com");
		User usuLog = login.logar(user);	
		assertEquals(usuLog.getEmail(), "itacir@hotmail.com" );
		assertTrue(BCrypt.checkpw("552525", usuLog.getPassword()) );
		assertEquals(usuLog.getNome(), "Pompeu" );
		assertNotEquals(user , usuLog);
	}
}
