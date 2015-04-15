<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Livros</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>
	<div class="container" style="margin-top: 40px">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" style="text-align: center;">Cadastro
							de Livros</h3>
					</div>
					<div class="panel-body">
						<form class="form-signin" action="<c:url value='/service.do'/>"
							method="post" enctype="multipart/form-data">
							<input type="hidden" name="service" value="CriarLivro"> <input
								type="hidden" name="id" value="${livro.id}">
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon">Titulo </span> <input
									class="form-control" type="text" name="titulo"
									value="${livro.titulo}">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"> Autor</span> <input
									class="form-control" type="text" name="autor"
									value="${livro.autor}">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon">Categoria </span> <input
									class="form-control" type="text" name="category"
									value="${livro.category}">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon">ISBN </span> <input
									class="form-control" type="text" name="isbn"
									value="${livro.isbn}">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon">Quantidade </span> <input
									class="form-control" type="text" name="qtd"
									value="${livro.qtd}">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon">Preço </span> <input
									class="form-control" type="text" name="preco"
									value="${livro.preco}">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<label for="image" class="input-group-addon"> Imagem </label>
								<input id="image" class="btn btn-xs btn-primary" type="file" name="file"/>
							</div>
							<c:if test="${not empty livro}">
								<div style="margin: 0px auto" class="input-group">
									<img width="100px" height="100px" alt="${livro.titulo}" src="${livro.imagem}">
									<input type="hidden" name="imagem" value="${livro.imagem}">
								</div>
							</c:if>
							<button class="btn btn-lg btn-primary btn-block" type="submit">Salvar</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>