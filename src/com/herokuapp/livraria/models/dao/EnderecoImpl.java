package com.herokuapp.livraria.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herokuapp.livraria.models.Endereco;
import com.herokuapp.livraria.models.Estados;
import com.herokuapp.livraria.models.User;

public class EnderecoImpl implements EndrecoDAO {

	private Connection con;
	private PreparedStatement stm;

	public EnderecoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public Endereco addEndereco(Endereco endereco) {

		String sql = "insert into enderecos (iduser ,cidade , estado ,logradouro ,"
				+ " cep , numero ,complemento )"
				+ "values (?, ?, ?, ?, ?, ?, ?)";

		try {

			stm = con.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);

			stm.setLong(1, endereco.getUser().getId());
			stm.setString(2, endereco.getCidade());
			stm.setString(3, endereco.getEstado().name());
			stm.setString(4, endereco.getLogradouro());
			stm.setString(5, endereco.getCep());
			stm.setString(6, endereco.getNumero());
			stm.setString(7, endereco.getComplemento());

			stm.execute();

			ResultSet rs = stm.getGeneratedKeys();

			if (rs.next()) {
				endereco.setId(rs.getInt(1));
			}

			stm.close();

			return endereco;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void removeEndreco(Endereco endereco) {

		String sql = "delete from enderecos where id=?";

		if (endereco.getId() != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						stm = con.prepareStatement(sql);
						stm.setLong(1, endereco.getId());
						stm.execute();
						stm.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			}).start();
		}

	}

	@Override
	public List<Endereco> getEnderecoByUser(User user) {

		String sql = "select * from enderecos where iduser=?";

		List<Endereco> enderecos = new ArrayList<>();

		try {

			stm = con.prepareStatement(sql);

			stm.setLong(1, user.getId());

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {

				enderecos.add(new Endereco(Integer.valueOf(rs.getInt("id")),
						user, Estados.getName(rs.getString("estado")), rs
								.getString("cidade"), rs
								.getString("logradouro"), rs.getString("cep"),
						rs.getString("numero"), rs.getString("complemento")));
			}

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return enderecos.size() > 0 ? enderecos : null;
	}

	@Override
	public Endereco getEnderecoById(Integer id) {
		String sql = "select * from enderecos where id=?";

		Endereco endereco = null;

		try {

			stm = con.prepareStatement(sql);

			stm.setLong(1, id);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				endereco = new Endereco(Integer.valueOf(rs.getString("id")),
						Estados.getName(rs.getString("estado")),
						rs.getString("cidade"), rs.getString("logradouro"),
						rs.getString("cep"), rs.getString("numero"),
						rs.getString("complemento"));
			}

			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return endereco;
	}
}
