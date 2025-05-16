<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BidMore Admin Dashboard</title>
<!-- External CSS -->
<link rel="stylesheet" href="${contextPath}/css/dashboard.css">
<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<div class="dashboard-container">
		<!-- Header Section -->
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
					<li class="active"><a href="${pageContext.request.contextPath}/dashboard"> <i class="fas fa-chart-line"></i>
							Dashboard
					</a></li>
					<li><a href="${pageContext.request.contextPath}/user"> <i class="fas fa-users"></i> Users
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
		<div class="main-content">
			<div class="page-header">
				<div class="header-left">
					<h1>Dashboard Overview</h1>
					<p class="subtitle">Monitor your platform's key metrics and
						recent activity</p>
				</div>

			</div>

			<!-- Stats Cards Section -->
			<div class="dashboard-stats">
				<!-- Stat Card: Total Users -->
				<div class="stat-card">
					<div class="stat-card-inner">
						<div class="stat-icon users">
							<i class="fas fa-users"></i>
						</div>
						<div class="stat-info">
							<h3>Total Users</h3>
							<div class="stat-number">
								<c:if test="${empty totalUser }">
									<h1>0</h1>
								</c:if>
								<c:if test="${not empty totalUser }">
									<h1>${totalUser}</h1>
								</c:if>
							</div>
							<div class="trend up">
								<i class="fas fa-arrow-up"></i> 12% this month
							</div>
						</div>
					</div>
				</div>

				<!-- Stat Card: Items Listed -->
				<div class="stat-card">
					<div class="stat-card-inner">
						<div class="stat-icon items">
							<i class="fas fa-box-open"></i>
						</div>
						<div class="stat-info">
							<h3>Items Listed</h3>
							<div class="stat-number"><c:if test="${empty totalItem }">
									<h1>0</h1>
								</c:if>
								<c:if test="${not empty totalItem }">
									<h1>${totalItem}</h1>
								</c:if></div>
							<div class="trend up">
								<i class="fas fa-arrow-up"></i> 8% this month
							</div>
						</div>
					</div>
				</div>

				<!-- Stat Card: Live Auctions -->
				<div class="stat-card">
					<div class="stat-card-inner">
						<div class="stat-icon live">
							<i class="fas fa-gavel"></i>
						</div>
						<div class="stat-info">
							<h3>Live Auctions</h3>
							<div class="stat-number"><c:if test="${empty totalAuction }">
									<h1>0</h1>
								</c:if>
								<c:if test="${not empty totalAuction }">
									<h1>${totalAuction}</h1>
								</c:if></div>
							<div class="trend up">
								<i class="fas fa-arrow-up"></i> 5% this week
							</div>
						</div>
					</div>
				</div>

				<!-- Stat Card: Total Bids -->
				<div class="stat-card">
					<div class="stat-card-inner">
						<div class="stat-icon bids">
							<i class="fas fa-money-bill-wave"></i>
						</div>
						<div class="stat-info">
							<h3>Total Bids</h3>
							<div class="stat-number">
							<c:if test="${empty totalBid }">
									<h1>0</h1>
								</c:if>
								<c:if test="${not empty totalBid }">
									<h1>${totalUser}</h1>
								</c:if></div>
							<div class="trend up">
								<i class="fas fa-arrow-up"></i> 15% this month
							</div>
						</div>
					</div>
				</div>

				<!-- Stat Card: Successful Auctions -->
				<div class="stat-card">
					<div class="stat-card-inner">
						<div class="stat-icon completion">
							<i class="fas fa-check-circle"></i>
						</div>
						<div class="stat-info">
							<h3>Successful Auctions</h3>
							<div class="stat-number">92%</div>
							<div class="trend up">
								<i class="fas fa-arrow-up"></i> 3% this month
							</div>
						</div>
					</div>
				</div>

				<!-- Stat Card: User Satisfaction -->
				<div class="stat-card">
					<div class="stat-card-inner">
						<div class="stat-icon feedback">
							<i class="fas fa-star"></i>
						</div>
						<div class="stat-info">
							<h3>User Satisfaction</h3>
							<div class="stat-number">4.8/5</div>
							<div class="trend up">
								<i class="fas fa-arrow-up"></i> 0.2 this month
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Dashboard Content Sections -->
			<div class="dashboard-sections">
				<!-- Recent Activity Section -->
				<div class="dashboard-section">
					<div class="section-header">
						<h2>
							<i class="fas fa-history"></i> Recent Activity
						</h2>
						<a href="${contextPath}/admin/activities" class="view-all">View
							All <i class="fas fa-arrow-right"></i>
						</a>
					</div>
					<div class="activity-list">
						<div class="activity-item">
							<div class="activity-icon new-user">
								<i class="fas fa-user-plus"></i>
							</div>
							<div class="activity-details">
								<p>
									<strong>New User Registration</strong>:${latestUser} joined the
									platform
								</p>
								<span class="activity-time">Latest</span>
							</div>
							<div class="activity-action">
								<button class="btn-view" title="View Details">
									<i class="fas fa-eye"></i>
								</button>
							</div>
						</div>
						<div class="activity-item">
							<div class="activity-icon new-auction">
								<i class="fas fa-gavel"></i>
							</div>
							<div class="activity-details">
								<p>
									<strong>New Auction Created</strong>: ${latestAuction}
								</p>
								<span class="activity-time">Recent</span>
							</div>
							<div class="activity-action">
								<button class="btn-view" title="View Details">
									<i class="fas fa-eye"></i>
								</button>
							</div>
						</div>
						<div class="activity-item">
							<div class="activity-icon bid">
								<i class="fas fa-money-bill-wave"></i>
							</div>
							<div class="activity-details">
								<p>
									<strong>Latest Bid </strong>: ${latestBid} on Our Listings
								</p>
								<span class="activity-time">Recent</span>
							</div>
							<div class="activity-action">
								<button class="btn-view" title="View Details">
									<i class="fas fa-eye"></i>
								</button>
							</div>
						</div>
					</div>

					<div class="section-footer">
						<button class="btn-load-more">Load More Activities</button>
					</div>
				</div>

				<!-- Quick Actions Section -->
				<div class="dashboard-section quick-actions">
					<div class="section-header">
						<h2>
							<i class="fas fa-bolt"></i> Quick Actions
						</h2>
					</div>
					<div class="quick-actions-grid">
						<a href="#" class="quick-action-card"> <i
							class="fas fa-user-plus"></i> <span>Review Metrics </span>
						</a> <a href="#" class="quick-action-card"> <i
							class="fas fa-plus-circle"></i> <span> See Auctions</span>
						</a> <a href="#" class="quick-action-card"> <i
							class="fas fa-chart-bar"></i> <span>Generate Report</span>
						</a> <a href="#" class="quick-action-card"> <i
							class="fas fa-exclamation-triangle"></i> <span>Review </span>
						</a>
					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>