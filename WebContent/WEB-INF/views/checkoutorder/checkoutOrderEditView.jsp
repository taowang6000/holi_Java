<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Checkout Order Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/forms.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/dashboard.css" />
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
				Orders</a> &rsaquo; Edit Checkout Order
		</div>
		<!-- Content -->
		<div id="content" class="colM">
			<h1>Edit Checkout Order</h1>
			<ul class="object-tools">
				<li><a
					href="${pageContext.request.contextPath}/itemAdd?id=${order.orderId}"
					class="addlink">Add New Item</a></li>
			</ul>
			<div id="content-main">
				<p style="color: red;">${errorString}</p>
				<form action="" method="post" id="checkout_order_form" novalidate>
					<input type="hidden" name="order_id" value="${order.orderId}" />

					<div>
						<fieldset class="module aligned ">
							<div class="form-row field-date_time">
								<div>
									<label for="id_date_time">Date time:</label> <input type="text"
										name="date_time" value="${order.dateTime}" class="vTextField"
										maxlength="255" id="id_date_time" />
								</div>
							</div>
							<div class="form-row field-total">
								<div>
									<label for="id_total">Total:</label> <input type="number"
										name="total" value="${order.total}" step="any" id="id_total" />
								</div>
								<div class="help">This vlaue should not be changed manually. </div>
							</div>
							<div class="form-row field-account">
								<div>
									<label for="id_account">Account:</label>
									<div class="related-widget-wrapper">
										<select name="id_account" id="id_account">
											<option value="" selected>---------</option>
											<c:forEach items="${accountList}" var="account_shown">
												<option value="${account_shown.userId}"
													${theAccount.userId == account_shown.userId ? 'selected' : '' }>${account_shown.userName}
												</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</fieldset>

						<div class=" dashboard">
							<div class="app-auth module">
								<table>

									<caption>
										<span class="section"> Items of the Checkout Order </span>
									</caption>
									<c:forEach items="${itemList}" var="item">
										<tr class="model-user">
											<th scope="row"><a
												href="${pageContext.request.contextPath}/itemEdit?id=${item.itemid}">${item.country}
													${item.city}</a></th>
											<td><a
												href="${pageContext.request.contextPath}/itemEdit?id=${item.itemid}"
												class="changelink">Edit</a>
											<td><a
												href="${pageContext.request.contextPath}/itemDelete?id=${item.itemid}"
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
									href="${pageContext.request.contextPath}/checkoutOrderDelete?id=${order.orderId}"
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
