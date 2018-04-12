<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Roles Edit Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/dashboard.css" />
</head>


<body class=" dashboard">
	<!--  Container -->
	<div id="container">
		<div id="header">
			<jsp:include page="../_header.jsp"></jsp:include>
		</div>

		<div class="breadcrumbs">
			<a href="${pageContext.request.contextPath}/home">Home</a> &rsaquo;
			Online_shopping &rsaquo; Roles
		</div>
		<!--  content  -->
		<p style="color: red;">${errorString}</p>
		<div id="content" class="colMS">
			<h1>Roles</h1>

			<div id="content_main">
				<ul class="object-tools">
					<li><a href="${pageContext.request.contextPath}/roleAdd"
						class="addlink"> Add Role </a></li>
				</ul>
				<div class="app-auth module">
					<table>

						<caption>
							<span class="section"> Roles to be Changed
							</span>
						</caption>
						<c:forEach items="${roleList}" var="role">
							<tr class="model-user">
								<th scope="row"><a
									href="${pageContext.request.contextPath}/roleEdit?id=${role.roleId}">${role.name}</a></th>
								<td><a
									href="${pageContext.request.contextPath}/roleEdit?id=${role.roleId}"
									class="changelink">Edit</a>
								<td><a
									href="${pageContext.request.contextPath}/roleDelete?id=${role.roleId}"
									class="deletelink">Delete</a>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<!-- End Content -->
	</div>
	<!--  End Container -->
</body>
</html>