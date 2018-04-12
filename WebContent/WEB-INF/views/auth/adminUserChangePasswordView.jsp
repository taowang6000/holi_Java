<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Admin User Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/forms.css" />
</head>
<body class=" app-auth model-user change-form">
	<!-- Container -->
	<div id="container">
		<!-- Header -->
		<div id="header">
			<jsp:include page="../_header.jsp"></jsp:include>
		</div>
		<!-- END Header -->

		<div class="breadcrumbs">
			<a href="${pageContext.request.contextPath}/home">Home</a> &rsaquo;
			Authentication and Authorization &rsaquo; Change Password
		</div>
		<!-- Content -->
		<div id="content" class="colM">
			<h1>Change Password</h1>
			<div id="content-main">
				<form action="" method="post" id="user_form" novalidate>
					<div>
						<fieldset class="module aligned ">
							<div class="form-row field-username">
								<div>
									<label class="required" for="id_username">Username:</label> <input
										type="text" name="name" class="vTextField"
										maxlength="150" required id="id_username" />
								</div>
							</div>
							<div class="form-row field-password1">
								<div>
									<label class="required" for="id_password">Old Password:</label> <input
										type="password" name="password" required id="id_password" />
								</div>
							</div>
							<div class="form-row field-password1">
								<div>
									<label class="required" for="id_password1">New Password:</label> <input
										type="password" name="password1" required id="id_password1" />
								</div>
							</div>

							<div class="form-row field-password2">
								<div>
									<label class="required" for="id_password2">Password
										confirmation:</label> <input type="password" name="password2" required
										id="id_password2" />
									<div class="help">Enter the same password as before, for
										verification.</div>
								</div>
							</div>
						</fieldset>

						<div class="submit-row">
							<input type="submit" value="Save" class="default" name="_save" />
						</div>
					</div>
				</form>
			</div>
			<br class="clear" />
		</div>
		<!-- END Content -->
	</div>
	<!-- END Container -->

</body>
</html>