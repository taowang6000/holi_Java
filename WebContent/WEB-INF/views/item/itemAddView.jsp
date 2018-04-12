<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Item Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/forms.css" />
</head>
<body class=" app-online_shopping model-item change-form">

	<!-- Container -->
	<div id="container">
		<!-- Header -->
		<div id="header">
			<jsp:include page="../_header.jsp"></jsp:include>
		</div>
		<!-- END Header -->

		<div class="breadcrumbs">
			<a href="${pageContext.request.contextPath}/home">Home</a> &rsaquo; Online_Shopping &rsaquo; <a
				href="${pageContext.request.contextPath}/item">Items</a> &rsaquo;
			Add item
		</div>
		<!-- Content -->
		<div id="content" class="colM">
			<h1>Add item</h1>
			<div id="content-main">
				<p style="color: red;">${errorString}</p>
				<form  action="" method="post" id="item_form" novalidate>
				<input type="hidden" name="entrance" value="${entrance}" />
					<div>
						<fieldset class="module aligned ">
							<div class="form-row field-city">
								<div>
									<label for="id_city">City:</label> <input type="text"
										name="city" class="vTextField" maxlength="255" id="id_city" />
								</div>
							</div>
							<div class="form-row field-country">
								<div>
									<label for="id_country">Country:</label> <input type="text"
										name="country" class="vTextField" maxlength="255"
										id="id_country" />
								</div>
							</div>
							<div class="form-row field-option_value">
								<div>
									<label for="id_option_value">Option value:</label> <input
										type="number" name="option_value" class="vIntegerField"
										id="id_option_value" />
								</div>
							</div>
							<div class="form-row field-price">
								<div>
									<label for="id_price">Price:</label> <input type="number"
										name="price" step="any" id="id_price" />
								</div>
							</div>
							<div class="form-row field-state">
								<div>
									<label for="id_state">State:</label> <input type="text"
										name="state" class="vTextField" maxlength="255" id="id_state" />
								</div>
							</div>
							<div class="form-row field-year">
								<div>
									<label for="id_year">Year:</label> <input type="text"
										name="year" class="vTextField" maxlength="255" id="id_year" />
								</div>
							</div>
							<div class="form-row field-order">
								<div>
									<label for="id_order">Order:</label>
									<div class="related-widget-wrapper">
										<select name="id_order" id="id_order">
										<option value="" >---------</option>
											<c:forEach items="${orderList}" var="order_shown">
												<option value="${order_shown.orderId}" ${selected_orderId == order_shown.orderId ? 'selected' : '' }>Order${order_shown.orderId}:
													${order_shown.dateTime}</option>
											</c:forEach>
										</select>
									</div>
								</div>

							</div>
						</fieldset>

						<div class="submit-row">
							<input type="submit" value="Save" class="default" name="_save" />
							<input type="submit" value="Save and add another"
								name="_addanother" /> 
							<input type="submit"
								value="Save and continue editing" name="_continue" />
						</div>
					</div>
				</form>
			</div>
			<br class="clear" />
		</div>
		<!-- END Content -->

		<div id="footer"></div>
	</div>
	<!-- END Container -->

</body>
</html>
