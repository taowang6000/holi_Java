<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User Account Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/forms.css" />
</head>
<body class=" app-online_shopping model-checkout_order change-form">

	<!-- Container -->
	<div id="container">
		<!-- Header -->
		<div id="header">
			<jsp:include page="../_header.jsp"></jsp:include>
		</div>
		<!-- END Header -->

		<div class="breadcrumbs">
			<a href="${pageContext.request.contextPath}/home">Home</a> &rsaquo; Online_Shopping &rsaquo; <a
				href="${pageContext.request.contextPath}/userAccount">User
				Accounts</a> &rsaquo; Add User Account
		</div>
		<!-- Content -->
		<div id="content" class="colM">
			<h1>Add User Account</h1>
			<div id="content-main">
				<p style="color: red;">${errorString}</p>
				<form action="" method="post" id="user_account_form" novalidate>
					<div>
						<fieldset class="module aligned ">
							<div class="form-row field-contact-number">
								<div>
									<label for="id_contact_no">Contact Number:</label> <input type="text"
										name="contact_no" class="vTextField" maxlength="255"
										id="id_contact_no" />
								</div>
							</div>
							<div class="form-row field-email">
								<div>
									<label for="id_email">Email:</label> <input type="text"
										name="email" class="vTextField" maxlength="255"
										id="id_email" />
								</div>
							</div>
							<div class="form-row field-password">
								<div>
									<label for="id_password">Password:</label> <input type="password"
										name="password" class="required" maxlength="255"
										id="id_password" />
								</div>
							</div>
							<div class="form-row field-user_name">
								<div>
									<label for="id_user_name">User Name:</label> <input type="text"
										name="user_name" class="required" maxlength="255"
										id="id_user_name" />
								</div>
							</div>	

						</fieldset>


						<div class="submit-row">
							<input type="submit" value="Save" class="default" name="_save" />
							<input type="submit" value="Save and add another"
								name="_addanother" /> <input type="submit"
								value="Save and continue editing" name="_continue" />
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
