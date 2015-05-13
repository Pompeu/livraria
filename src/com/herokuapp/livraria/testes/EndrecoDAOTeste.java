package com.herokuapp.livraria.testes;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.herokuapp.livraria.models.Endereco;
import com.herokuapp.livraria.models.Estados;
import com.herokuapp.livraria.models.User;
import com.herokuapp.livraria.models.dao.DAO;
import com.herokuapp.livraria.models.dao.EnderecoImpl;
import com.herokuapp.livraria.models.dao.EndrecoDAO;
import com.herokuapp.livraria.models.dao.UserImpl;
import com.herokuapp.livraria.uteis.JdbcFactory;

public class EndrecoDAOTeste {

	private Endereco endereco;
	private DAO<User> userDao = new UserImpl(JdbcFactory.getInstance()
			.getConnection());
	private User user = userDao.retriveById(5);

	private String cidade = "Butiti Alegre";
	private String bairro = "Centro";
	private String logradouro = "Rua 24 de Junho";
	private String cep = "75660000";
	private String numero = "104";
	private String complemento = "proximo ao banco do brasil";

	private EndrecoDAO endDao = new EnderecoImpl(JdbcFactory.getInstance().getConnection());

	@Before
	public void before() {
		endereco = new Endereco(user, Estados.Goias, cidade,bairro, logradouro, cep,
				numero, complemento);
	}

	@After
	public void After() {
		endDao.removeEndreco(endereco);
	}

	@Test
	public void enderecoCreate() {

		assertEquals(cidade, endereco.getCidade());
		assertEquals(logradouro, endereco.getLogradouro());
		assertEquals(cep, endereco.getCep());
		assertEquals(numero, endereco.getNumero());
		assertEquals(complemento, endereco.getComplemento());

	}

	@Test
	public void addUserInEndreco() {
		Endereco addEndereco = endDao.addEndereco(endereco);
		assertEquals(addEndereco, endereco);
		endereco = addEndereco;
	}

	@Test
	public void getEnderecosByUser() {
		User user = userDao.retriveById(5);
		assertNotNull(user);
		List<Endereco> enderecoByUser = endDao.getEnderecoByUser(user);
		assertNotNull(enderecoByUser);
		assertTrue(enderecoByUser.size() > 0);
	}
	@Test
	public void userwithoutendereco() {
		User user = userDao.retriveById(4);
		assertNotNull(user);
		List<Endereco> enderecoByUser = endDao.getEnderecoByUser(user);
		assertNotNull(enderecoByUser);
	}
	@Test
	public void getEnderecobyId(){
		
		Endereco enderecoById = endDao.getEnderecoById(30);
		assertNotNull(enderecoById);
	}
}
