package com.herokuapp.livraria.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.herokuapp.livraria.models.ImagemBase64;
import com.herokuapp.livraria.models.Livro;

public class LivrosByTituloImpl implements LivroByTituloDAO {

	private Connection con;
	private PreparedStatement stm;
	private Gson json;

	public LivrosByTituloImpl(Connection con) {
		this.con = con;
		json = new Gson();

	}

	@Override
	public List<Livro> retriveLivroByTitulo(String titulo) {

		String sql = "select * from livros  where titulo  ilike ? Limit 6";

		List<Livro> livros = new ArrayList<>();

		try {
			stm = con.prepareStatement(sql);

			stm.setString(1, "%" + titulo + "%");

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				livros.add(new Livro(rs.getInt("id"), rs.getString("titulo"),
						rs.getString("autor"), rs.getString("category"), rs
								.getString("isbn"), rs.getInt("qtd"), rs
								.getBigDecimal("preco"), json.fromJson(
								rs.getString("imagem"), ImagemBase64.class)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	}

	@Override
	public List<String> getTituloAutoComplete(String input) {

		String sql = "select titulo from livros  where titulo  ilike ? Limit 20";

		List<String> livros = new ArrayList<>();

		try {
			stm = con.prepareStatement(sql);

			stm.setString(1, "%" + input + "%");

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				livros.add(rs.getString("titulo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return livros;
	}

}
