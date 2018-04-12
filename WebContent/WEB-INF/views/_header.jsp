<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="branding">
	<h1>Holiday Administration</h1>
</div>

<div id="user-tools">
	<!-- User store in session with attribute: loginedUser -->

	Welcome <strong>${loginedUser.userName}</strong> . <a
		href="${pageContext.request.contextPath}/home">HOME</a> /<a
		href="${pageContext.request.contextPath}/changePassword">CHANGE
		PASSWORD</a> / <a href="${pageContext.request.contextPath}/logOut">LOG
		OUT</a>
</div>


