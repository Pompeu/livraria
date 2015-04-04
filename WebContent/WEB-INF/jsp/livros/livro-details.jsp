<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Livro Detalhes</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>
	<c:if test="${not empty livro}">
		<h1>${livro.titulo}</h1>
		<h2>${livro.autor}</h2>
		<h4>${livro.category}</h4>
		<span>${livro.qtd}</span>
		<span>${livro.isbn}</span>
    	<div>    		
    		<fmt:setLocale value="pt_BR"/>
    		<fmt:formatNumber value="${livro.preco}" pattern="R$ #,##0.00"/>
    	</div>  
		<a
			href="<c:url value="/service.do?service=EditarLivro&id=${livro.id}"/>">Editar</a>
		<a
			href="<c:url value="/service.do?service=DeletarLivro&id=${livro.id}"/>">Deletar</a>
	</c:if>

</body>
</html>