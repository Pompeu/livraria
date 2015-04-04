<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../includes/header.jsp"></c:import>

<c:if test="${sessionScope.usuLogado == null}">
	<div class="container" style="margin-top: 40px">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" style="text-align: center;">Livraria</h3>
					</div>
					<div class="panel-body">
						<form class="form-signin" action="<c:url value='/crtl.do'/>"
							method="post">
							<h3 class="form-signin-heading" style="text-align: center;">Logar
								no Sistema</h3>
							<input type="hidden" name="crtl" value="LogarCrtl">
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span> <input name="email" class="form-control" type="email"
									autofocus="" required="" placeholder="Digite o Email">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-lock"></i>
								</span> <input name="password" class="form-control" type="password"
									required="" placeholder="Digite o Senha">
							</div>
							<button class="btn btn-lg btn-primary btn-block" type="submit">
								Logar</button>
							<c:if test="${requestScope.result != null}">
								<span class="text-danger">${requestScope.result}</span>
							</c:if>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>