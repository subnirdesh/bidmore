<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body>
	<div class="login-wrapper">
		<div class="logo-container">
			<img
				src="${pageContext.request.contextPath}/resources/images/system/logo.png"
				alt="Logo" class="logo-img"
				onmouseover="this.src='${pageContext.request.contextPath}/resources/images/system/altlogo.png'"
				onmouseout="this.src='${pageContext.request.contextPath}/resources/images/system/logo.png'">
		</div>
		<div class="login-box">
			<h2>Login</h2>
			<div class="message-container">

				<!-- Displaying error message if available -->
				<c:if test="${not empty error}">
					<div class="error-container">
						<span class="alert-icon">!</span>
						<p class="error-text">${error}</p>
					</div>
				</c:if>
			</div>
			<form action="${pageContext.request.contextPath}/login" method="post">
				<div class="row">
					<div class="col">
						<label for="username">Username:</label> <input type="text"
							id="username" name="username">
					</div>
				</div>
				<div class="row">
					<div class="col">
						<label for="password">Password:</label> <input type="password"
							id="password" name="password">
					</div>
				</div>
				<div class="button-wrapper">
					<button type="submit" class="login-btn">Login</button>
				</div>
			</form>
			<div class="register-link">
				<p>
					New here? <a href="${pageContext.request.contextPath}/registration"
						class="register-link-anchor"> Create an account </a>
				</p>
			</div>


		</div>
	</div>
</body>
</html>