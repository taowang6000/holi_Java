<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>AdminUser Delete Page</title>
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
			<a href="${pageContext.request.contextPath}/home">Home</a> &rsaquo; Authentication and Authorization
			&rsaquo; <a href="${pageContext.request.contextPath}/adminUser">Users</a>
			&rsaquo; <a
				href="${pageContext.request.contextPath}/adminUserEdit?username=${name}">${name}</a>
			&rsaquo; Delete
		</div>
		<!-- Content -->
		<div id="content" class="colM">

			<h1>Are you sure?</h1>


			<p>Are you sure you want to delete the user "${name}"?</p>

			<form method="post">
			<input type="hidden" name="name_todelete" value="${name}" />
				<div>
					<input type="hidden" name="post" value="yes" /> <input
						type="submit" value="Yes, I'm sure" /> <a
						href="${pageContext.request.contextPath}/adminUser"
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