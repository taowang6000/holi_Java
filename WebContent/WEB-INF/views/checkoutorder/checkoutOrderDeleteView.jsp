<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Checkout Order Delete Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/base.css" />
</head>
<body class=" app-auth model-user delete-confirmation">

	<!-- Container -->
	<div id="container">
		<!-- Header -->
		<div id="header">
			<jsp:include page="../_header.jsp"></jsp:include>
		</div>
		<!-- END Header -->

		<div class="breadcrumbs">
			<a href="${pageContext.request.contextPath}/home">Home</a> &rsaquo; Online Shopping &rsaquo; <a
				href="${pageContext.request.contextPath}/checkoutOrder">Checkout
				Orders</a> &rsaquo; <a
				href="${pageContext.request.contextPath}/checkoutOrderEdit?id=${order.orderId}">${order.dateTime}</a>
			&rsaquo; Delete
		</div>
		<!-- Content -->
		<div id="content" class="colM">
			<h1>Are you sure?</h1>
			<p>Are you sure you want to delete the Checkout Order
				"${order.dateTime}"? All of the following related items will be
				deleted:</p>
			<h2>Summary</h2>
			<ul>
				<c:forEach items="${order.items}" var="item">
					<li>"${item.country} ${item.city}"</li>
				</c:forEach>
			</ul>

			<form method="post">
				<input type="hidden" name="orderId_todelete" value="${order.orderId}" />
				<div>
					<input type="hidden" name="post" value="yes" /> <input
						type="submit" value="Yes, I'm sure" /> <a
						href="${pageContext.request.contextPath}/checkoutOrder"
						class="button cancel-link">No, take me back</a>
				</div>
			</form>
			<br class="clear" />
		</div>
		<!-- END Content -->
	</div>
	<!-- END Container -->
</body>
</html>