<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../includes/header.jsp"></c:import>
<c:if test="${not empty newpass}">
	<h1>Sua noava Senha é ${newpass}</h1>
	<a href="<c:url value='/service.do?service=FormLogar'/>"> <i
		class="glyphicon glyphicon-ok"></i> Logar
	</a>
</c:if>
<c:if test="${empty newpass}">
	<div class="container" style="margin-top: 40px">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" style="text-align: center;">Recupear
							Password</h3>
					</div>
					<div class="panel-body">
						<form class="form-signin" action="<c:url value='/crtl.do'/>"
							method="post">
							<h3 class="form-signin-heading" style="text-align: center;">Recupear
								Password</h3>
							<input type="hidden" name="crtl" value="RecPasswordCrtl">
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-envelope"></i>
								</span> <input name="email" class="form-control" type="email" autofocus=""
									required="" placeholder="Digite o Email">
							</div>
							<button class="btn btn-lg btn-primary btn-block" type="submit">
								Recuperar</button>
							<c:if test="${not empty result}">
								<span class="text-danger">${result}</span>
							</c:if>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>