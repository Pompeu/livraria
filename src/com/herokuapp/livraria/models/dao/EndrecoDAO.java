package com.herokuapp.livraria.models.dao;

import java.util.List;

import com.herokuapp.livraria.models.Endereco;
import com.herokuapp.livraria.models.User;

public interface EndrecoDAO {

	Endereco addEndereco(Endereco endereco);

	void removeEndreco(Endereco endereco);

	List<Endereco>  getEnderecoByUser(User user);

	Endereco getEnderecoById(Integer id);

}
