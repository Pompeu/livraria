<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css"
	href="<c:url value='css/style.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='css/style.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='bower_components/bootstrap/dist/css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='bower_components/bootstrap/dist/css/bootstrap-theme.min.css'/>">
<script
	src="<c:url value='bower_components/jquery/dist/jquery.min.js'/>">
	
</script>
<script
	src="<c:url value='bower_components/bootstrap/dist/js/bootstrap.min.js'/>">
	
</script>


<nav class="role="
	navigation" class="navbar navbar-default navbar-fixed-top">
<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="<c:url value='/'/>"> <i
			class="glyphicon glyphicon-home"></i> Livraria
		</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<li><a href="<c:url value='/service.do?service=FormUser'/>">
					<i class="glyphicon glyphicon-thumbs-up"></i> Cadastrar
			</a></li>
			<li><c:if test="${sessionScope.usuLogado == null}">
					<a href="<c:url value='/service.do?service=FormLogar'/>"> <i
						class="glyphicon glyphicon-ok"></i> Logar
					</a>
				</c:if></li>
			<li><c:if test="${sessionScope.usuLogado.nivel eq 'ADMIN'}">
					<a href="<c:url value='/service.do?service=FormLivro'/>"> <i
						class=""></i> Cadastrar Livros
					</a>
				</c:if></li>
			<li>
				<div>
					<form class="navbar-form navbar-right" role="titulo"
						action='<c:url value="service.do"/>' method="post">
						<input type="hidden" name="service" value="BuscarLivro">
						<div class="form-group">
							<label for="titulo"> <i
								class="glyphicon glyphicon-search"></i>
							</label> <input class="form-control" id="titulo" type="text"
								name="titulo">
							<button type="submit" class="btn btn-success">Buscar</button>
						</div>
					</form>
				</div>
			</li>
			<li><c:if test="${not empty sessionScope.carrinho.livros}">
					<a href='<c:url value="crtl.do?crtl=CarrinhoCrtl"/>'><i
						class="glyphicon glyphicon-shopping-cart"></i> Carrinho</a>
				</c:if></li>
			<li><c:if test="${sessionScope.usuLogado != null}">
					<a href="<c:url value='/crtl.do?crtl=LogoutCrtl'/>"> Ola Sr <span>${sessionScope.usuLogado.nome}</span>
						<i class="glyphicon glyphicon-off"></i> Sair
					</a>
				</c:if></li>
		</ul>
	</div>
</div>
</nav>