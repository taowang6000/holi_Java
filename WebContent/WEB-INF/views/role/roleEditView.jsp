<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Role Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/forms.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/dashboard.css" />
</head>
<body class=" app-online_shopping model-role change-form">

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
				href="${pageContext.request.contextPath}/role">Roles </a> &rsaquo;
			Edit Role
		</div>
		<!-- Content -->
		<div id="content" class="colM">
			<h1>Edit Role</h1>
			<ul class="object-tools">
				<li><a
					href="${pageContext.request.contextPath}/userAccountRoleAdd?id=${role.roleId}"
					class="addlink">Add Roles to Accounts</a></li>
			</ul>
			<div id="content-main">
				<p style="color: red;">${errorString}</p>
				<form action="" method="post" id="checkout_order_form" novalidate>
					<input type="hidden" name="role_id" value="${role.roleId}" />

					<div>
						<fieldset class="module aligned ">
							<div class="form-row field-contact-number">
								<div>
									<label for="id_name">Name:</label> <input type="text"
										name="name" value="${role.name}" class="vTextField"
										maxlength="255" id="id_name" />
								</div>
							</div>

						</fieldset>
						<div class=" dashboard">
							<div class="app-auth module">
								<table>
									<caption>
										<span class="section"> Following User Accounts have
											this Role: </span>
									</caption>
									<c:forEach items="${accountList}" var="account">
										<tr class="model-user">
											<th scope="row">${account.userName}</th>
											<td><a
												href="${pageContext.request.contextPath}/userAccountRoleDelete?id_role=${role.roleId}&id_account=${account.userId}"
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
									href="${pageContext.request.contextPath}/roleDelete?id=${role.roleId}"
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