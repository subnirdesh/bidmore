<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BidMore - Buy & Sell via Online Auction</title>

<%-- CSS Links --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />

<%-- Google Fonts (Poppins) --%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">

<%-- Font Awesome for Icons --%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">

</head>
<body>
	<%-- Header --%>
	<jsp:include page="header.jsp" />

	<%-- Main Content Area --%>
	<main class="main-content">

		<!-- Hero Section -->
		<section class="hero" id="top">
			<div class="container">
				<div class="hero-content">
					<div class="hero-text">
						<h1>
							Discover & Auction <span class="accent">Extraordinary</span>
							Items
						</h1>
						<p class="subtitle">A modern marketplace connecting sellers
							and collectors. Simple, transparent, and secure.</p>

						<form class="search-form">
							<div class="input-wrapper">
								<i class="fas fa-search search-icon"></i> <input type="search"
									name="query" placeholder="What are you looking for?" required
									aria-label="Search Auctions">
								<button type="submit" class="search-button">Search</button>
							</div>
						</form>

						<div class="cta-container">
							<span class="cta-divider">or</span> <a
								href="${pageContext.request.contextPath}/sell/start"
								class="btn-sell"> List Your Item <i
								class="fas fa-arrow-right"></i>
							</a>
						</div>
					</div>

					<div class="hero-visual">
						<div class="image-container">
							<img
								src="${pageContext.request.contextPath}/resources/images/system/hero-auction-platform.png"
								alt="Curated auction items and bidding experience"
								class="hero-image">
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- Featured Auctions Section -->
		<section class="featured-auctions" id="auctions">
			<div class="container">
				<div class="section-header">
					<h2>
						Hot Auctions <span class="accent">Ending Soon</span>
					</h2>
					<a href="${pageContext.request.contextPath}/auctions"
						class="view-all">View All <i class="fas fa-arrow-right"></i></a>
				</div>

				<div class="auction-grid">
					<!-- This block will be repeated for each auction from the database -->
					<div class="auction-card">
						<div class="auction-image">
							<img
								src="${pageContext.request.contextPath}/resources/images/items/nikeairmax.png"
								alt="Nike Air Max" loading="lazy">
							<div class="time-badge">
								<i class="far fa-clock"></i> 1d 8h
							</div>
						</div>
						<div class="auction-content">
							<h3 class="auction-title">Nike Air Max</h3>
							<div class="bid-info">
								<div class="current-bid">
									<span class="label">Current Bid</span> <span class="amount">$250</span>
								</div>
								<div class="bid-count">
									<span class="count">8</span> <span class="label">Bids</span>
								</div>
							</div>
							<a href="${pageContext.request.contextPath}/auction?id=1"
								class="btn-bid">Bid Now</a>
						</div>
					</div>

					<div class="auction-card">
						<div class="auction-image">
							<img
								src="${pageContext.request.contextPath}/resources/images/items/ergonomicchair.jpg"
								alt="Antique Vase" loading="lazy">
							<div class="time-badge">
								<i class="far fa-clock"></i> 3d 2h
							</div>
						</div>
						<div class="auction-content">
							<h3 class="auction-title">Ergonomic Chair</h3>
							<div class="bid-info">
								<div class="current-bid">
									<span class="label">Current Bid</span> <span class="amount">$800</span>
								</div>
								<div class="bid-count">
									<span class="count">12</span> <span class="label">Bids</span>
								</div>
							</div>
							<a href="${pageContext.request.contextPath}/auction?id=2"
								class="btn-bid">Bid Now</a>
						</div>
					</div>

					<div class="auction-card">
						<div class="auction-image">
							<img
								src="${pageContext.request.contextPath}/resources/images/items/alchemist.jpg"
								alt="Signed Baseball" loading="lazy">
							<div class="time-badge urgent">
								<i class="far fa-clock"></i> Ends Soon!
							</div>
						</div>
						<div class="auction-content">
							<h3 class="auction-title">Alchemist </h3>
							<div class="bid-info">
								<div class="current-bid">
									<span class="label">Starting Bid</span> <span class="amount">$10</span>
								</div>
								<div class="bid-count">
									<span class="count">0</span> <span class="label">Bids</span>
								</div>
							</div>
							<a href="${pageContext.request.contextPath}/auction?id=3"
								class="btn-bid">Bid Now</a>
						</div>
					</div>

					<div class="auction-card">
						<div class="auction-image">
							<img
								src="${pageContext.request.contextPath}/resources/images/items/acousticguitar.png"
								alt="Gaming Laptop" loading="lazy">
							<div class="time-badge">
								<i class="far fa-clock"></i> 18h
							</div>
						</div>
						<div class="auction-content">
							<h3 class="auction-title">Acoustic Guitar</h3>
							<div class="bid-info">
								<div class="current-bid">
									<span class="label">Current Bid</span> <span class="amount">$980</span>
								</div>
								<div class="bid-count">
									<span class="count">5</span> <span class="label">Bids</span>
								</div>
							</div>
							<a href="${pageContext.request.contextPath}/auction?id=4"
								class="btn-bid">Bid Now</a>
						</div>
					</div>
					<!-- End of repeated block -->
				</div>
			</div>
		</section>

		<!-- Platform Highlights Section -->
		<section class="platform-highlights" id="why-bidmore">
			<div class="container">
				<h2>Why BidMore?</h2>

				<p class="section-subtitle">Discover the features that make
					BidMore the preferred platform for online auctions</p>

				<div class="highlights-grid">
					<div class="highlight-item">
						<div class="highlight-accent"></div>
						<div class="highlight-icon">
							<i class="fas fa-shopping-basket"></i>
						</div>
						<h3>Vast Selection</h3>
						<p>Explore a diverse marketplace of unique items listed by our
							growing community.</p>
					</div>

					<div class="highlight-item">
						<div class="highlight-accent"></div>
						<div class="highlight-icon">
							<i class="fas fa-gavel"></i>
						</div>
						<h3>Exciting Bidding</h3>
						<p>Experience the thrill of auction bidding and win items at
							great prices.</p>
					</div>

					<div class="highlight-item">
						<div class="highlight-accent"></div>
						<div class="highlight-icon">
							<i class="fas fa-upload"></i>
						</div>
						<h3>Easy Listing</h3>
						<p>Quickly list your items for sale with our simple,
							step-by-step process.</p>
					</div>

					<div class="highlight-item">
						<div class="highlight-accent"></div>
						<div class="highlight-icon">
							<i class="fas fa-lock"></i>
						</div>
						<h3>Secure Transactions</h3>
						<p>Buy and sell with confidence thanks to our secure platform
							and processes.</p>
					</div>

					<div class="highlight-item">
						<div class="highlight-accent"></div>
						<div class="highlight-icon">
							<i class="fas fa-chart-line"></i>
						</div>
						<h3>Seller Dashboard</h3>
						<p>Manage your listings, track sales, and communicate with
							buyers easily.</p>
					</div>

					<div class="highlight-item">
						<div class="highlight-accent"></div>
						<div class="highlight-icon">
							<i class="fas fa-mobile-alt"></i>
						</div>
						<h3>Mobile Friendly</h3>
						<p>Bid, sell, and manage your account seamlessly on any
							device.</p>
					</div>
				</div>
			</div>
		</section>
		
		<!-- Start Selling Section -->
		<section class="start-selling" id="sell">
			<div class="container">
				<h2>Start Your Auction Journey</h2>

				<p class="section-subtitle">Join our vibrant community and turn
					your treasures into opportunities in just three simple steps</p>

				<div class="selling-steps-container">
					<div class="selling-step-item">
						<div class="step-accent"></div>
						<span class="step-number">1</span>
						<div class="step-icon">
							<i class="fas fa-camera-retro"></i>
						</div>
						<h3>Create Your Listing</h3>
						<p>Showcase your item with quality photos and compelling
							descriptions that capture bidders' attention.</p>
					</div>

					<div class="step-connector">
						<div class="connector-line"></div>
						<div class="connector-icon">
							<i class="fas fa-arrow-right"></i>
						</div>
					</div>

					<div class="selling-step-item">
						<div class="step-accent"></div>
						<span class="step-number">2</span>
						<div class="step-icon">
							<i class="fas fa-gavel"></i>
						</div>
						<h3>Engage With Bidders</h3>
						<p>Set your auction duration, answer questions, and watch as
							community members compete for your item.</p>
					</div>

					<div class="step-connector">
						<div class="connector-line"></div>
						<div class="connector-icon">
							<i class="fas fa-arrow-right"></i>
						</div>
					</div>

					<div class="selling-step-item">
						<div class="step-accent"></div>
						<span class="step-number">3</span>
						<div class="step-icon">
							<i class="fas fa-hand-holding-usd"></i>
						</div>
						<h3>Complete The Deal</h3>
						<p>Finalize the transaction with your winning bidder and enjoy
							your earnings from a successful auction.</p>
					</div>
				</div>

				<div class="cta-container">
					<a href="${pageContext.request.contextPath}/sell/start"
						class="btn-start-selling"> Join Our Selling Community <i
						class="fas fa-arrow-right"></i>
					</a>
				</div>
			</div>
		</section>



		<!-- Final Call to Action -->
		<section class="final-cta">
			<div class="container">
				<h2>Ready to Get Started?</h2>
				<p>Find your next treasure or list an item for sale today!</p>
				<div class="final-cta-buttons">
					<a href="#auctions" class="btn btn-primary btn-large">Browse
						Auctions</a> <a href="${pageContext.request.contextPath}/sell/start"
						class="btn btn-secondary btn-large">List an Item</a>
				</div>
			</div>
		</section>

	</main>
	<%-- End Main Content --%>

	<%-- Footer --%>
	<jsp:include page="footer.jsp" />


</body>
</html>