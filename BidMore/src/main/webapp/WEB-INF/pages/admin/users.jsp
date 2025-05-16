<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Management | BidMore Admin</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${contextPath}/css/user.css">
</head>
<body>
	<div class="dashboard-container">
		<!-- Header Navigation -->
		<header class="admin-header">
			<nav class="main-nav">
				<div class="logo">
					<a href="${contextPath}/dashboard"> <img
						src="${contextPath}/resources/images/system/logo.png"
						alt="BidMore"
						onmouseover="this.src='${contextPath}/resources/images/system/altlogo.png'"
						onmouseout="this.src='${contextPath}/resources/images/system/logo.png'" />
					</a>
				</div>
				<ul class="nav-links">
					<li ><a href="${pageContext.request.contextPath}/dashboard"> <i class="fas fa-chart-line"></i>
							Dashboard
					</a></li>
					<li class="active"><a href="${pageContext.request.contextPath}/user"> <i class="fas fa-users"></i> Users
					</a></li>
				</ul>
				<div class="header-actions">

					<form action="${pageContext.request.contextPath}/logout"
						method="post">
						<button type="submit" class="btn-logout">
							<i class="fas fa-sign-out-alt"></i> Logout
						</button>
					</form>
				</div>
			</nav>
		</header>

		<!-- Main Content -->
		<main class="main-content">
			<div class="content-header">
				<h1>User Management</h1>
			</div>

			<!-- User Table -->
			<div class="table-container">
				<table id="userTable" class="data-table">
					<thead>
						<tr>
							<th>ID <i class="fas fa-sort"></i></th>
							<th>Name <i class="fas fa-sort"></i></th>
							<%-- Header changed to Name --%>
							<th>Username <i class="fas fa-sort"></i></th>
							<th>Email <i class="fas fa-sort"></i></th>
							<th>Phone <i class="fas fa-sort"></i></th>
							<th>Birth Date <i class="fas fa-sort"></i></th>
							<th>Registration Date <i class="fas fa-sort"></i></th>
							<th>Status <i class="fas fa-sort"></i></th>

						</tr>
					</thead>
					<tbody>
						<%-- Check if the users list is not empty --%>
						<c:choose>
							<%-- Corrected items attribute to iterate over a list named 'users' --%>
							<c:when test="${not empty users}">
								<%-- Iterate over the list of users --%>
								<c:forEach var="user" items="${users}">
									<tr>
										<td>${user.userId}</td>
										<td>
											<div class="user-info">
												<%-- Display first letter of the first name for avatar --%>
												<div class="avatar-placeholder">
													<c:choose>
														<c:when test="${not empty user.firstName}">
                                                            ${user.firstName.substring(0, 1).toUpperCase()}
                                                        </c:when>
														<c:otherwise>
															<i class="fas fa-user"></i>
															<%-- Fallback icon --%>
														</c:otherwise>
													</c:choose>
												</div>
												<div class="user-details">
													<%-- Display combined first and last name --%>
													<span class="user-name">${user.firstName}
														${user.lastName}</span>
													<%-- Removed location as it's not in the new attribute list --%>
												</div>
											</div>
										</td>
										<td>${user.userName}</td>
										<td>${user.email}</td>
										<td>${user.phone}</td>
										<td><c:if test="${not empty user.birthDate}">
												${user.birthDate}
													
											</c:if></td>
										<td><c:if test="${not empty user.registrationDate}">
												${user.registrationDate}
													
											</c:if></td>
										<td>
											<%-- Dynamically set status badge class and text --%> <c:choose>
												<c:when test="${user.status == 'active'}">
													<span class="badge status-badge status-active">Active</span>
												</c:when>
												<c:when test="${user.status == 'inactive'}">
													<span class="badge status-badge status-inactive">Inactive</span>
												</c:when>
												<c:when test="${user.status == 'locked'}">
													<span class="badge status-badge status-locked">Locked</span>
												</c:when>
												<c:when test="${user.status == 'deleted'}">
													<span class="badge status-badge status-deleted">Deleted</span>
												</c:when>
												<c:otherwise>
													<span class="badge status-badge status-default">${user.status}</span>
												</c:otherwise>
											</c:choose>
										</td>
										<%-- Added Actions cell back --%>

									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<%-- Message if no users are found --%>
								<tr>
									<td colspan="9" class="text-center">No users found.</td>
									<%-- Colspan matches number of headers --%>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>


		</main>
	</div>

</body>
</html>