<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>AdminUser Edit Page</title>
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
			Authentication and Authorization &rsaquo; Users
		</div>
		<!--  content  -->
		<p style="color: red;">${errorString}</p>
		<div id="content" class="colMS">
			<h1>Administration Users Maintainance</h1>

			<div id="content_main">
				<ul class="object-tools">
					<li><a href="${pageContext.request.contextPath}/adminUserAdd"
						class="addlink"> Add user </a></li>
				</ul>
				<div class="app-auth module">
					<table>

						<caption>
							<span class="section"> Administration Users to be Changed
							</span>
							<!--  
							<a href="${pageContext.request.contextPath}/auth" class="section"
								title="authtication modules in the application">
								AUTHENTICATION AND AUTHORIZATION</a>
							-->
						</caption>
						<c:forEach items="${adminUserList}" var="user">
							<tr class="model-user">
								<th scope="row"><a
									href="${pageContext.request.contextPath}/adminUserEdit?username=${user.userName}&email=${user.email}">${user.userName}</a></th>
								<td><a
									href="${pageContext.request.contextPath}/adminUserEdit?username=${user.userName}&email=${user.email}"
									class="changelink">Edit</a>
								<td><a
									href="${pageContext.request.contextPath}/adminUserDelete?username=${user.userName}"
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