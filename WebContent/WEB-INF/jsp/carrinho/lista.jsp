<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrinho de Compras</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>
	<c:if test='${fn:length(sessionScope.carrinho.livros) < 1}'>
		<c:redirect url="crtl.do?crtl=EstanteCrtl" />
	</c:if>
	<div class="container">
		<div class="bs-example">
			<h1>Carrinho Compras</h1>
			<table class="table">
				<tr>
					<th>Titulo</th>
					<th>Valor</th>
					<th>Qtd</th>
					<th>Remover</th>
				</tr>
				<c:forEach items="${sessionScope.carrinho.livros}" var="livro">
					<tr>
						<td>${livro.titulo}</td>
						<td><fmt:setLocale value="pt_BR" /> <fmt:formatNumber
								value="${livro.preco}" pattern="R$ #,##0.00" /></td>
						<td>
							<strong>${livro.qtd}</strong>
						</td>
						<td><a class="btn btn-danger"
							href='<c:url value='crtl.do?crtl=CarrinhoCrtl&item=${livro.titulo}'/>'>Remove</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${not empty sessionScope.carrinho.livros}">
				<span>Total da Compra </span>
				<fmt:setLocale value="pt_BR" />
				<fmt:formatNumber value="${sessionScope.carrinho.total}"
					pattern="R$ #,##0.00" />
				<c:if test="${ empty sessionScope.usuLogado}">
					<a href="<c:url value='/service.do?service=FormLogar'/>"> <i
							class="glyphicon glyphicon-ok"></i> Logar
					</a>
				</c:if>
				<div>
					<a class="btn btn-success" href="<c:url value='crtl.do?crtl=EstanteCrtl'/>">Continuar Comprando</a>
				</div>
			</c:if>
			<a class="btn btn-info" href='<c:url value='service.do?service=FormEndereco' />'> Adicionar Endereco</a>
			<c:if test="${not empty enderecos}">
				<div>
					<form action='<c:url value="service.do"/>' method="post">
					<input type="hidden" name="service" value="CarrinhoDetails">
					<label> Endereco Para Entrega </label> 
					<select name=endereco>
					<c:forEach items="${enderecos}" var="e">
						<option value="${e}">${e.logradouro} em ${e.cidade}</option>
					</c:forEach> 
					</select>
					<button class="btn btn-success"> Confirmar</button>
					</form>
				</div>
			</c:if>
		</div>
	</div>

</body>
</html>