<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario de Usuario</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>
	<div class="container" style="margin-top: 40px">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" style="text-align: center;">Cadastro User</h3>
					</div>
					<div class="panel-body">
						<form class="form-signin" action="<c:url value='/service.do'/>"
							method="post">
							<input type="hidden" name="service" value="CriarUser">
							<div style="margin-bottom: 25px" class="input-group">	 
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span>
							 	<input name="nome"  class="form-control" type="text" value=""
							 	  autofocus=""  required="" placeholder="Digite o nome">
							</div>
							<div style="margin-bottom: 25px" class="input-group">	 
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-knight"></i>
								</span>
							 	<input name="cpf"  class="form-control" type="text" value="" 
							 	 autofocus=""  required="" placeholder="Digite o cpf">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-envelope"></i>
								</span>
								 <input name="email" class="form-control" type="email"
									required="" placeholder="Digite o Email">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-lock"></i>
								</span> <input name="password" class="form-control" type="password"
									required="" placeholder="Digite a Senha">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<c:if test="${sessionScope.usuLogado.nivel eq 'ADMIN'}">
									<label>ADMIN</label>
									<input type="radio" name="nivel" value="ADMIN">
								</c:if>
								 <label>CLIENTE</label>
								 <input	type="radio" name="nivel" value="CLIENTE" checked="checked">
							</div>
							<button class="btn btn-lg btn-primary btn-block" type="submit">
								Cadastrar</button>
							<c:if test="${requestScope.result != null}">
								<span class="text-danger">${requestScope.result}</span>
							</c:if>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>