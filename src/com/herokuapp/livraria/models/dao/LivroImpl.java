package com.herokuapp.livraria.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.herokuapp.livraria.models.Livro;
import com.herokuapp.livraria.uteis.ImagemBase64;

public class LivroImpl implements LivroDAO {

	private Connection con;
	private PreparedStatement stm;
	private Gson json;

	public LivroImpl(Connection con) {
		json = new Gson();
		this.con = con;
	}

	@Override
	public Livro create(Livro livro) {

		String sql = "insert into livros(isbn ,titulo , autor , preco ,category, qtd , imagem )"
				+ "values (?, ?, ?, ? , ? , ? , ?::jsonb)";
		try {
			stm = con.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);

			stm.setString(1, livro.getIsbn());
			stm.setString(2, livro.getTitulo());
			stm.setString(3, livro.getAutor());
			stm.setBigDecimal(4, livro.getPreco());
			stm.setString(5, livro.getCategory());
			stm.setInt(6, livro.getQtd());
			stm.setString(7, json.toJson(livro.getImagem()));

			stm.execute();

			ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				livro.setId(rs.getInt(1));
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return livro;
	}

	@Override
	public Livro update(Livro livro) {

		String sql = "update livros set "
				+ "isbn=? , titulo=? , autor=? , preco=? ,qtd = ? ,category=? , imagem=?::jsonb "
				+ "where id = ?";

		try {
			stm = con.prepareStatement(sql);

			stm.setString(1, livro.getIsbn());
			stm.setString(2, livro.getTitulo());
			stm.setString(3, livro.getAutor());
			stm.setBigDecimal(4, livro.getPreco());
			stm.setInt(5, livro.getQtd());
			stm.setString(6, livro.getCategory());
			stm.setString(7, json.toJson(livro.getImagem()));
			stm.setLong(8, livro.getId());

			stm.execute();
			stm.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return livro;
	}

	@Override
	public boolean delete(Livro livro) {
		String sql = "delete from livros where id=?";

		try {
			stm = con.prepareStatement(sql);
			stm.setLong(1, livro.getId());

			stm.execute();

			stm.close();

			if (retriveById(livro.getId()) == null) {
				return true;
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Livro retriveById(Integer id) {
		String sql = "select * from livros  where id=?";
		Livro livro = null;
		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				livro = new Livro(rs.getInt("id"), rs.getString("titulo"),
						rs.getString("autor"), rs.getString("category"),
						rs.getString("isbn"), rs.getInt("qtd"),
						rs.getBigDecimal("preco"), json.fromJson(
								rs.getString("imagem"), ImagemBase64.class));
			}
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return livro;
	}

	@Override
	public List<Livro> retriveAll() {

		String sql = "select * from livros Limit 6";
		List<Livro> livros = new ArrayList<>();

		try {
			stm = con.prepareStatement(sql);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				livros.add(new Livro(rs.getInt("id"), rs.getString("titulo"),
						rs.getString("autor"), rs.getString("category"), rs
								.getString("isbn"), rs.getInt("qtd"), rs
								.getBigDecimal("preco"), json.fromJson(
								rs.getString("imagem"), ImagemBase64.class)));
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		livros.sort((l1 , l2) -> l1.getTitulo().compareTo(l2.getTitulo()));
		return livros;
	}

	public List<Livro> retriveAll(int offset) {

		String sql = "select * from livros Limit 6 offset ?";
		List<Livro> livros = new ArrayList<>();

		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1, offset);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				livros.add(new Livro(rs.getInt("id"), rs.getString("titulo"),
						rs.getString("autor"), rs.getString("category"), rs
								.getString("isbn"), rs.getInt("qtd"), rs
								.getBigDecimal("preco"), json.fromJson(
								rs.getString("imagem"), ImagemBase64.class)));
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	}
}
