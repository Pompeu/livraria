<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalhes da Compra</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>
	<h1>Dados da Compra</h1>
	<div>
		<div>
			<h2>Dados do Cliente</h2>
			${sessionScope.usuLogado.nome} ${sessionScope.usuLogado.cpf}
		</div>
		<div>
			<h3>Endereco para Entregar</h3>
			${endereco.logradouro} ${endereco.numero} ${endereco.cep}
		</div>
		<div>
			<a href="<c:url value="service.do?service=GerarBoleto&cep=${endereco.cep}" />">Gerar
				Boleto</a>
		</div>
	</div>
</body>
</html>