<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/registration.css" />

</head>
<body>
	<div class="main-container">
		<div class="image-container">
			<img
				src="${pageContext.request.contextPath}/resources/images/system/logo.png"
				alt="bidmore logo"
				onmouseover="this.src='${pageContext.request.contextPath}/resources/images/system/altlogo.png'"
				onmouseout="this.src='${pageContext.request.contextPath}/resources/images/system/logo.png'">
		</div>
		<div class="form-container">
			<h1 class="header">Sign Up</h1>

			<div class="message-container">

				<!-- Display error message if available -->
				<c:if test="${not empty error}">
					<div class="error-container">
						<span class="alert-icon">!</span>
						<p class="error-text">${error}</p>
					</div>
				</c:if>

				<!-- Display success message if available -->
				<c:if test="${not empty success}">
					<div class="success-container">
						<span class="success-icon">✓</span>
						<p class="success-text">${success}</p>
					</div>

				</c:if>
			</div>
			<form action="${pageContext.request.contextPath}/registration"
				method="post" enctype="multipart/form-data">
				<div class="form-row">
					<div class="form-group">
						<label for="firstName">First Name:</label> <input type="text"
							id="firstName" name="firstName" required>
					</div>
					<div class="form-group">
						<label for="lastName">Last Name:</label> <input type="text"
							id="lastName" name="lastName" required>
					</div>
				</div>

				<div class="form-row">
					<div class="form-group">
						<label for="username">Username:</label> <input type="text"
							id="userName" name="userName" required>
					</div>
					<div class="form-group">
						<label for="birthday">Birthday:</label> <input type="date"
							id="birthday" name="birthday" placeholder="mm/dd/yyyy" required>
					</div>
				</div>

				<div class="form-row">

					<div class="form-group">
						<label for="email">Email:</label> <input type="email" id="email"
							name="email" required>
					</div>
					<div class="form-group">
						<label for="phone">Phone Number:</label> <input type="tel"
							id="phone" name="phone" required>
					</div>
				</div>

				<div class="form-row">
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							id="password" name="password" required>
					</div>
					<div class="form-group">
						<label for="retypePassword">Retype Password:</label> <input
							type="password" id="retypePassword" name="retypePassword"
							required>
					</div>
				</div>
				
				<div class="form-row">
					<div class="form-group">
						<label for="image">Profile Picture:</label> <input type="file"
						id="image" name="image">
					</div>
					
				</div>
				
				
				<div class="button-wrapper">

					<button type="submit" class="submit-btn">Submit</button>
				</div>
			</form>
			<div class="login-link">
				<p>
					Already a member? <a
						href="${pageContext.request.contextPath}/login"
						class="login-link-anchor"> Sign in here </a>
				</p>
			</div>
		</div>
	</div>
</body>
</html> 

