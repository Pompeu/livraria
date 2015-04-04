package com.herokuapp.livraria.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herokuapp.livraria.models.Nivel;
import com.herokuapp.livraria.models.User;

public class UserImpl implements DAO<User> {

	private Connection con;
	private PreparedStatement stm;

	public UserImpl(Connection con) {
		this.con = con;
	}

	@Override
	public User create(User user) {

		String sql = "insert into users(nome , cpf , email ,password, nivel )"
				+ "values (?, ?, ?, ? , ? )";

		try {
			stm = con.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);

			stm.setString(1, user.getNome());
			stm.setString(2, user.getCpf());
			stm.setString(3, user.getEmail());
			stm.setString(4, user.getPassword());
			stm.setString(5, user.getNivel());

			stm.execute();

			ResultSet rs = stm.getGeneratedKeys();

			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User update(User user) {

		String sql = "update users set(nome=? , cpf=? , email=? , password=? ,nivel=?)"
				+ "where id = ?";

		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, user.getNome());
			stm.setString(2, user.getCpf());
			stm.setString(3, user.getEmail());
			stm.setString(4, user.getPassword());
			stm.setString(5, user.getNivel());
			stm.setLong(6, user.getId());

			stm.execute();
			stm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public boolean delete(User user) {
		String sql = "delete from users where id=?";

		try {
			stm = con.prepareStatement(sql);
			stm.setLong(1, user.getId());

			stm.execute();

			stm.close();

			if (retriveById(user.getId()) == null) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public User retriveById(Integer id) {
		String sql = "select * from users  where id=?";
		User user = null;
		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				switch (rs.getString("nivel")) {
				case "ADMIN":
					user = new User(Nivel.ADMIN, rs.getString("nome"),
							rs.getString("cpf"), rs.getString("email"),
							rs.getString("password"));
					break;
				case "CLIENTE":
					user = new User(Nivel.CLIENTE, rs.getString("nome"),
							rs.getString("cpf"), rs.getString("email"),
							rs.getString("password"));
					break;
				default:
					throw new RuntimeException("Nivel de usuario invalido");
				}

			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> retriveAll() {

		String sql = "select * from users";
		List<User> users = new ArrayList<>();

		try {
			stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				switch (rs.getString("nivel")) {
				case "ADMIN":
					users.add(new User(Nivel.ADMIN, rs.getString("nome"), rs
							.getString("cpf"), rs.getString("email"), rs
							.getString("password")));
					break;
				case "CLIENTE":
					users.add(new User(Nivel.CLIENTE, rs.getString("nome"), rs
							.getString("cpf"), rs.getString("email"), rs
							.getString("password")));
					break;
				default:
					throw new RuntimeException("Nivel de usuario invalido");
				}

			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
