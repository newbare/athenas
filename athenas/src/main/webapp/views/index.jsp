<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>


	<head>
		<title>Spring MVC com AngularJs</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/screen.css"/>"/>
	</head>

	<body>
	
		<div id="container">
			<div class="dualbrand">
				
			</div>
			<div id="content">
				<h1>Bem Vindo!</h1>

				<div>
					<p>
						<h3>O Projeto Spring MVC foi implantado com sucesso- esta página é jsp.</h3>	
					</p>				
					<img src="<c:url value="/static/resources/gfx/angularjs.png"/>"/><br />
				</div>

				<form:form commandName="newMember" id="reg">
					<h2>Registro de Membros</h2>					
					<table>
						<tbody>
							<tr>
								<td><form:label path="name">Nome:</form:label></td>
								<td><form:input path="name"/></td>
								<td><form:errors class="invalid" path="name"/></td>
							</tr>
							<tr>
								<td><form:label path="email">Email:</form:label></td>
								<td><form:input path="email"/></td>
								<td><form:errors class="invalid" path="email"/></td>
							</tr>
							<tr>
								<td><form:label path="phoneNumber">Fone #:</form:label>
								<td><form:input path="phoneNumber"/></td>
								<td><form:errors class="invalid" path="phoneNumber"/></td>
							</tr>
	
						</tbody>
					</table>
					<table>
						<tr>
							<td>
								<input type="submit" value="Register" class="register"/>
							</td>
						</tr>
					</table>
				</form:form>
				<h2>Members</h2>
				<c:choose>
					<c:when test="${members.size()==0}">
						<em>Não existem Membros registrados na Base.</em>
					</c:when>
					<c:otherwise>
						<table class="simpletablestyle">
							<thead>
								<tr>
									<th>Id</th>
									<th>Nome</th>
									<th>Email</th>
									<th>Fone</th>
									<th>URL REST</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${members}" var="member">
									<tr>
										<td>${member.id}</td>
										<td>${member.name}</td>
										<td>${member.email}</td>
										<td>${member.phoneNumber}</td>
										<td><a href="<c:url value="/rest/members/${member.id}"/>">/rest/members/${member.id}</a></td>
								</c:forEach>
							</tbody>
						</table>
						<table class="simpletablestyle">
							<tr>
								<td>
									 URL REST para Todos os Membros: <a href="<c:url value="/rest/members"/>">/rest/members</a>
								</td>
							</tr>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="aside">
					<img src="<c:url value="/static/resources/gfx/spMvc.png"/>"/><br />
					<img src="<c:url value="/static/resources/gfx/hiber.png"/>"/><br />
					<img src="<c:url value="/static/resources/gfx/tom.png"/>"/>		<br />			
			</div>
			
			<div id="footer">
			    <p>
					Este Projeto foi gerado com base em um archetype Maven baseado no JBoss e customizado por Jefferson Leite.<br />
			    </p>
			</div>
		</div>
			
	</body>
</html>
