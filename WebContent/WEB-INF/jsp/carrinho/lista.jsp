<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrinho de Compras</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>
	<a href="<c:url value='crtl.do?crtl=EstanteCrtl'/>">Comprar Mais</a>
	<div>
		<div>
			<h1>Carrinho Compras</h1>
			<table>
				<tr>
					<th>Titulo</th>
					<th>Valor</th>
					<th>Qtd</th>
					<th>remove</th>
				</tr>
				<c:forEach items="${sessionScope.carrinho.livros}" var="livro">
					<tr>
						<td>${livro.titulo}</td>
						<td><fmt:setLocale value="pt_BR" /> <fmt:formatNumber
								value="${livro.preco}" pattern="R$ #,##0.00" /></td>
						<td><input type="text" value="1"></td>
						<td><a
							href='<c:url value='crtl.do?crtl=CarrinhoCrtl&item=${livro.titulo}'/>'>Remove</a></td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${not empty sessionScope.carrinho.livros}">
				<span>Total </span>
				<fmt:setLocale value="pt_BR" />
				<fmt:formatNumber value="${sessionScope.carrinho.total}"
					pattern="R$ #,##0.00" />
				<a href="#">Recalcular</a>
				<a href="#">Finalizar Comprar</a>
			</c:if>
		</div>
	</div>
</body>
</html>