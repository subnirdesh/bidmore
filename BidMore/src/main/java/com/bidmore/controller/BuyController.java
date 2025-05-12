package com.bidmore.controller;

import java.io.IOException;
import java.util.List;

import com.bidmore.model.AuctionModel;
import com.bidmore.model.BidModel;
import com.bidmore.model.UserModel;
import com.bidmore.services.BuyService;
import com.bidmore.services.PortfolioService;
import com.bidmore.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BuyController
 */
@WebServlet(asyncSupported = true, urlPatterns = "/buy")
public class BuyController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BuyService buyService;
	private PortfolioService portfolioService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// Initializing dependencies in init()
		this.buyService = new BuyService();
		this.portfolioService = new PortfolioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Getting all listing with their item and auction details
		List<AuctionModel> auctions = buyService.getListings();
		req.setAttribute("auctions", auctions);

		req.getRequestDispatcher("WEB-INF/pages/buy.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String validationMessage = validateBidForm(req);
			if (validationMessage != null) {
				handleError(req, resp, validationMessage);
				return;
			}

			// Using SessionUtil to get the username
			String userName = (String) SessionUtil.getAttribute(req, "username");
			UserModel userModel = portfolioService.getUserInfo(userName);
			String auctionIdStr = req.getParameter("auctionId");
			int auctionId = Integer.parseInt(auctionIdStr);
			AuctionModel auctionModel = buyService.getAuctionById(auctionId);

			// Checking if user is trying to bid on their own listing
			if (isUserOwnAuction(userModel, auctionModel)) {
				handleError(req, resp, "You cannot bid on your own listing!");
				return;
			}
			
			// Check if user has already placed a bid on this auction
            if (hasUserAlreadyBid(userModel, auctionModel)) {
                handleError(req, resp, "You have already placed a bid on this auction!");
                return;
            }

			BidModel bidModel = extractBidModel(req, userModel, auctionModel);
			Boolean isPlaced = buyService.placeBid(bidModel);

			if (isPlaced == null) {
				handleError(req, resp, "Our server is under maintenance. Please try again later!");
				return;
			} else if (isPlaced) {

				handleSuccess(req, resp, "Your bid is successfully placed", "/buy");

			} else {
				handleError(req, resp, "Could not place your bid. Please try again later!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			return;

		}

	}

	private String validateBidForm(HttpServletRequest request) {
		String auctionIdStr = request.getParameter("auctionId");
		String bidAmountStr = request.getParameter("bidAmount");

		// Validate auction ID
		if (auctionIdStr == null || auctionIdStr.trim().isEmpty()) {
			return "Our Server is Under Maintainance.";
		}

		int auctionId;
		try {
			auctionId = Integer.parseInt(auctionIdStr);
			if (auctionId <= 0) {
				return "Some problem occured! Please try again!";
			}
		} catch (NumberFormatException e) {
			return "Some problem occured! Please try again!";
		}

		// Validate bid amount
		if (bidAmountStr == null || bidAmountStr.trim().isEmpty()) {
			return "Bid amount is required";
		}

		float bidAmount;
		try {
			bidAmount = Float.parseFloat(bidAmountStr);
			if (bidAmount <= 0) {
				return "Bid amount must be greater than zero";
			}
		} catch (NumberFormatException e) {
			return "Bid amount must be a valid number";
		}

		// Validation passed
		return null;
	}

	private BidModel extractBidModel(HttpServletRequest request, UserModel user, AuctionModel auction)
			throws ServletException {

		String bidAmountStr = request.getParameter("bidAmount");

		Float bidAmount = Float.parseFloat(bidAmountStr);

		BidModel bid = new BidModel();

		bid.setBidAmount(bidAmount);
		bid.setUser(user);
		bid.setAuction(auction);

		return bid;

	}

	/**
	 * Checks if the current user is the owner of the auction
	 * 
	 * @param user    Current user
	 * @param auction Auction to check
	 * @return true if user is the owner, false otherwise
	 */
	private boolean isUserOwnAuction(UserModel user, AuctionModel auction) {
		// Check if the auction's item seller ID matches the user's ID
		if (auction != null && auction.getItem() != null && auction.getItem().getItemId() != 0) {
			int sellerId = auction.getItem().getUser().getUserId();
			return sellerId == user.getUserId();
		}
		return false;
	}
	
	private boolean hasUserAlreadyBid(UserModel user, AuctionModel auction) {
        // Call the service method to check if user has already bid
        return buyService.hasUserBidOnAuction(user, auction);
    }

	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.getSession().setAttribute("successMessage", message);
		String contextPath = req.getContextPath();
		resp.sendRedirect(contextPath + redirectPage);
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);

		// Re-fetch auctions for the page reload
		List<AuctionModel> auctions = buyService.getListings();
		req.setAttribute("auctions", auctions);

		req.getRequestDispatcher("/WEB-INF/pages/buy.jsp").forward(req, resp);
	}

}
