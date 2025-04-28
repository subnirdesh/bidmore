<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>About Us - BidMore | Redefining Auctions</title>
<meta name="description"
	content="Learn about BidMore's mission to make online auctions easy, exciting, and accessible for buyers and sellers.">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/about.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>

	<%-- Header --%>
	<jsp:include page="header.jsp" />

	<main>

		<section class="section hero-section">
			<div class="container">
				<div class="hero-content">
					<div class="text-content">
						<h1 class="hero-text">About BIDMORE:</h1>
						<h1 class="sub-hero-text">Redefining the Auction Experience</h1>
						<p class="lead">We believe buying and selling unique items
							should be easy, exciting, and accessible to everyone. BIDMORE is
							the platform making that happen, connecting a vibrant community
							of buyers and sellers through the dynamic energy of online
							auctions.</p>
					</div>
					<div class="image-content">
						<img
							src="${pageContext.request.contextPath}/resources/images/system/about.png"
							alt="Abstract illustration representing auction value growth">
					</div>
				</div>
			</div>
		</section>

		<section
			class="section story-mission-section section-light-bg section-centered">
			<div class="container">
				<div class="sophisticated-header">
					<div class="header-line"></div>
					<h2>
						Our <span class="accent-text">Story</span> & <span
							class="accent-text">Mission</span>
					</h2>
					<div class="header-line"></div>
				</div>

				<div class="content-wrapper">
					<div class="story-card">
						<div class="card-accent"></div>
						<div class="icon-container">
							<div class="icon-circle">
								<span class="elegant-icon">✦</span>
							</div>
						</div>
						<p>
							BidMore was born from a simple idea: the <span
								class="highlight-text">thrill of discovery</span> and the
							fairness of value setting found in traditional auctions shouldn't
							be confined to specific places or times. We saw an opportunity to
							democratize the auction process, bringing it online in a way that
							is intuitive, transparent, and genuinely fun.
						</p>
					</div>

					<div class="connector">
						<div class="dot-line"></div>
					</div>

					<div class="mission-card">
						<div class="card-accent"></div>
						<div class="icon-container">
							<div class="icon-circle">
								<span class="elegant-icon">✧</span>
							</div>
						</div>
						<p>
							Our mission is to empower individuals to easily turn items they
							no longer need into value, and to help buyers discover <span
								class="highlight-text">hidden gems and unique treasures</span>
							from across the community. We strive to build a platform where
							every bid feels exciting and every transaction is seamless.
						</p>
					</div>
				</div>

				<div class="elegant-footer">
					<button class="sophisticated-button">Join Our Community</button>
				</div>
			</div>
		</section>

		<%-- Section 3: What We Do --%>
		<section class="section what-we-do-section section-centered">
			<div class="container">
				<h2 class="section-title">What We Do</h2>
				<p class="section-intro">BidMore provides a modern,
					user-friendly platform for listing and bidding on a wide variety of
					items via auction.</p>

				<div class="card-container">
					<div class="info-card">
						<div class="card-header">
							<h3>For Buyers</h3>
						</div>
						<p>Dive into a world of unique finds. Bid on items you won't
							see everywhere, secure in the knowledge that you're participating
							in a transparent, competitive process that helps establish fair
							market value. Experience the genuine excitement of watching the
							clock tick down and winning an item you love.</p>
					</div>

					<div class="info-card">
						<div class="card-header">
							<h3>For Sellers</h3>
						</div>
						<p>Easily list your items in just a few steps. Reach a broad,
							engaged audience eager to discover something new. Our auction
							format helps ensure your items find their true value, connecting
							you directly with motivated buyers.</p>
					</div>
				</div>

				<p class="platform-info">Our platform is designed with
					simplicity and engagement at its core, ensuring a smooth experience
					whether you're a first-time user or a seasoned auction enthusiast.
				</p>
			</div>
		</section>

		<%-- Section 4: Our Values --%>
		<section class="section values-section section-light-bg">
			<div class="container section-centered">
				<h2 class="section-title">Our Values</h2>
				<p class="section-intro">Our actions are guided by the
					principles that make BidMore a trusted and exciting place to be:</p>

				<div class="values-grid">
					<div class="value-item">
						<span class="value-accent"></span>
						<h4>Transparency</h4>
						<p>Open and honest bidding is fundamental. We provide clear
							information and a level playing field for all participants.</p>
					</div>

					<div class="value-item">
						<span class="value-accent"></span>
						<h4>Community</h4>
						<p>We're building more than a marketplace; we're fostering
							connections between people who share a passion for unique items
							and the thrill of the auction.</p>
					</div>

					<div class="value-item">
						<span class="value-accent"></span>
						<h4>Simplicity</h4>
						<p>We strip away complexity, making the process of buying and
							selling through auction straightforward and accessible to
							everyone.</p>
					</div>

					<div class="value-item">
						<span class="value-accent"></span>
						<h4>Excitement</h4>
						<p>We aim to capture the dynamic energy of the auction
							environment, making every interaction on BidMore engaging and
							fun.</p>
					</div>

					<div class="value-item">
						<span class="value-accent"></span>
						<h4>Innovation</h4>
						<p>We are constantly evolving, using technology to improve the
							user experience and introduce new ways to connect and transact.</p>
					</div>

					<div class="value-item">
						<span class="value-accent"></span>
						<h4>Reliability</h4>
						<p>We focus on creating a stable and secure platform where you
							can buy and sell with confidence.</p>
					</div>
				</div>
			</div>
		</section>


		<%-- Section 6: Join the BidMore Community (CTA) --%>
		<section class="section cta-section section-centered section-light-bg">
			<div class="container">
				<h2>Join the BidMore Community</h2>
				<p>Ready to discover unique items or turn your own items into
					value?</p>
				<div>
					<a href="/buy" class="cta-button primary">Explore Auctions</a> <a
						href="/sell" class="cta-button accent">List Your Item</a>
					<%-- Replace # with actual buy/sell page URLs --%>
				</div>
				<a href="/signup" class="cta-link">or Sign Up Now</a>
				<%-- Replace # with actual signup page URL --%>
			</div>
		</section>

	</main>

	<jsp:include page="footer.jsp" />

</body>
</html>