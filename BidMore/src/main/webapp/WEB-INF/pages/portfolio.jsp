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


	<div class="message-container">

		<!-- Display error message if available -->
		<c:if test="${not empty error}">
			<div class="message-box error-container">
				<span class="alert-icon">!</span>
				<p class="error-text">${error}</p>
				<button class="close-btn"
					onclick="this.parentElement.style.display='none'">&times;</button>
			</div>
		</c:if>

		<!-- Display success message if available -->
		<c:if test="${not empty success}">
			<div class=" message-box success-container">
				<span class="success-icon">✓</span>
				<p class="success-text">${success}</p>
				<button class="close-btn"
					onclick="this.parentElement.style.display='none'">&times;</button>
			</div>

		</c:if>

	</div>

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

				<div class="profile-greeting">Hi,${user.firstName}</div>
				<div class="profile-actions hidden">
					<label for="image-upload" class="btn upload-btn"> <i
						class="fas fa-camera"></i> Upload New Photo
					</label> <input type="file" id="image-upload" name="image"
						style="display: none;">
				</div>
			</div>


			<div class="profile-card">
				<form id="profile-form"
					action="${pageContext.request.contextPath}/portfolio" method="post"
					enctype="multipart/form-data">

					<div class="form-sections">
						<section class="form-section">
							<h3>
								<i class="fas fa-user-circle"></i> Personal Information
							</h3>
							<div class="form-grid">
								<div class="form-group">
									<label for="firstName">First Name</label> <input type="text"
										id="firstName" name="firstName" value="${user.firstName}"
										disabled>
								</div>
								<div class="form-group">
									<label for="lastName">Last Name</label> <input type="text"
										id="lastName" name="lastName" value=${user.lastName } disabled>
								</div>
								<div class="form-group">
									<label for="userName">Username</label> <input type="text"
										id="userName" name="userName" value="${user.userName}"
										disabled>
									<p id="username-update-message"
										class="username-update-notice hidden">Username cannot be
										updated.</p>
								</div>
								<div class="form-group">
									<label for="birthday">Birthday</label>
									<c:set var="dateStr" value="${user.birthDate.toString()}" />
									<input type="date" id="birthday" name="birthday"
										value="${dateStr}" disabled>
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
										id="email" name="email" value="${user.email}" disabled>
								</div>
								<div class="form-group">
									<label for="phone">Phone Number</label> <input type="tel"
										id="phone" name="phone" placeholder="N/A"
										value="${user.phone}" disabled>
								</div>
							</div>
						</section>

						<section class="form-section" id="password-section">
							<h3>
								<i class="fas fa-lock"></i> Security
							</h3>
							<%-- Change Password button is always visible, but fields are toggled --%>
							<a href="${pageContext.request.contextPath}/password"
								class="btn secondary-btn" id="change-password-btn"> <i
								class="fas fa-key"></i> Change Password
							</a>

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

	<%-- JavaScrpit got toggling edit profile functionality --%>
	<script>
	document.addEventListener('DOMContentLoaded', function() {
	    // Get references to elements
	    const editToggleBtn = document.getElementById('edit-toggle');
	    const profileForm = document.getElementById('profile-form');
	    const formActions = document.querySelector('.form-actions');
	    const profileActions = document.querySelector('.profile-actions');
	    const cancelBtn = document.getElementById('cancel-btn');
	    const updateMessage = document.getElementById('username-update-message');

	    // Function to toggle edit mode
	    function toggleEditMode() {
	        // Toggle button text
	        if (editToggleBtn.innerHTML.includes('Edit Profile')) {
	            editToggleBtn.innerHTML = '<i class="fas fa-times"></i> Cancel Editing';
	            // Show form actions and profile image upload option
	            formActions.classList.remove('hidden');
	            profileActions.classList.remove('hidden');
	            updateMessage.classList.remove('hidden');
	            
	            // Enable all form fields except password fields
	            const inputs = profileForm.querySelectorAll('input:not(#userName)');
	            inputs.forEach(input => {
	                input.disabled = false;
	            });
	        } else {
	            editToggleBtn.innerHTML = '<i class="fas fa-pencil-alt"></i> Edit Profile';
	            // Hide form actions and profile image upload option
	            formActions.classList.add('hidden');
	            profileActions.classList.add('hidden');
	            passwordFields.classList.add('hidden');
	            
	            // Disable all form fields
	            const inputs = profileForm.querySelectorAll('input');
	            inputs.forEach(input => {
	                input.disabled = true;
	            });
	        }
	    }
	    
	    // Add click event for edit toggle button
	    editToggleBtn.addEventListener('click', toggleEditMode);
	    
	    // Add click event for cancel button
	    cancelBtn.addEventListener('click', function() {
	        // Reset form to original values
	        profileForm.reset();
	        // Switch back to view mode
	        toggleEditMode();
	    });
	    
	    
	});
	</script>

</body>
</html>