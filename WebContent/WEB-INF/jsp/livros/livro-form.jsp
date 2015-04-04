<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de  Livros</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>
	<form action="/livaria/service.do" method="post">
	
		<input type="hidden" name="service" value="CriarLivro">
		<input type="hidden" name="id" value="${livro.id}">
		<div>
			<label>Titulo </label> <input type="text" name="titulo"
				value="${livro.titulo}">
		</div>
		<div>
			 <label>
				Autor</label> <input type="text" name="autor" 
				value="${user.autor}">
		</div>
		<div>
			<label>Categoria </label> <input type="text" name="category"
				value="${livro.category}">
		</div>
		<div>
			<label>ISBN </label> <input type="text" name="isbn"
				value="${livro.isbn}">
		</div>
			<div>
			<label>Quantidade </label> <input type="text" name="qtd"
				value="${livro.qtd}">
		</div>
		<div>
			<label>Preço </label> <input type="text" name="preco"
				value="${livro.preco}">
		</div>		
		<button type="submit">Salvar</button>

	</form>
</body>
</html>