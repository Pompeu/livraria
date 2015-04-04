<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario de Usuario</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>
	<form action="<c:url value='/service.do'/>" method="post">
		<div>
			<input type="hidden" name="service" value="CriarUser">
			<input type="hidden" name="id" value="${user.id}"> <label>
				Nome</label> <input type="text" name="nome" value="${user.nome}">
		</div>
		<div>
			<label>Cpf</label> <input type="text" name="cpf"
				value="${user.cpf}">
		</div>
		<div>
			<label>Email</label> <input type="email" name="email"
				value="${user.email}">
		</div>
		<div>
			<label>Passoword</label> <input type="password" name="password"
				value="${user.password}">
		</div>
		<div>
			<c:if test="${sessionScope.usuLogado.nivel eq 'ADMIN'}">
				<label>ADMIN</label>
				<input type="radio" name="nivel" value="ADMIN">
			</c:if>
			 <label>CLIENTE</label> <input
				type="radio" name="nivel" value="CLIENTE" checked="checked">
		</div>
		<button type="submit">Salvar</button>
	</form>
</body>
</html>