package com.herokuapp.livraria.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<Livro> retriveLivroByTitulo(String input) {

		String sql = "SELECT *	FROM livros	WHERE to_tsvector(titulo) @@ to_tsquery(?)";

		List<Livro> livros = new ArrayList<>();

		try {
			stm = con.prepareStatement(sql);

			stm.setString(1, joinPostgresSqlToVector(input));

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

	private String joinPostgresSqlToVector(String input) {
		return Arrays
				.asList(input.toLowerCase().replaceAll("^\\d+$", "").split(" "))
				.stream().collect(Collectors.joining(" & "));
	}	

	@Override
	public List<?> getTituloAutoComplete(String input) {

		String sql = "select titulo from livros  where titulo  ilike ? Limit 5";

		List<Titulo> livros = new ArrayList<>();

		try {
			stm = con.prepareStatement(sql);

			stm.setString(1, "%" + input + "%");

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				livros.add(new Titulo(rs.getString("titulo")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return livros;
	}

	class Titulo {
		@SuppressWarnings("unused")
		private String titulo;
		public Titulo(String titulo) {
			this.titulo = titulo;
		}
	}
}
