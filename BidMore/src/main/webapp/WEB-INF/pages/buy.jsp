<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
			<!-- 
                In the future, this section will be populated using JSTL:
                
                <%-- 
                <c:forEach items="${products}" var="product">
                    ... product listing content ...
                </c:forEach>
                --%>
            -->

			<!-- Sample Product 1 -->
			<div class="product-row">
				<div class="product-image-container">
					<img src="${pageContext.request.contextPath}/resources/images/items/nikeairmax.png"
						alt="Nike Air Max Shoes" class="product-image">
					<button class="watch-button">♡</button>
				</div>

				<div class="product-info">
					<h3 class="product-name">Nike Air Max Shoes</h3>
					<div class="product-meta">Pre-Owned · Nike</div>

					<div class="product-price-container">
						<div class="price-info">
							<div class="current-price">$120.50</div>
							<div class="shipping-info">$10.00 shipping</div>
						</div>
						<div class="bid-info">
							<div class="bid-count">15 bids</div>
							<div class="time-left warning">1h 25m</div>
						</div>
					</div>

					<form class="bid-form" action="placeBid.jsp" method="post">
						<input type="hidden" name="productId" value="1"> <input
							type="number" name="bidAmount" class="bid-input"
							placeholder="Enter your bid (min $125.00)" min="125.00"
							step="0.50" required>
						<button type="submit" class="bid-button">Place Bid</button>
					</form>
				</div>
			</div>

			<!-- Sample Product 2 -->
			<div class="product-row">
				<div class="product-image-container">
					<img src="${pageContext.request.contextPath}/resources/images/items/alchemist.jpg"
						alt="The Alchemist - Book" class="product-image">
					<button class="watch-button">♡</button>
				</div>

				<div class="product-info">
					<h3 class="product-name">The Alchemist - Book</h3>
					<div class="product-meta">Used - Good · Paulo Coelho</div>

					<div class="product-price-container">
						<div class="price-info">
							<div class="current-price">$10.00</div>
							<div class="shipping-info">$3.50 shipping</div>
						</div>
						<div class="bid-info">
							<div class="bid-count">5 bids</div>
							<div class="time-left">2d 8h</div>
						</div>
					</div>

					<form class="bid-form" action="placeBid.jsp" method="post">
						<input type="hidden" name="productId" value="2"> <input
							type="number" name="bidAmount" class="bid-input"
							placeholder="Enter your bid (min $10.50)" min="10.50" step="0.50"
							required>
						<button type="submit" class="bid-button">Place Bid</button>
					</form>
				</div>
			</div>

			<!-- Sample Product 3 -->
			<div class="product-row">
				<div class="product-image-container">
					<img src="${pageContext.request.contextPath}/resources/images/items/acousticguitar.png"
						alt="Acoustic Guitar" class="product-image">
					<button class="watch-button">♡</button>
				</div>

				<div class="product-info">
					<h3 class="product-name">Acoustic Guitar</h3>
					<div class="product-meta">Used - Very Good · Yamaha F310</div>

					<div class="product-price-container">
						<div class="price-info">
							<div class="current-price">$95.00</div>
							<div class="shipping-info">$15.00 shipping</div>
						</div>
						<div class="bid-info">
							<div class="bid-count">8 bids</div>
							<div class="time-left">6h 10m</div>
						</div>
					</div>

					<form class="bid-form" action="placeBid.jsp" method="post">
						<input type="hidden" name="productId" value="3"> <input
							type="number" name="bidAmount" class="bid-input"
							placeholder="Enter your bid (min $100.00)" min="100.00"
							step="1.00" required>
						<button type="submit" class="bid-button">Place Bid</button>
					</form>
				</div>
			</div>

			<!-- Sample Product 4 -->
			<div class="product-row">
				<div class="product-image-container">
					<img src="${pageContext.request.contextPath}/resources/images/items/ergonomicchair.jpg"
						alt="Office Chair" class="product-image">
					<button class="watch-button">♡</button>
				</div>

				<div class="product-info">
					<h3 class="product-name">Office Chair</h3>
					<div class="product-meta">New · Ergonomic Design</div>

					<div class="product-price-container">
						<div class="price-info">
							<div class="current-price">$75.25</div>
							<div class="shipping-info">Local Pickup</div>
						</div>
						<div class="bid-info">
							<div class="bid-count">3 bids</div>
							<div class="time-left">4d 12h</div>
						</div>
					</div>

					<form class="bid-form" action="placeBid.jsp" method="post">
						<input type="hidden" name="productId" value="4"> <input
							type="number" name="bidAmount" class="bid-input"
							placeholder="Enter your bid (min $80.00)" min="80.00" step="1.00"
							required>
						<button type="submit" class="bid-button">Place Bid</button>
					</form>
				</div>
			</div>

		</div>
	</div>
	<%-- Footer --%>
	<jsp:include page="footer.jsp" />
</body>
</html>