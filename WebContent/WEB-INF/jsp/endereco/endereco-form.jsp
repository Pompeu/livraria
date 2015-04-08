<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro endereco</title>
</head>
<body>
	<c:import url="../includes/header.jsp"></c:import>
	<div class="container" style="margin-top: 40px">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" style="text-align: center;">Cadastro
							de Ederecos</h3>
					</div>
					<div class="panel-body">
						<form class="form-signin" action="<c:url value='/service.do'/>"
							method="post">
							<input type="hidden" name="service" value="AddEndereco">
							<input type="hidden" name="id" value="${endereco.id}">
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"> Cidade</span> <input
									class="form-control" type="text" name="cidade"
									value="${endereco.cidade}">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"> Estado</span> <select
									class="form-control" name=estado>
									<c:forEach items="${estados}" var="e">
										<option value="${e}">${e}</option>
									</c:forEach>
								</select>
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon">Bairro </span> <input
									class="form-control" type="text" name="bairro"
									value="${endereco.bairro}">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon">Logradouro </span> <input
									class="form-control" type="text" name="logradouro"
									value="${endereco.logradouro}">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon">CEP </span> <input
									class="form-control" type="text" name="cep"
									value="${endereco.cep}">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon">Numero </span> <input
									class="form-control" type="text" name="numero"
									value="${endereco.numero}">
							</div>
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon">Complemento </span> <input
									class="form-control" type="text" name="complemento"
									value="${endereco.complemento}">
							</div>
							<button class="btn btn-lg btn-primary btn-block" type="submit">
								Salvar</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>