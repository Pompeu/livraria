package com.herokuapp.livraria.models.dao;

import com.herokuapp.livraria.models.Carrinho;

public interface CarrinhoDAO {
	
	Carrinho fecharCarrinho(Carrinho carrinho);
	boolean deletarCarrinho(Carrinho carrinho);
}
