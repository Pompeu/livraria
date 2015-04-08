<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users Detalhes</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>

	<div class="container">
		<p>${user.id}</p>
		<p>${user.nome}</p>
		<p>${user.cpf}</p>
		<p>${user.email}</p>
		<p>${user.nivel}</p>
		<c:set var="user" scope="session" value="${user}"></c:set>
		<div class="container">
			<a href="<c:url value='/service.do?service=FormUser'/>">
			 	<i class="glyphicon glyphicon-ok"></i>
			 <span>Editar</span>
			</a>
		</div>
		
		<div class="container">
			<a  href="<c:url value='/'/>">
				<i class="glyphicon  glyphicon-home"></i>
			<span>Voltar as Compras</span></a>
		</div>
	</div>
</body>
</html>