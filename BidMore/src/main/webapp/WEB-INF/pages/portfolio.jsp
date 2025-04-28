<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Profile | BidMore</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/portfolio.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
	<%-- Header --%>
	<jsp:include page="header.jsp" />

	<div class="main-container">
		<div class="content-container">
			<header class="page-header">
				<h1>My Profile</h1>
				<%-- Edit button is visible in view mode --%>
				<button id="edit-toggle" class="btn primary-btn">
					<i class="fas fa-pencil-alt"></i> Edit Profile
				</button>
			</header>


			<div class="profile-image-container">
				<div class="profile-image">
					<c:choose>
						<c:when test="${not empty user.imageUrl}">
							<img src="${pageContext.request.contextPath}${user.imageUrl}"
								alt="${user.firstName}'s Profile Image">
						</c:when>
						<c:otherwise>
							<img
								src="${pageContext.request.contextPath}/resources/images/users/defaultdp.png"
								alt="Default Profile Image">
						</c:otherwise>
					</c:choose>
				</div>

				<div class="profile-greeting">Hi, Nirdesh</div>
				<div class="profile-actions hidden">
					<label for="image-upload" class="btn upload-btn"> <i
						class="fas fa-camera"></i> Upload New Photo
					</label> <input type="file" id="image-upload" name="image" accept="image/*"
						style="display: none;">
				</div>
			</div>


			<div class="profile-card">
				<form id="profile-form"
					action="${pageContext.request.contextPath}/updateProfile"
					method="post">

					<div class="form-sections">
						<section class="form-section">
							<h3>
								<i class="fas fa-user-circle"></i> Personal Information
							</h3>
							<div class="form-grid">
								<div class="form-group">
									<label for="firstName">First Name</label> <input type="text"
										id="firstName" name="firstName" value="Nirdesh"
										disabled>
								</div>
								<div class="form-group">
									<label for="lastName">Last Name</label> <input type="text"
										id="lastName" name="lastName" value="Subedi"
										disabled>
								</div>
								<div class="form-group">
									<label for="userName">Username</label> <input type="text"
										id="userName" name="userName" value="nirdeshsub123"
										disabled>
								</div>
								<div class="form-group">
									<label for="birthday">Birthday</label>
									<fmt:formatDate value="${user.birthdate}" pattern="yyyy-MM-dd"
										var="formattedBirthdate" />
									<input type="date" id="birthday" name="birthday"
										value="2004-12-11" disabled>
								</div>
							</div>
						</section>

						<section class="form-section">
							<h3>
								<i class="fas fa-envelope"></i> Contact Information
							</h3>
							<div class="form-grid">
								<div class="form-group">
									<label for="email">Email Address</label> <input type="email"
										id="email" name="email" value="subn@yahoo.com" disabled>
								</div>
								<div class="form-group">
									<label for="phone">Phone Number</label> <input type="tel"
										id="phone" name="phone" value="9811001101"
										placeholder="N/A" disabled>
								</div>
							</div>
						</section>

						<section class="form-section" id="password-section">
							<h3>
								<i class="fas fa-lock"></i> Security
							</h3>
							<%-- Password fields are hidden by default --%>
							<div class="form-grid hidden" id="password-fields">
								<div class="form-group full-width">
									<label for="current-password">Current Password</label> <input
										type="password" id="current-password" name="currentPassword"
										placeholder="Enter current password">
								</div>
								<div class="form-group">
									<label for="new-password">New Password</label> <input
										type="password" id="new-password" name="newPassword"
										placeholder="Minimum 8 characters">
								</div>
								<div class="form-group">
									<label for="confirm-password">Confirm New Password</label> <input
										type="password" id="confirm-password" name="confirmPassword"
										placeholder="Re-enter new password">
								</div>
							</div>
							<%-- Change Password button is always visible, but fields are toggled --%>
							<button type="button" id="change-password-btn"
								class="btn secondary-btn">
								<i class="fas fa-key"></i> Change Password
							</button>
						</section>
					</div>

					<%-- Action buttons are hidden in view mode --%>
					<div class="form-actions hidden">
						<button type="button" id="cancel-btn" class="btn cancel-btn">
							<i class="fas fa-times"></i> Cancel
						</button>
						<button type="submit" class="btn save-btn">
							<i class="fas fa-save"></i> Save Changes
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%-- Footer --%>
	<jsp:include page="footer.jsp" />

</body>
</html>