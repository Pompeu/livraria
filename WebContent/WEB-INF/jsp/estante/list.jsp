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
				<input type="hidden" value="${livro.id}>"> <img class="img-book"
					width="100px" height="80px" alt="${livro.titulo}"
					src="${livro.imagem}">
				<h5>${livro.titulo}</h5>
				<h4>
					Preço :
					<fmt:setLocale value="pt_BR" />
					<fmt:formatNumber value="${livro.preco}" pattern="R$ #,##0.00" />
				</h4>
				<div>
					<form class="form-inline" action='<c:url value="service.do"/>'
						method="post">
						<input type="hidden" name="service" value="AddLivro"> <input
							type="hidden" name="id" value="${livro.id}">
						<div class="form-group">
							<input class="form-control" type="text" value="" name="qtd"
								placeholder="Quantidade" required>
						</div>
						<div class="form-group">
							<button class="btn btn-primary" type="submit">Comprar</button>
						</div>
						<c:if test="${sessionScope.usuLogado.nivel eq 'ADMIN'}">
							<div class="form-group">
								<a
									href="<c:url value="/service.do?service=FormLivro&id=${livro.id}"/>">Editar</a>
							</div>
						</c:if>
					</form>
				</div>
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