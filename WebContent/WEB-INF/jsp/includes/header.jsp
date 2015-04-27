<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<c:import url="../includes/css.jsp"></c:import>
<c:import url="../includes/javascripts.jsp"></c:import>

<nav ng-app="app" class="navbar navbar-default" role="
	navigation"
	class="navbar navbar-default navbar-fixed-top">
<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="<c:url value='/'/>"> <i
			class="glyphicon glyphicon-home"></i> Livraria
		</a>
	</div>
	<div>
		<ul class="nav navbar-nav">
			<li><a href="<c:url value='/service.do?service=FormUser'/>">
					<i class="glyphicon glyphicon-thumbs-up"></i> Novo Usuario
			</a></li>
			<li><c:if test="${empty sessionScope.usuLogado}">
					<a href="<c:url value='/service.do?service=FormLogar'/>"> <i
						class="glyphicon glyphicon-ok"></i> Logar
					</a>
				</c:if></li>
			<li><c:if test="${sessionScope.usuLogado.nivel eq 'ADMIN'}">
					<a href="<c:url value='/service.do?service=FormLivro'/>"> <i
						class="glyphicon glyphicon-book"></i> Cadastrar Livros
					</a>
				</c:if></li>
			<li>
				<div ng-controller="AutoCompleteCrtl">
					<form class="navbar-form navbar-right" role="titulo"
						action='<c:url value="service.do"/>' method="post">
						<input type="hidden" name="service" value="BuscarLivro">
						<div class="form-group">
							<label for="titulo"> <i
								class="glyphicon glyphicon-search"></i>
							</label> <input class="form-control" id="titulo" type="text"
								name="titulo" ng-keyup="pesquisar(search)" ng-model="search"
								autofocus autocomplete="off">
							<div class="autocomplete" ng-show="completing">
								<a class="link" ng-click="addValueSearch(dica)" ng-repeat="dica in dicas">
								  <span>{{dica.titulo}}</span> <br />
								</a>
							</div>
							<button type="submit" class="btn btn-success">Buscar
								Livros</button>
						</div>
					</form>
				</div>
			</li>
			<li><c:if test="${not empty sessionScope.carrinho.livros}">
					<a href='<c:url value="crtl.do?crtl=CarrinhoCrtl"/>'><i
						class="glyphicon glyphicon-shopping-cart"></i> Carrinho</a>
				</c:if></li>
			<li><c:if test="${not empty sessionScope.usuLogado}">
					<a href="<c:url value='/service.do?service=UserDetails'/>"> <i
						class="glyphicon glyphicon-user"></i> Ola <span>${sessionScope.usuLogado.nome}</span>
					</a>
				</c:if></li>
			<li><c:if test="${not empty sessionScope.usuLogado}">
					<a href="<c:url value='/crtl.do?crtl=LogoutCrtl'/>"> <i
						class="glyphicon glyphicon-off"></i> Sair
					</a>
				</c:if></li>
		</ul>
	</div>
</div>
</nav>