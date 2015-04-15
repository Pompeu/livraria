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
	
	<div class="container">
		<h1>Dados da Compra</h1>
		<div>
			<h2>Dados do Cliente</h2>
			<div>Nome : ${sessionScope.usuLogado.nome}</div>
			<div>CPF : ${sessionScope.usuLogado.cpf} </div>
		</div>
		<div>
			<h3>Endereco para Entregar</h3>
			<div> Cidade : ${endereco.cidade}</div>
			<div> Rua : ${endereco.logradouro}</div> 
			<div> Numero :${endereco.numero}</div> 
			<div> Cep : ${endereco.cep}</div>
		</div>
		<div>
			<h3>Total da compra</h3>
			<div>Valor total : 
				<fmt:setLocale value="pt_BR" />
				<fmt:formatNumber value="${sessionScope.carrinho.total}"
					pattern="R$ #,##0.00" /></div>
		</div>
		<div>
			<a href="<c:url value="service.do?service=GerarBoleto&cep=${endereco.cep}" />">Gerar
				Boleto</a>
		</div>
		
	</div>
</body>
</html>