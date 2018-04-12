<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/dashboard.css" />
</head>
<body class=" dashboard">
	<!--  Container -->
	<div id="container">
		<div id="header">
			<jsp:include page="_header.jsp"></jsp:include>
		</div>

		<!--  content  -->
		<div id="content" class="colMS">
			<h1>
				<a href="${pageContext.request.contextPath}/home">Site
					administration</a>
			</h1>

			<div id="content_main">
				<div class="app-auth module">
					<table>
						
						<caption>
							<span class="section">
								AUTHENTICATION AND AUTHORIZATION
							</span>
							<!--  
							<a href="${pageContext.request.contextPath}/auth" class="section"
								title="authtication modules in the application">
								AUTHENTICATION AND AUTHORIZATION</a>
							-->
						</caption>
						<tr class="model-user">
							<th scope="row"><a
								href="${pageContext.request.contextPath}/adminUser">Users</a></th>
							<td><a href="${pageContext.request.contextPath}/adminUserAdd"
								class="addlink">Add</a>
							<td><a href="${pageContext.request.contextPath}/adminUser"
								class="changelink">Change</a>
						</tr>
					</table>
				</div>

				<div class="app-online_shopping module">
					<table>
						<caption>
						<span class="section">
								ONLINE_SHOPPING
						</span>
						<!--  
							<a href="${pageContext.request.contextPath}/onlineShopping"
								class="section"
								title="onlineshopping modules in the application">
								ONLINE_SHOPPING</a>
						-->
						</caption>
						
						<tr class="model-useraccount">
							<th scope="row"><a
								href="${pageContext.request.contextPath}/userAccount">User
									accounts</a></th>
							<td><a
								href="${pageContext.request.contextPath}/userAccountAdd"
								class="addlink">Add</a></td>
							<td><a href="${pageContext.request.contextPath}/userAccount"
								class="changelink">Change</a></td>
						</tr>
						
						<tr class="model-checkoutorder">
							<th scope="row"><a
								href="${pageContext.request.contextPath}/checkoutOrder">Checkout
									orders</a></th>
							<td><a
								href="${pageContext.request.contextPath}/checkoutOrderAdd"
								class="addlink">Add</a></td>
							<td><a
								href="${pageContext.request.contextPath}/checkoutOrder"
								class="changelink">Change</a></td>
						</tr>

						<tr class="model-item">
							<th scope="row"><a
								href="${pageContext.request.contextPath}/item">Items</a></th>
							<td><a href="${pageContext.request.contextPath}/itemAdd"
								class="addlink">Add</a></td>
							<td><a href="${pageContext.request.contextPath}/item"
								class="changelink">Change</a></td>
						</tr>

						<tr class="model-role">
							<th scope="row"><a
								href="${pageContext.request.contextPath}/role">Roles</a></th>
							<td><a href="${pageContext.request.contextPath}/roleAdd"
								class="addlink">Add</a></td>
							<td><a href="${pageContext.request.contextPath}/role"
								class="changelink">Change</a></td>
						</tr>

					</table>
				</div>
			</div>
		</div>
		<!-- End Content -->

	</div>
	<!--  End Container -->
</body>
</html>