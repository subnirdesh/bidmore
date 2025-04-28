<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Auction Marketplace - List an Item</title>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/sell.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
	<%-- Header --%>
	<jsp:include page="header.jsp" />
	<div class="container">

		<div class="form-container">
			<form action="processListing.jsp" method="post"
				enctype="multipart/form-data" class="listing-form">
				<div class="form-sections">
					<!-- Item Details Section -->
					<div class="form-section">
						<h2 class="section-title">Item Details</h2>

						<div class="form-group">
							<label for="itemName">Item Name</label> <input type="text"
								id="itemName" name="name" required
								placeholder="e.g. Nike Air Max Shoes">
						</div>

						<div class="form-group">
							<label for="itemDescription">Description</label>
							<textarea id="itemDescription" name="description" rows="4"
								required
								placeholder="Provide a detailed description of your item"></textarea>
						</div>

						<div class="form-group">
							<label for="itemCondition">Condition</label> <select
								id="itemCondition" name="condition" required>
								<option value="">Select condition</option>
								<option value="New">New</option>
								<option value="Used - Like New">Used - Like New</option>
								<option value="Used - Very Good">Used - Very Good</option>
								<option value="Used - Good">Used - Good</option>
								<option value="Used - Acceptable">Used - Acceptable</option>
							</select>
						</div>

						<div class="form-group">
							<label for="itemImage">Item Image</label>
							<div class="file-input-container">
								<input type="file" id="itemImage" name="image_path"
									accept="image/*" required>
								<div class="file-input-button">Choose File</div>
								<span class="file-name">No file chosen</span>
							</div>
							<div class="image-preview-container">
								<img id="imagePreview" src="#" alt="Image Preview"
									class="image-preview">
							</div>
						</div>
					</div>

					<!-- Auction Details Section -->
					<div class="form-section">
						<h2 class="section-title">Auction Details</h2>

						<div class="form-group">
							<label for="startPrice">Starting Price ($)</label> <input
								type="number" id="startPrice" name="start_price" min="0.01"
								step="0.01" required placeholder="e.g. 50.00">
						</div>

						<div class="form-group">
							<label for="reservePrice">Reserve Price ($)</label> <input
								type="number" id="reservePrice" name="reserve_price" min="0.01"
								step="0.01" required placeholder="e.g. 70.00">
							<div class="helper-text">Minimum price for the item to be
								sold</div>
						</div>

						<div class="form-group">
							<label for="buyNowPrice">Buy Now Price ($)</label> <input
								type="number" id="buyNowPrice" name="buynow_price" min="0.01"
								step="0.01" required placeholder="e.g. 90.00">
							<div class="helper-text">Price at which a buyer can
								immediately purchase the item</div>
						</div>

						<div class="form-group">
							<label for="auctionDuration">Auction Duration</label> <select
								id="auctionDuration" name="duration" required>
								<option value="">Select duration</option>
								<option value="3">3 Days</option>
								<option value="5">5 Days</option>
								<option value="7">7 Days</option>
								<option value="10">10 Days</option>
								<option value="14">14 Days</option>
							</select>
						</div>
					</div>
				</div>

				<div class="form-actions">
					<button type="reset" class="btn btn-secondary">Reset Form</button>
					<button type="submit" class="btn btn-primary">List Item
						for Auction</button>
				</div>
			</form>
		</div>
	</div>
<%-- Footer--%>
	<jsp:include page="footer.jsp" />
</body>
</html>