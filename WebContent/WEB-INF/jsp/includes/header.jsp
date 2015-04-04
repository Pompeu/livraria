<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css"
	href="<c:url value='css/style.css'/>">
<div>
	<div>
		<form action='<c:url value="service.do"/>' method="post">
			<input type="hidden" name="service" value="BuscarLivro">
			<div>
				<label>Buscar Livros</label> <input type="text" name="titulo">
				<button type="submit">Buscar</button>
			</div>
		</form>
	</div>
	<div>
		<c:if test="${sessionScope.usuLogado != null}">
		Ola <span>${sessionScope.usuLogado.nome}</span>
			<a href="<c:url value='/crtl.do?crtl=LogoutCrtl'/>"> não sou
				${sessionScope.usuLogado.nome} <b>ou sair</b>
			</a>
		</c:if>
	</div>
	<a href='<c:url value="crtl.do?crtl=EstanteCrtl"/>'>Estante</a>
	<c:if test="${sessionScope.usuLogado == null}">
		<a href="<c:url value='/service.do?service=FormLogar'/>">Logar</a>
	</c:if>
	<a href="<c:url value='/service.do?service=FormUser'/>">Cadastrar</a>
	<c:if test="${sessionScope.usuLogado.nivel eq 'ADMIN'}">
		<a href="<c:url value='/service.do?service=FormLivro'/>">Cadastrar
			Livros</a>
	</c:if>
	<a href='<c:url value="crtl.do?crtl=CarrinhoCrtl"/>'><strong>Carrinho</strong></a>
</div>