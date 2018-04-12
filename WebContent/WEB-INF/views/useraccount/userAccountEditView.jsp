<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit User Account Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/forms.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/dashboard.css" />
</head>
<body class=" app-online_shopping model-user-account change-form">

	<!-- Container -->
	<div id="container">
		<!-- Header -->
		<div id="header">
			<jsp:include page="../_header.jsp"></jsp:include>
		</div>
		<!-- END Header -->

		<div class="breadcrumbs">
			<a href="${pageContext.request.contextPath}/home">Home</a> &rsaquo;
			Online_Shopping &rsaquo; <a
				href="${pageContext.request.contextPath}/userAccount">User
				Accounts </a> &rsaquo; Edit User Account
		</div>
		<!-- Content -->
		<div id="content" class="colM">
			<h1>Edit User Account</h1>
			<ul class="object-tools">
				<li><a
					href="${pageContext.request.contextPath}/checkoutOrderAdd?id=${account.userId}"
					class="addlink">Add New CheckoutOrder</a></li>
			</ul>
			<div id="content-main">
				<p style="color: red;">${errorString}</p>
				<form action="" method="post" id="checkout_order_form" novalidate>
					<input type="hidden" name="account_id" value="${account.userId}" />

					<div>
						<fieldset class="module aligned ">
							<div class="form-row field-contact-number">
								<div>
									<label for="id_contact_no">Contact Number:</label> <input
										type="text" name="contact_no" value="${account.contactNo}"
										class="vTextField" maxlength="255" id="id_contact_no" />
								</div>
							</div>
							<div class="form-row field-email">
								<div>
									<label for="id_email">Email:</label> <input type="text"
										name="email" value="${account.email}" class="vTextField"
										maxlength="255" id="id_email" />
								</div>
							</div>
							<div class="form-row field-password">
								<div>
									<label for="id_password">Password:</label> <input
										type="password" name="password" value="${account.password}"
										class="required" maxlength="255" id="id_password" />
								</div>
							</div>
							<div class="form-row field-user_name">
								<div>
									<label for="id_user_name">User Name:</label> <input type="text"
										name="user_name" value="${account.userName}" class="required"
										maxlength="255" id="id_user_name" />
								</div>
							</div>
							<div class="form-row field-role">
								<div>
									<label for="id_role">Role of the Account:</label>
									<c:forEach items="${roleList}" var="role">
										${role.name} &nbsp; 
									</c:forEach>
								</div>
							</div>

						</fieldset>
						<div class=" dashboard">
							<div class="app-auth module">
								<table>

									<caption>
										<span class="section"> Orders of the UserAccount </span>
									</caption>
									<c:forEach items="${orderList}" var="order">
										<tr class="model-user">
											<th scope="row"><a
												href="${pageContext.request.contextPath}/checkoutOrderEdit?id=${order.orderId}">${order.dateTime}
											</a></th>
											<td><a
												href="${pageContext.request.contextPath}/checkoutOrderEdit?id=${order.orderId}"
												class="changelink">Edit</a>
											<td><a
												href="${pageContext.request.contextPath}/checkoutOrderDelete?id=${order.orderId}"
												class="deletelink">Delete</a>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>



						<div class="submit-row">
							<input type="submit" value="Save" class="default" name="_save" />
							<p class="deletelink-box">
								<a
									href="${pageContext.request.contextPath}/userAccountDelete?id=${account.userId}"
									class="deletelink">Delete</a>
							</p>
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