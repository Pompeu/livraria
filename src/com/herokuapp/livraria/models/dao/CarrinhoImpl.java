package com.herokuapp.livraria.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.herokuapp.livraria.models.Carrinho;
import com.herokuapp.livraria.models.EstadoCarrinho;

public class CarrinhoImpl implements CarrinhoDAO {

	private Connection con;
	private PreparedStatement stm;

	public CarrinhoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public Carrinho fecharCarrinho(Carrinho carrinho) {
		String sql = "insert into carrinhos(iduser , data , estado , total , qtd)"
				+ "values (?, ?, ?, ?, ?)";
		try {
			stm = con.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);

			stm.setLong(1, carrinho.getUser().getId());
			stm.setTimestamp(2, Timestamp.valueOf(carrinho.getData()));
			stm.setString(3, EstadoCarrinho.ABERTO.name());
			stm.setBigDecimal(4, carrinho.getTotal());
			stm.setInt(5, carrinho.getQtd());

			stm.execute();
			

			ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				carrinho.setId(rs.getInt(1));
			}

			new Thread(() -> carrinho.getLivros().forEach(
					livro -> livroCarrinho(carrinho.getId(), livro.getId())))
					.start();
			
			stm.close();
			return carrinho;
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deletarCarrinho(Carrinho carrinho) {
		String sql = "DELETE FROM CARRINHO WHERE id=?";

		try {
			stm = con.prepareStatement(sql);
			stm.setLong(1, carrinho.getId());

			stm.execute();

			stm.close();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private void livroCarrinho(Integer idCarrinho, Integer IdLivro) {
		String sql = "insert into livro_carrinho(idCarrinho , idLivro)"
				+ "values (?, ?)";
		try {
			stm = con.prepareStatement(sql);

			stm.setLong(1, idCarrinho);
			stm.setLong(2, IdLivro);

			stm.execute();
			stm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
