package com.herokuapp.livraria.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.herokuapp.livraria.models.Nivel;
import com.herokuapp.livraria.models.User;

public class LoginImpl implements LoginDAO {
	private Connection con;
	private PreparedStatement stm;

	public LoginImpl(Connection con) {
		this.con = con;
	}

	@Override
	public User logar(User user) {

		String sql = "SELECT * FROM USERS WHERE email = ?";
		User usulogado = null;

		try {

			stm = con.prepareStatement(sql);

			stm.setString(1, user.getEmail());
			

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				
				switch (rs.getString("nivel")) {
				case "ADMIN":
					usulogado = new User(Nivel.ADMIN, rs.getString("nome"),
							rs.getString("cpf"), rs.getString("email"),
							rs.getString("password"));
					usulogado.setId(rs.getInt("id"));
					break;
				case "CLIENTE":
					usulogado = new User(Nivel.CLIENTE, rs.getString("nome"),
							rs.getString("cpf"), rs.getString("email"),
							rs.getString("password"));
					usulogado.setId(rs.getInt("id"));	
					break;
				default:
					throw new RuntimeException("Nivel de usuario invalido");
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usulogado;
	}

}
