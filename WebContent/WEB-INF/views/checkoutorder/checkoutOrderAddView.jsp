<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Checkout Order Page</title>
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
				href="${pageContext.request.contextPath}/checkoutOrder">Checkout
				Orders</a> &rsaquo; Add Checkout Order
		</div>
		<!-- Content -->
		<div id="content" class="colM">
			<h1>Add Checkout Order</h1>
			<div id="content-main">
				<p style="color: red;">${errorString}</p>
				<form action="" method="post" id="checkout_order_form" novalidate>
					<div>
						<fieldset class="module aligned ">
							<div class="form-row field-date_time">
								<div>
									<label for="id_date_time">Date time:</label> <input type="text"
										name="date_time" class="vTextField" maxlength="255"
										id="id_date_time" />
								</div>
							</div>
							<div class="form-row field-account">
								<div>
									<label for="id_account">Account:</label>
									<div class="related-widget-wrapper">
										<select name="id_account" id="id_account">
											<option value="" selected>---------</option>
											<c:forEach items="${accountList}" var="account_shown">
												<option value="${account_shown.userId}">${account_shown.userName}</option>
											</c:forEach>
										</select> 
									</div>
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
