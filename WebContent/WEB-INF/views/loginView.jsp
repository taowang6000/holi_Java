<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body class="login">
	<!--  Container -->
	<div id="container">
		<div id="header">
			<div id="branding">
				<h1 id="site-name">Holidays Administration</h1>
			</div>
		</div>
		<!--  content  -->
		<p style="color: red;">${errorString}</p>
		<div id="content" class="colM">
			<div id="content_main">
				<form action="${pageContext.request.contextPath}/login" method="post"
					id="login-form">
					<div class="form-row">

						<label class="required" for="id_username">Username:</label> <input
							type="text" name="username" autofocus maxlength="254" required
							id="id_username" />
					</div>
					<div class="form-row">

						<label class="required" for="id_password">Password:</label> <input
							type="password" name="password" required id="id_password" />
					</div>


					<div class="submit-row">
						<label>&nbsp;</label><input type="submit" value="Log in" />
					</div>
				</form>

			</div>
			<br class="clear" />
		</div>
		<!-- End Content -->

	</div>
	<!--  End Container -->
</body>
</html>