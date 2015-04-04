package com.herokuapp.livraria.models;

public class Endereco {

	private Integer id;
	private User user;
	private Estados estado;
	private String cidade;
	private String logradouro;
	private String cep;
	private String numero;
	private String complemento;

	Endereco() {
	}

	public Endereco(User user, Estados estado, String cidade, String logradouro,
			String cep, String numero, String complemento) {
		this.user = user;
		this.estado = estado;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}

	public Endereco(Integer id, User user, Estados estado, String cidade,
			String logradouro, String cep, String numero, String complemento) {
		this.id = id;
		this.user = user;
		this.estado = estado;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public Estados getEstado() {
		return estado;
	}

	public String getCidade() {
		return cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getCep() {
		return cep;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [user=" + user + ", estado=" + estado + ", cidade="
				+ cidade + ", logradouro=" + logradouro + ", cep=" + cep
				+ ", numero=" + numero + ", complemento=" + complemento + "]";
	}

}
