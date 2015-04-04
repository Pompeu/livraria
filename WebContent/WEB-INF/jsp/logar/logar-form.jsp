<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../includes/header.jsp"></c:import>
<c:if test="${sessionScope.usuLogado == null}">
	<form action="<c:url value='/crtl.do'/>" method="post">
		<input type="hidden" name="crtl" value="LogarCrtl">
		<div>
			<label>Email</label> <input type="email" name="email"
				value="${user.email}">
		</div>
		<div>
			<label>Passoword</label> <input type="password" name="password"
				value="${user.password}">
		</div>
		 <c:if test="${requestScope.result != null}">		
			<span>${requestScope.result}</span> 
		</c:if>
		<button type="submit">Logar</button>
	</form>
</c:if>