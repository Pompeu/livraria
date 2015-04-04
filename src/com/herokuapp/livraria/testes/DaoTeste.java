package com.herokuapp.livraria.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.User;
import com.herokuapp.livraria.models.dao.LoginDAO;
import com.herokuapp.livraria.models.dao.LoginImpl;

public class DaoTeste {
	private User user = null;
	private Connection con = JdbcFactory.getInstance().getConnection();
	private LoginDAO login;

	@Test
	public void connectionTest() throws SQLException {
		assertTrue(con != null);
		assertFalse(con.isClosed());
	}

	@Test
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
