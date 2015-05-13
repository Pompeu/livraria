package com.herokuapp.livraria.uteis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcFactory {

	private static JdbcFactory INSTANCE = null;
	private Connection con = null;
	private static final String DRIVER = "org.postgresql.Driver";

	private JdbcFactory() {

	}

	public static JdbcFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JdbcFactory();
		}
		return INSTANCE;
	}

	public Connection getConnection() {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/livraria", "postgres",
					"123");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void disconnect() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Fechou");
		disconnect();
	}
}
