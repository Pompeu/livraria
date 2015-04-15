package com.herokuapp.livraria.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Set;

public class Carrinho implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private BigDecimal total;
	private int qtdLivros;
	private User user;
	private LocalDateTime data;
	private EstadoCarrinho estadoDoCarrinho;
	private Set<Livro> livros;

	public Carrinho() {
	}

	public Carrinho(Set<Livro> livros, User user,
			EstadoCarrinho estadoDoCarrinho) {
		this.livros = livros;
		this.user = user;
		this.estadoDoCarrinho = estadoDoCarrinho;
		this.data = LocalDateTime.now();
	}

	public Carrinho(Set<Livro> livros, BigDecimal total, User user,
			EstadoCarrinho estadoDoCarrinho, LocalDateTime data) {
		this.livros = livros;
		this.total = total;
		this.user = user;
		this.estadoDoCarrinho = estadoDoCarrinho;
		this.data = data;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public EstadoCarrinho getEstadoDoCarrinho() {
		return estadoDoCarrinho;
	}

	public Set<Livro> getLivros() {
		return livros;
	}

	public BigDecimal getTotal() {
		this.total = calcTotal();
		return total.setScale(2, RoundingMode.CEILING);
	}

	public int getQtd() {
		return qtdLivros;
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getData() {
		return data;
	}

	private BigDecimal calcTotal() {
		double sum = 0;
		if (livros != null) {
			sum = livros
					.stream()
					.mapToDouble(
							livro -> livro.getPreco().doubleValue()
									* livro.getQtd()).sum();
		}
		return new BigDecimal(sum);
	}

	public int calcQtd() {
		return this.livros.size();
	}

	@Override
	public String toString() {
		return "Carrinho [livros=" + livros + ", total=" + total + ", qtd="
				+ qtdLivros + ", user=" + user + ", data=" + data
				+ ", estadoDoCarrinho=" + estadoDoCarrinho + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrinho other = (Carrinho) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
