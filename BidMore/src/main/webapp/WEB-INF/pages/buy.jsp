<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Auction Marketplace - Buy Items</title>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/buy.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
	<%-- Header --%>
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="message-container">

			<!-- Display error message if available -->
			<c:if test="${not empty error}">
				<div class="message-box error-container" role="alert">
					<span class="icon">⚠️</span>
					<p class="message-text">${error}</p>
					<button class="close-btn"
						onclick="this.parentElement.style.display='none'">&times;</button>
				</div>
			</c:if>

			<!-- Display success message if available -->
			<c:if test="${not empty sessionScope.successMessage}">
				<div class="message-box success-container" role="alert">
					<span class="icon">✅</span>
					<p class="message-text">${sessionScope.successMessage}</p>
					<button class="close-btn"
						onclick="this.parentElement.style.display='none'">&times;</button>
				</div>
				<c:remove var="successMessage" scope="session" />
			</c:if>

		</div>

		<div class="filters">
			<button class="filter-button">
				<span>Filter</span>
			</button>

			<div class="dropdown-filter">
				<span>Condition</span> <span class="dropdown-icon">▼</span>
			</div>

			<div class="dropdown-filter">
				<span>Price</span> <span class="dropdown-icon">▼</span>
			</div>
		</div>

		<div class="product-listings">
			<c:forEach items="${auctions}" var="auction">
				<div class="product-row">
					<div class="product-image-container">
						<img
							src="${pageContext.request.contextPath}${auction.item.imagePath}"
							alt="${auction.item.itemName}" class="product-image">
						<button class="watch-button">♡</button>
					</div>

					<div class="product-info">
						<h3 class="product-name">${auction.item.itemName}</h3>
						<div class="product-meta">${auction.item.condition}·
							${auction.item.category}</div>

						<div class="product-price-container">
							<div class="price-info">
								<div class="current-price">
									$
									<fmt:formatNumber value="${auction.startPrice}"
										pattern="#,##0.00" />
								</div>
							</div>
						</div>

						<form action="${pageContext.request.contextPath}/buy"
							class="bid-form" method="post">
							<input type="hidden" name="auctionId"
								value="${auction.auctionId}">
							<c:set var="minBid" value="${auction.startPrice + 0.50}" />
							<input type="number" name="bidAmount" class="bid-input"
								placeholder="Enter your bid (min $${minBid})" min="${minBid}"
								step="0.50" required>
							<button type="submit" class="bid-button">Place Bid</button>
						</form>
					</div>
				</div>
			</c:forEach>

			<c:if test="${empty auctions}">
				<div class="no-items-message">
					<p>No auction items available at the moment. Please check back
						later!</p>
				</div>
			</c:if>
		</div>
	</div>
	<%-- Footer --%>
	<jsp:include page="footer.jsp" />
</body>
</html>