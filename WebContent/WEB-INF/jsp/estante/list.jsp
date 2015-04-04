<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estante de Livros</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>
	<div class="main">
		<c:forEach items="${livros}" var="livro">
			<div class="main-livro">
				<input type="hidden" value="${livro.id}>">
				<h4>${livro.titulo}</h4>
				<span>ISBN é ${livro.isbn} </span>
				<h5>De ${livro.autor}</h5>
				<h3>
					Preço :
					<fmt:setLocale value="pt_BR" />
					<fmt:formatNumber value="${livro.preco}" pattern="R$ #,##0.00" />
				</h3>
				<a
					href='<c:url value="service.do?service=AddLivro&id=${livro.id}"/>'><strong>Comprar</strong></a>
			</div>

		</c:forEach>
		<c:if test="${fn:length(livros)/6 == 1}">
		<c:set var="len" scope="session" value="${fn:length(livros)}" />
		<fmt:parseNumber var="i" pattern="#" value="${len}" />
		<a href='<c:url value="crtl.do?crtl=EstanteCrtl&pagina=${i}"/>'>
			Proxima</a>
		</c:if>
	</div>
</body>
</html>