<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro endereco</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>

	<form action="<c:url value='/service.do'/>" method="post">
		<input type="hidden" name="service" value="AddEndereco">
		 <input type="hidden" name="id" value="${endereco.id}">
		<div>
			<label> Cidade</label> <input type="text" name="cidade"
				value="${endereco.cidade}">
		</div>
		<div>
			<label> Estado</label> 
			<select name=estado>
			<c:forEach items="${estados}" var="e">
				<option value="${e}">${e}</option>
			</c:forEach> 
			</select>
		</div>
		<div>
			<label>Logradouro </label> <input type="text" name="logradouro"
				value="${endereco.logradouro}">
		</div>
		<div>
			<label>CEP </label> <input type="text" name="cep"
				value="${endereco.cep}">
		</div>
		<div>
			<label>Numero </label> <input type="text" name="numero"
				value="${endereco.numero}">
		</div>
		<div>
			<label>Complemento </label> <input type="text" name="complemento"
				value="${endereco.complemento}">
		</div>
		<button type="submit">Salvar</button>
	</form>
</body>
</html>